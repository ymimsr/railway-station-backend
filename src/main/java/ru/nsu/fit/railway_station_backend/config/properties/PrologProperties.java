package ru.nsu.fit.railway_station_backend.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("prolog")
@Getter
@Setter
public class PrologProperties {

    private String filename;

}
