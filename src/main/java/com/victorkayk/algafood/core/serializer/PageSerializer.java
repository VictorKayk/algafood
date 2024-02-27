package com.victorkayk.algafood.core.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.data.domain.Page;

import java.io.IOException;

public class PageSerializer extends JsonSerializer<Page<?>> {
    @Override
    public void serialize(Page<?> page, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();

        gen.writeObjectField("content", page.getContent());
        gen.writeNumberField("currentPage", page.getPageable().getPageNumber());
        gen.writeNumberField("itemsPerPage", page.getSize());
        gen.writeNumberField("totalPages", page.getTotalPages());
        gen.writeNumberField("totalItems", page.getTotalElements());

        gen.writeEndObject();
    }
}
