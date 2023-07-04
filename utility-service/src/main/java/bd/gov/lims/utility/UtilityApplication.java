package bd.gov.lims.utility;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"bd.gov.lims"})
@EnableDiscoveryClient
public class UtilityApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(UtilityApplication.class)
                .web(WebApplicationType.REACTIVE)
                .build()
                .run(args);
    }
}
