package bd.gov.lims.registry;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableEurekaServer
@ComponentScan(basePackages = {"bd.gov.lims"})
public class RegistryApplication {
    public static void main(String[] args) {
        SpringApplication.run(RegistryApplication.class);
    }
}
