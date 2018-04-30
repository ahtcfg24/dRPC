package cn.iamding.drpc.server.zk;

import io.grpc.Attributes;
import io.grpc.NameResolver;
import io.grpc.NameResolverProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nullable;
import java.net.URI;
import java.util.Comparator;


public class ZookeeperZoneAwareNameResolverProvider extends NameResolverProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(ZookeeperZoneAwareNameResolverProvider.class);

    private static final String SCHEME = "zk";
    private final String zookeeperAddress;
    private final Comparator<ServiceDiscovery.HostandZone> hostComparator;

    private ZookeeperZoneAwareNameResolverProvider(String zookeeperAddress,
                                                   Comparator<ServiceDiscovery.HostandZone> hostComparator) {
        this.zookeeperAddress = zookeeperAddress;
        this.hostComparator = hostComparator;
    }

    @Override
    protected boolean isAvailable() {
        return true;
    }

    @Override
    protected int priority() {
        return 5;
    }

    @Nullable
    @Override
    public NameResolver newNameResolver(URI targetUri, Attributes params) {
        return new ZookeeperZoneAwareNameResolver(targetUri, new ServiceDiscovery(zookeeperAddress), hostComparator);
    }

    @Override
    public String getDefaultScheme() {
        return SCHEME;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        private String zookeeperAddress;

        public Builder setZookeeperAddress(String zookeeperAddress) {
            this.zookeeperAddress = zookeeperAddress;
            return this;
        }

        public NameResolverProvider build() {
            Comparator<ServiceDiscovery.HostandZone> comparator;
            comparator = Comparator.comparing(hostandZone -> {
                String host = hostandZone.getHostURI().getHost();
                LOGGER.info("节点[{}]可用", host);
                return host;
            }, Comparator.naturalOrder());
            return new ZookeeperZoneAwareNameResolverProvider(zookeeperAddress, comparator);
        }
    }
}
