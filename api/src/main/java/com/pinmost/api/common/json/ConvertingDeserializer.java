package com.pinmost.api.common.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.io.IOException;

final class ConvertingDeserializer extends JsonDeserializer<Object> {

    private final ConversionService conversionService;

    private final TypeDescriptor targetType;

    public ConvertingDeserializer(ConversionService conversionService, TypeDescriptor targetType) {
        this.conversionService = conversionService;
        this.targetType = targetType;
    }

    @Override
    public Object deserialize(JsonParser jp, DeserializationContext ctxt)
            throws IOException {
        Object value = jp.getText();
        TypeDescriptor sourceType = TypeDescriptor.forObject(value);
        return this.conversionService.convert(value, sourceType, targetType);
    }

}
