package cn.iamding.drpc.server.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

/**
 * 系统工具类，用于获取系统相关信息
 */
public class CustomSystemUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CustomSystemUtil.class);

    public static String INNER_IP = getInnerIp(); // 内网IP
    public static String OUTTER_IP = getOutterIp(); // 外网IP

    private CustomSystemUtil() {
    }

    /**
     * 获得内网IP
     *
     * @return 内网IP
     */
    private static String getInnerIp() {
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获得外网IP
     *
     * @return 外网IP
     */
    private static String getOutterIp() {
        try {
            Enumeration<NetworkInterface> networks = NetworkInterface.getNetworkInterfaces();
            InetAddress ip = null;
            Enumeration<InetAddress> addrs;
            while (networks.hasMoreElements()) {
                addrs = networks.nextElement().getInetAddresses();
                while (addrs.hasMoreElements()) {
                    ip = addrs.nextElement();
                    if (ip instanceof Inet4Address
                            && ip.isSiteLocalAddress()
                            && !ip.getHostAddress().equals(INNER_IP)) {
                        LOGGER.warn("使用外网ip[{}]", ip);
                        return ip.getHostAddress();
                    }
                }
            }
            LOGGER.warn("外网ip为空，使用内网ip[{}]", INNER_IP);
            // 如果没有外网IP，就返回内网IP
            return INNER_IP;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}