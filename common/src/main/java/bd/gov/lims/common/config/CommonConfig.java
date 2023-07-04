package bd.gov.lims.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@Configuration
public class CommonConfig implements WebFluxConfigurer {
    private final AbstractApplicationContext abstractApplicationContext;
    @Autowired
    CommonConfig(AbstractApplicationContext abstractApplicationContext) {
        this.abstractApplicationContext = abstractApplicationContext;
    }

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(getCustomResolver());
        WebFluxConfigurer.super.configureArgumentResolvers(configurer);
    }

    private HandlerMethodArgumentResolver getCustomResolver() {
        return (HandlerMethodArgumentResolver) new CustomSpecificationArgumentResolver(abstractApplicationContext);
    }
}
