package cn.iamding.drpc.client.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * order注解值越小越先执行
 */
@Order(1)
@WebFilter(filterName = "baseFilter", urlPatterns = "/*")
public class BaseFilter implements Filter {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpReq = (HttpServletRequest) servletRequest;
        LOGGER.info("baseFilter client ip={} url={} method={} params={}", httpReq.getRemoteAddr(),
                httpReq.getRequestURL(), httpReq.getMethod(), httpReq.getParameterMap());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {

    }
}