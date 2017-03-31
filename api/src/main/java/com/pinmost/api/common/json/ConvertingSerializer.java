package com.pinmost.api.common.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;

import java.io.IOException;

final class ConvertingSerializer extends JsonSerializer<Object> {

    private final ConversionService conversionService;

    private final TypeDescriptor sourceType;

    public ConvertingSerializer(ConversionService conversionService, TypeDescriptor sourceType) {
        this.conversionService = conversionService;
        this.sourceType = sourceType;
    }

    @Override
    public void serialize(Object value, JsonGenerator jgen,
                          SerializerProvider provider) throws IOException {
        jgen.writeString((String) this.conversionService.convert(value, sourceType, TypeDescriptor.valueOf(String.class)));
    }

}
