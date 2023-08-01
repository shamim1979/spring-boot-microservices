package bd.gov.lims.common.config;

import bd.gov.lims.common.config.specification.WebfluxSpecificationArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@AutoConfiguration
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.REACTIVE)
public class ArgumentResolverConfig implements WebFluxConfigurer {
    private final AbstractApplicationContext abstractApplicationContext;
    @Autowired
    ArgumentResolverConfig(AbstractApplicationContext abstractApplicationContext) {
        this.abstractApplicationContext = abstractApplicationContext;
    }

    @Override
    public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
        configurer.addCustomResolver(getCustomResolver());
        WebFluxConfigurer.super.configureArgumentResolvers(configurer);
    }

    private HandlerMethodArgumentResolver getCustomResolver() {
        return (HandlerMethodArgumentResolver) new WebfluxSpecificationArgumentResolver(abstractApplicationContext);
    }
}
