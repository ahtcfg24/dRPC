package cn.iamding.drpc.server.utils;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class HostUtils {
    private static final Logger LOGGER = LoggerFactory.getLogger(HostUtils.class);
    private static final LoadingCache<String, String> ipCache = CacheBuilder.newBuilder().expireAfterWrite(30, TimeUnit.MINUTES)
            .build(new CacheLoader<String, String>() {
                @Override
                public String load(String key) throws Exception {
                    return loadHostIp();
                }
            });
    private static final String HOST_IP_KEY = "host_ip";

    private static String loadHostIp() {
        Enumeration<NetworkInterface> nifs = null;
        try {
            nifs = NetworkInterface.getNetworkInterfaces();
            while (nifs.hasMoreElements()) {
                NetworkInterface nif = nifs.nextElement();

                // 获得与该网络接口绑定的 IP 地址，一般只有一个
                Enumeration<InetAddress> addresses = nif.getInetAddresses();
                while (addresses.hasMoreElements()) {
                    InetAddress addr = addresses.nextElement();

                    if (addr instanceof Inet4Address) { // 只关心 IPv4 地址
                        String ip = addr.getHostAddress();
                        LOGGER.info("网卡接口[{}]:[{}]", nif.getName(), addr.getHostAddress());
//                        if (ip.startsWith("10")) { //只返回内网ip
//                            return ip;
//                        }
                        if (!ip.startsWith("10")) { //只返回外网ip
                            return ip;
                        }

                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
    }

    public static String getHostIp() {
        try {
            return ipCache.get(HOST_IP_KEY);
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        return "127.0.0.1";
    }
}