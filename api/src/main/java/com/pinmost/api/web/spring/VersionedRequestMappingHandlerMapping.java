package com.pinmost.api.web.spring;

import com.pinmost.api.web.spring.version.RequestMinVersion;
import com.pinmost.api.web.spring.version.RequestMinVersionCondition;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.servlet.mvc.condition.RequestCondition;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.reflect.Method;

/**
 * User: mei
 * Date: 3/5/16
 * Time: 21:56
 */
public class VersionedRequestMappingHandlerMapping extends RequestMappingHandlerMapping {
    @Override
    protected RequestCondition<?> getCustomMethodCondition(Method method) {
        RequestMinVersion methodAnnotation = AnnotationUtils.findAnnotation(method, RequestMinVersion.class);
        return createCondition(methodAnnotation);
    }

    private RequestCondition<?> createCondition(RequestMinVersion annotation) {
        if (annotation == null){
            return new RequestMinVersionCondition(RequestMinVersion.DEFAULT, this.getContentNegotiationManager());
        }
        return new RequestMinVersionCondition(annotation.value(), this.getContentNegotiationManager());
    }
}


