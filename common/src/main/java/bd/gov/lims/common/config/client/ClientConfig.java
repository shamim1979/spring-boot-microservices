package bd.gov.lims.common.config.client;

import bd.gov.lims.common.errorhandling.ApiErrorResponse;
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
            log.info("client response status code -> {}", clientResponse.bodyToMono(ApiErrorResponse.class));
            if (clientResponse.statusCode().is5xxServerError()) {
                return clientResponse.bodyToMono(ApiErrorResponse.class)
                        .flatMap(errorResponse -> {
                            errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR);
                            fillErrorResponse(errorResponse);
                            return Mono.error(new ClientResponseException(errorResponse));
                        });
            } else if (clientResponse.statusCode().is4xxClientError()) {
                return clientResponse.bodyToMono(ApiErrorResponse.class)
                        .flatMap(errorResponse -> {
                            errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST);
                            fillErrorResponse(errorResponse);
                            return Mono.error(new ClientResponseException(errorResponse));
                        });
            } else {
                return Mono.just(clientResponse);
            }
        });
    }

    private void fillErrorResponse(ApiErrorResponse errorResponse) {
        if (errorResponse.getFieldErrors() == null) {
            errorResponse.setFieldErrors(Collections.emptyList());
        }
        if (errorResponse.getGlobalErrors() == null) {
            errorResponse.setGlobalErrors(Collections.emptyList());
        }
        if (errorResponse.getParameterErrors() == null) {
            errorResponse.setParameterErrors(Collections.emptyList());
        }
        if (errorResponse.getProperties() == null) {
            errorResponse.setProperties(Collections.EMPTY_MAP);
        }
    }
}
