package at.java.ex06.monitoring;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component
public class CompassHealthIndicator implements HealthIndicator {
    private long maxDuration = 0L;

    @Override
    public Health health() {
        Health.Builder builder = Health.up();
        System.gc();
        builder.withDetail("freememory", Runtime.getRuntime().freeMemory());
        builder.withDetail("maxmemory", Runtime.getRuntime().maxMemory());
        builder.withDetail("cpucores", Runtime.getRuntime().availableProcessors());
        builder.withDetail("max method call duration", maxDuration);
        return builder.build();
    }

    public void reportDuration(long duration) {
        if (duration > this.maxDuration) {
            this.maxDuration = duration;
        }
    }
}
