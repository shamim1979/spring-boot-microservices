package bd.gov.lims.gateway.config;

import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayServerConfig {
    /*@Bean
    SecurityWebFilterChain securityFilterChain(ServerHttpSecurity http) throws Exception {
        return http
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/**").permitAll()
                        .anyExchange().authenticated())
                .oauth2Login(withDefaults())
                .build();
    }*/
}
