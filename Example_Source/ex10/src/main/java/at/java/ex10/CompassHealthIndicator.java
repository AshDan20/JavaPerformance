package at.java.ex10;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Properties;

@Component
public class CompassHealthIndicator implements HealthIndicator {
    @Override
    public Health health() {
        Health.Builder builder = Health.up();
        builder.withDetail("freememory", Runtime.getRuntime().freeMemory());
        builder.withDetail("cpu.cores", Runtime.getRuntime().availableProcessors());

        Properties props = System.getProperties();
        for (Map.Entry<Object, Object> entry:props.entrySet()){
            builder.withDetail(entry.getKey().toString(), entry.getValue());
        }

        return builder.build();
    }
}
