package bd.gov.lims.common.config;

import net.kaczmarzyk.spring.data.jpa.web.ProcessingContext;
import net.kaczmarzyk.spring.data.jpa.web.SpecificationFactory;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.core.MethodParameter;
import org.springframework.core.convert.ConversionService;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.reactive.BindingContext;
import org.springframework.web.reactive.result.method.HandlerMethodArgumentResolver;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.lang.annotation.Annotation;
import java.util.Iterator;
import java.util.Locale;

public class CustomSpecificationArgumentResolver implements HandlerMethodArgumentResolver {
    private SpecificationFactory specificationFactory;

    public CustomSpecificationArgumentResolver(ConversionService conversionService, AbstractApplicationContext abstractApplicationContext) {
        this(conversionService, abstractApplicationContext, Locale.getDefault());
    }

    public CustomSpecificationArgumentResolver(AbstractApplicationContext applicationContext) {
        this((ConversionService)null, (AbstractApplicationContext)applicationContext);
    }

    public CustomSpecificationArgumentResolver(ConversionService conversionService, AbstractApplicationContext abstractApplicationContext, Locale defaultLocale) {
        this.specificationFactory = new SpecificationFactory(conversionService, abstractApplicationContext, defaultLocale);
    }

    public boolean supportsParameter(MethodParameter parameter) {
        Class<?> paramType = parameter.getParameterType();
        return paramType.isInterface() && Specification.class.isAssignableFrom(paramType) && this.isAnnotated(parameter);
    }

    @Override
    public Mono<Object> resolveArgument(MethodParameter parameter, BindingContext bindingContext, ServerWebExchange exchange) {
        return (Mono<Object>) this.specificationFactory.createSpecificationDependingOn((ProcessingContext) bindingContext);
    }

    private boolean isAnnotated(MethodParameter methodParameter) {
        Annotation[] var2 = methodParameter.getParameterAnnotations();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Annotation annotation = var2[var4];
            Iterator var6 = this.specificationFactory.getResolversBySupportedType().iterator();

            while(var6.hasNext()) {
                Class<? extends Annotation> annotationType = (Class)var6.next();
                if (annotationType.equals(annotation.annotationType())) {
                    return true;
                }
            }
        }

        return this.isAnnotatedRecursively(methodParameter.getParameterType());
    }

    private boolean isAnnotatedRecursively(Class<?> target) {
        if (target.getAnnotations().length != 0) {
            Iterator var2 = this.specificationFactory.getResolversBySupportedType().iterator();

            while(var2.hasNext()) {
                Class<? extends Annotation> annotationType = (Class)var2.next();
                if (target.getAnnotation(annotationType) != null) {
                    return true;
                }
            }
        }

        Class[] var6 = target.getInterfaces();
        int var7 = var6.length;

        for(int var4 = 0; var4 < var7; ++var4) {
            Class<?> targetInterface = var6[var4];
            if (this.isAnnotatedRecursively(targetInterface)) {
                return true;
            }
        }

        return false;
    }
}

