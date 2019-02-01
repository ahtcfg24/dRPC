package cn.iamding.drpc.client.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.util.StringUtils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Order(2)
@WebFilter(filterName = "CrossDomainFilter", urlPatterns = "/*")
public class CrossDomainFilter implements Filter {

    private Logger logger = LoggerFactory.getLogger(CrossDomainFilter.class);

    private static final String LOCALHOST_PORT_REGEX = "^localhost:\\d+$";
    private static final String ALLOW_METHODS = "GET, POST, PUT, DELETE, OPTIONS";
    private static final String ALLOW_HEADERS = "Accept, Content-Type, Content-Range, Content-Disposition";
    private String[] originDomainSuffix = new String[]{"iamding.cn", "localhost"};
    private String allowHeadersWithAddition = ALLOW_HEADERS;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        logger.info("CrossDomainFilter init allowMethods={} allowHeaders={} allowOrigins={}",
                ALLOW_METHODS, allowHeadersWithAddition, originDomainSuffix);
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResp = (HttpServletResponse) servletResponse;

        httpResp.addHeader("Access-Control-Allow-Methods", ALLOW_METHODS);
        httpResp.addHeader("Access-Control-Allow-Headers", allowHeadersWithAddition);

        String requestOrigin = httpReq.getHeader("Origin");

        if (isSuffixOrigin(requestOrigin)) {
            httpResp.addHeader("Access-Control-Allow-Origin", requestOrigin);
            httpResp.addHeader("Access-Control-Allow-Credentials", "true");
        } else if (StringUtils.isEmpty(requestOrigin) || "null".equals(requestOrigin)) {
            logger.info("request without cors origin={} url={}", requestOrigin, httpReq.getRequestURL());
        } else {
            httpResp.setStatus(403);
            logger.warn("request forbidden origin={} url={}", requestOrigin, httpReq.getRequestURL());
            return;
        }
        if ("OPTIONS".equalsIgnoreCase(httpReq.getMethod())) {
            return;
        }
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }

    /**
     * 判定域后缀是否在白名单内
     */
    private boolean isSuffixOrigin(String requestOrigin) {
        if (originDomainSuffix == null || originDomainSuffix.length == 0) {
            return true;
        }

        if (StringUtils.isEmpty(requestOrigin)) {
            return false;
        }

        String requestOriginHost = getHost(requestOrigin);
        for (String val : originDomainSuffix) {
            if (requestOriginHost.endsWith("." + val) || requestOriginHost.equals(val)) {
                return true;
            }
        }

        return requestOriginHost.matches(LOCALHOST_PORT_REGEX);
    }

    /**
     * 获取Origin host
     */
    private String getHost(String requestOrigin) {
        return requestOrigin.replaceFirst("http://|https://", "");
    }
}
