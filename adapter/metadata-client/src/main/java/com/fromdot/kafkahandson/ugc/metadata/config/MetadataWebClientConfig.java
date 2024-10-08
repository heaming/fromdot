package com.fromdot.kafkahandson.ugc.metadata.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class MetadataWebClientConfig {

    @Value("${external-server.metadata.url}")
    private String medataApiUrl;

    @Bean
    @Primary
    public WebClient metadataWebClient() {
        return WebClient.builder()
                .baseUrl(medataApiUrl)
                .build();
    }
}
