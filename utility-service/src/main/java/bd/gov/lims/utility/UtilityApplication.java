package bd.gov.lims.utility;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"bd.gov.lims"})
public class UtilityApplication {
    public static void main(String[] args) {
        SpringApplication.run(UtilityApplication.class);
    }
}
