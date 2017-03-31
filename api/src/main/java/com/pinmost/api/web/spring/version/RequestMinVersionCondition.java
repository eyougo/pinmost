package com.pinmost.api.web.spring.version;

import org.springframework.http.MediaType;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.accept.ContentNegotiationManager;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.servlet.mvc.condition.AbstractRequestCondition;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * User: mei
 * Date: 3/5/16
 * Time: 22:21
 */
public class RequestMinVersionCondition extends AbstractRequestCondition<RequestMinVersionCondition> {

    private int version;

    private ContentNegotiationManager contentNegotiationManager;

    public RequestMinVersionCondition(int version, ContentNegotiationManager contentNegotiationManager) {
        this.version = version;
        this.contentNegotiationManager = contentNegotiationManager;
    }

    @Override
    protected Collection<?> getContent() {
        return Collections.singleton(this.version) ;
    }

    @Override
    protected String getToStringInfix() {
        return " ";
    }

    @Override
    public RequestMinVersionCondition combine(RequestMinVersionCondition other) {
        return this.version >= other.version? this : other;
    }

    @Override
    public RequestMinVersionCondition getMatchingCondition(HttpServletRequest request) {
        if (this.version > 0){
            return this;
        }
        return null;
    }

    @Override
    public int compareTo(RequestMinVersionCondition other, HttpServletRequest request) {
        if (this.version == other.version){
            return 0;
        }
        try {
            List<MediaType> mediaTypes = this.contentNegotiationManager.resolveMediaTypes(new ServletWebRequest(request));
            List<MediaType> acceptedMediaTypes = mediaTypes.isEmpty() ? Collections.singletonList(MediaType.ALL) : mediaTypes;
            int requestVersion = 1;
            for (MediaType acceptedMediaType : acceptedMediaTypes) {
                String version = acceptedMediaType.getParameter("version");
                if (version != null){
                    try {
                        requestVersion = Integer.parseInt(version);
                        break;
                    } catch (NumberFormatException e) {
                    }
                }
            }
            if (requestVersion >= this.version && requestVersion >= other.version){
                return this.version > other.version ? -1 : 1;
            } else if (requestVersion < this.version && requestVersion < other.version){
                return 0;
            } else {
                return this.version < other.version ? -1 : 1;
            }
        } catch (HttpMediaTypeNotAcceptableException ex) {

            // should never happen
            throw new IllegalStateException("Cannot compare without having any requested media types", ex);
        }
    }
}
