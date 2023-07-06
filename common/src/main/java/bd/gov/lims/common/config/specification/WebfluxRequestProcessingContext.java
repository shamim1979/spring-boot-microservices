package bd.gov.lims.common.config.specification;

import net.kaczmarzyk.spring.data.jpa.utils.BodyParams;
import net.kaczmarzyk.spring.data.jpa.utils.JsonBodyParams;
import net.kaczmarzyk.spring.data.jpa.utils.QueryContext;
import net.kaczmarzyk.spring.data.jpa.web.DefaultQueryContext;
import net.kaczmarzyk.spring.data.jpa.web.ProcessingContext;
import net.kaczmarzyk.spring.data.jpa.web.annotation.MissingPathVarPolicy;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.reactive.HandlerMapping;
import org.springframework.web.server.ServerWebExchange;

import java.lang.annotation.Annotation;
import java.util.Collections;
import java.util.Map;
import java.util.Objects;


public class WebfluxRequestProcessingContext implements ProcessingContext {
    private final MethodParameter methodParameter;
    private final ServerWebExchange serverWebExchange;
    private BodyParams bodyParams;
    private Map<String, String> resolvedPathVariables;
    private QueryContext queryContext;

    public WebfluxRequestProcessingContext(MethodParameter methodParameter, ServerWebExchange serverWebExchange) {
        this.methodParameter = methodParameter;
        this.serverWebExchange = serverWebExchange;
        this.queryContext = new DefaultQueryContext();
    }

    public Class<?> getParameterType() {
        return this.methodParameter.getParameterType();
    }

    public Annotation[] getParameterAnnotations() {
        return this.methodParameter.getParameterAnnotations();
    }

    public String[] getParameterValues(String webParamName) {
        return new String[]{this.serverWebExchange.getRequest().getQueryParams().getFirst(webParamName)};
    }

    public QueryContext queryContext() {
        return this.queryContext;
    }

    public String getPathVariableValue(String pathVariableName, MissingPathVarPolicy missingPathVarPolicy) {
        String attributeName = HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE;
        return (String) this.serverWebExchange.getAttributeOrDefault(attributeName, Collections.emptyMap()).get(pathVariableName);
    }

    public String getRequestHeaderValue(String headerKey) {
        return this.serverWebExchange.getRequest().getHeaders().getFirst(headerKey);
    }

    public String[] getBodyParamValues(String bodyParamName) {
        return (String[])this.getBodyParams().getParamValues(bodyParamName).toArray(new String[0]);
    }

    private BodyParams getBodyParams() {
        if (Objects.isNull(this.bodyParams)) {
            this.bodyParams = BodyParams.empty();
            String contentType = this.getRequestHeaderValue("Content-Type");
            if (!Objects.isNull(contentType)) {
                MediaType mediaType = MediaType.parseMediaType(contentType);
                if (MediaType.APPLICATION_JSON.includes(mediaType)) {
                    this.bodyParams = JsonBodyParams.parse(this.getRequestBody());
                }
            }
        }

        return this.bodyParams;
    }

    private String getRequestBody() {
        try {
            ServerHttpRequest request = this.serverWebExchange.getRequest();
            if (request == null) {
                throw new IllegalStateException("Request body not present");
            } else {
                return "hello";
            }
        } catch (Exception var2) {
            throw new RuntimeException("Cannot read request body", var2);
        }
    }
}
