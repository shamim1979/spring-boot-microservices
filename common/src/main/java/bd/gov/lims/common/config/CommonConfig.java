package bd.gov.lims.common.config;

import net.kaczmarzyk.spring.data.jpa.web.SpecificationArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
public class CommonConfig implements WebMvcConfigurer {
    private final AbstractApplicationContext abstractApplicationContext;
    @Autowired
    CommonConfig(AbstractApplicationContext abstractApplicationContext) {
        this.abstractApplicationContext = abstractApplicationContext;
    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(new SpecificationArgumentResolver(abstractApplicationContext));
        //argumentResolvers.add(new PageableHandlerMethodArgumentResolver());
    }

}
