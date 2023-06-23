package ru.nsu.fit.railway_station_backend.config;

import lombok.RequiredArgsConstructor;
import org.projog.api.Projog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.parameters.P;
import ru.nsu.fit.railway_station_backend.config.properties.PrologProperties;

import java.io.File;

@Configuration
@RequiredArgsConstructor
public class PrologConfiguration {

    private final PrologProperties prologProperties;

    @Bean
    public Projog projogBean() {
        Projog projog = new Projog();
        projog.consultFile(new File(PrologConfiguration.class.getResource(prologProperties.getFilename()).getFile()));

        return projog;
    }

}
