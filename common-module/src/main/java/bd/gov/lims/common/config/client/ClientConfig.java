package bd.gov.lims.common.config.client;

import bd.gov.lims.base.support.ApiErrorResponseDto;
import bd.gov.lims.common.errorhandling.exception.ClientResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Configuration
@Slf4j
public class ClientConfig {

    @Bean
    @Qualifier("userClient")
    @LoadBalanced
    WebClient.Builder userClient() {
        return WebClient.builder()
                .filter(requestFilter())
                .filter(responseFilter())
                .baseUrl("http://USER-SERVICE");
    }

    private ExchangeFilterFunction requestFilter() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.info("client request method -> {} url -> {}", clientRequest.method(), clientRequest.url());
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction responseFilter() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.info("client response status code -> {}", clientResponse.bodyToMono(ApiErrorResponseDto.class));
            if (clientResponse.statusCode().is5xxServerError()) {
                return clientResponse.bodyToMono(ApiErrorResponseDto.class)
                        .flatMap(errorResponse -> {
                            errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
                            return Mono.error(new ClientResponseException(errorResponse));
                        });
            } else if (clientResponse.statusCode().is4xxClientError()) {
                return clientResponse.bodyToMono(ApiErrorResponseDto.class)
                        .flatMap(errorResponse -> {
                            errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
                            return Mono.error(new ClientResponseException(errorResponse));
                        });
            } else {
                return Mono.just(clientResponse);
            }
        });
    }
}
