package com.denzhn.galacticgold.config.coincap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
@EnableScheduling
public class ClientConfig {
    @Value("${coin.cap.api.url}")
    private String apiUrl;

    @Bean
    WebClient getWebClient(){
        return WebClient.builder()
                .baseUrl(apiUrl)
                .defaultHeader(HttpHeaders.ACCEPT_ENCODING, "gzip")
                .build();
    }
}
