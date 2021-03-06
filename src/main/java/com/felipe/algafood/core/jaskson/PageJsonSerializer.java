package com.felipe.algafood.core.jaskson;

import java.io.IOException;

import org.springframework.boot.jackson.JsonComponent;
import org.springframework.data.domain.Page;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class PageJsonSerializer extends JsonSerializer<Page<?>>{

	@Override
	public void serialize(Page<?> value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
		gen.writeStartObject();
		gen.writeObjectField("content", value.getContent());
		gen.writeObjectField("size", value.getSize());
		gen.writeObjectField("totalElements", value.getTotalElements());
		gen.writeObjectField("totalPages", value.getTotalPages());
		gen.writeObjectField("number", value.getNumber());
		gen.writeEndObject();
	}

}
