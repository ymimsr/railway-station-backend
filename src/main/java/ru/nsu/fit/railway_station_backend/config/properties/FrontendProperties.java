package ru.nsu.fit.railway_station_backend.config.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("front-end")
@Getter
@Setter
public class FrontendProperties {

    private String address;

}
