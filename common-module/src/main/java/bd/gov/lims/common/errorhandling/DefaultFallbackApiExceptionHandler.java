package bd.gov.lims.common.errorhandling;

import bd.gov.lims.common.errorhandling.mapper.ErrorCodeMapper;
import bd.gov.lims.common.errorhandling.mapper.ErrorMessageMapper;
import bd.gov.lims.common.errorhandling.mapper.HttpStatusMapper;
import bd.gov.lims.common.support.ApiErrorResponseDto;
import bd.gov.lims.common.support.ErrorDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;


public class DefaultFallbackApiExceptionHandler implements FallbackApiExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultFallbackApiExceptionHandler.class);

    private final HttpStatusMapper httpStatusMapper;
    private final ErrorCodeMapper errorCodeMapper;
    private final ErrorMessageMapper errorMessageMapper;

    public DefaultFallbackApiExceptionHandler(HttpStatusMapper httpStatusMapper,
                                              ErrorCodeMapper errorCodeMapper,
                                              ErrorMessageMapper errorMessageMapper) {
        this.httpStatusMapper = httpStatusMapper;
        this.errorCodeMapper = errorCodeMapper;
        this.errorMessageMapper = errorMessageMapper;
    }

    @Override
    public ApiErrorResponseDto handle(Throwable exception) {
        int statusCode = httpStatusMapper.getHttpStatus(exception).value();
        String errorCode = errorCodeMapper.getErrorCode(exception);
        String errorMessage = errorMessageMapper.getErrorMessage(exception);

        ApiErrorResponseDto response = ApiErrorResponseDto.builder()
                .nonce(Instant.now().toEpochMilli())
                .status(statusCode)
                .message(errorMessage)
                .error(ErrorDto.builder()
                        .code(errorCode)
                        .message(errorMessage)
                        .build())
                .build();

        Map<String,Object> methodResponseErrorProperties = getMethodResponseErrorProperties(exception);
        Map<String,Object> fieldResponseErrorProperties = getFieldResponseErrorProperties(exception);
        Map<String,Object> allResponseErrorProperties = new HashMap<>();
        methodResponseErrorProperties.entrySet().stream()
                .forEach(entry ->allResponseErrorProperties.put(entry.getKey(),entry.getValue()));
        fieldResponseErrorProperties.entrySet().stream()
                .forEach(entry ->allResponseErrorProperties.put(entry.getKey(),entry.getValue()));
        response.getError().setProperties(allResponseErrorProperties);

        return response;
    }

    private Map<String, Object> getFieldResponseErrorProperties(Throwable exception) {
        Map<String, Object> result = new HashMap<>();
        ReflectionUtils.doWithFields(exception.getClass(), field -> {
            if (field.isAnnotationPresent(ResponseErrorProperty.class)) {
                try {
                    field.setAccessible(true);
                    Object value = field.get(exception);
                    if (value != null || field.getAnnotation(ResponseErrorProperty.class).includeIfNull()) {
                        result.put(getPropertyName(field), value);
                    }
                } catch (IllegalAccessException e) {
                    LOGGER.error(String.format("Unable to use field result of field %s.%s", exception.getClass().getName(), field.getName()));
                }
            }
        });
        return result;
    }

    private Map<String, Object> getMethodResponseErrorProperties(Throwable exception) {
        Map<String, Object> result = new HashMap<>();
        Class<? extends Throwable> exceptionClass = exception.getClass();
        ReflectionUtils.doWithMethods(exceptionClass, method -> {
            if (method.isAnnotationPresent(ResponseErrorProperty.class)
                    && method.getReturnType() != Void.TYPE
                    && method.getParameterCount() == 0) {
                try {
                    method.setAccessible(true);

                    Object value = method.invoke(exception);
                    if (value != null || method.getAnnotation(ResponseErrorProperty.class).includeIfNull()) {
                        result.put(getPropertyName(exceptionClass, method),
                                   value);
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    LOGGER.error(String.format("Unable to use method result of method %s.%s", exceptionClass.getName(), method.getName()));
                }
            }
        });
        return result;
    }

    private String getPropertyName(Field field) {
        ResponseErrorProperty annotation = AnnotationUtils.getAnnotation(field, ResponseErrorProperty.class);
        assert annotation != null;
        if (StringUtils.hasText(annotation.value())) {
            return annotation.value();
        }

        return field.getName();
    }

    private String getPropertyName(Class<? extends Throwable> exceptionClass, Method method) {
        ResponseErrorProperty annotation = AnnotationUtils.getAnnotation(method, ResponseErrorProperty.class);
        assert annotation != null;
        if (StringUtils.hasText(annotation.value())) {
            return annotation.value();
        }

        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(exceptionClass);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                if (propertyDescriptor.getReadMethod().equals(method)) {
                    return propertyDescriptor.getName();
                }
            }
        } catch (IntrospectionException e) {
            //ignore
        }

        return method.getName();
    }
}
