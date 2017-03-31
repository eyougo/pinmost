package com.pinmost.api.common.json;

import com.fasterxml.jackson.databind.AnnotationIntrospector;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import org.springframework.core.convert.ConversionService;
import org.springframework.format.support.DefaultFormattingConversionService;

/**
 * A custom Jackson ObjectMapper that installs JSON serialization/deserialization support
 * for properties annotated with Spring format annotations such as @DateTimeFormat and @NumberFormat.
 *
 * @since 0.8.0
 *
 */
public class ConversionServiceAwareObjectMapper extends ObjectMapper {

    public ConversionServiceAwareObjectMapper(){
        this(new DefaultFormattingConversionService());
    }

    public ConversionServiceAwareObjectMapper(ConversionService conversionService) {
        super();
        AnnotationIntrospector introspector = AnnotationIntrospector.pair(new FormatAnnotationIntrospector(conversionService), DEFAULT_ANNOTATION_INTROSPECTOR);

        DeserializationConfig dconfig = super.getDeserializationConfig().with(introspector);
        SerializationConfig sconfig = super.getSerializationConfig().with(introspector);
        setConfig(sconfig);
        setConfig(dconfig);
    }
}
