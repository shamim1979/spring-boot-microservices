package bd.gov.lims.common.config.client;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class ClientConfig {

    @Bean
    @Qualifier("userClient")
    @LoadBalanced
    WebClient.Builder userClient() {
        return WebClient.builder()
                .baseUrl("http://USER-SERVICE");
    }
}
