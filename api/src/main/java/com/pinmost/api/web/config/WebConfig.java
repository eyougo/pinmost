package com.pinmost.api.web.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pinmost.api.common.json.ConversionServiceAwareObjectMapper;
import com.pinmost.api.web.spring.VersionedRequestMappingHandlerMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.format.support.FormattingConversionServiceFactoryBean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DelegatingWebMvcConfiguration;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.nio.charset.Charset;
import java.util.List;

/**
 * User: mei
 * Date: 3/6/16
 * Time: 20:58
 */
@Configuration
@ImportResource("classpath:springDispatcher.xml")
public class WebConfig extends DelegatingWebMvcConfiguration {

    @Override
    @Bean
    public RequestMappingHandlerMapping requestMappingHandlerMapping() {
        RequestMappingHandlerMapping handlerMapping = new VersionedRequestMappingHandlerMapping();
        handlerMapping.setOrder(0);
        handlerMapping.setInterceptors(getInterceptors());
        handlerMapping.setContentNegotiationManager(mvcContentNegotiationManager());
        return handlerMapping;
    }

    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        StringHttpMessageConverter stringHttpMessageConverter = new StringHttpMessageConverter(Charset.forName("utf-8"));
        stringHttpMessageConverter.setWriteAcceptCharset(false);
        converters.add(stringHttpMessageConverter);

        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        FormattingConversionServiceFactoryBean factoryBean = new FormattingConversionServiceFactoryBean();
        factoryBean.afterPropertiesSet();
        ObjectMapper objectMapper = new ConversionServiceAwareObjectMapper(factoryBean.getObject());
        mappingJackson2HttpMessageConverter.setObjectMapper(objectMapper);
        converters.add(mappingJackson2HttpMessageConverter);

        super.addDefaultHttpMessageConverters(converters);
    }
}
