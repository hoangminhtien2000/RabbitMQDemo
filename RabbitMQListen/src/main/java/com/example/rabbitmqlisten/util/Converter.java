package com.example.rabbitmqlisten.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.MappingException;
import org.modelmapper.ModelMapper;

import java.io.IOException;

public class Converter {

    private static final ObjectMapper mapper = new ObjectMapper();
    private static final ModelMapper modelMapper = new ModelMapper();

    public static String toJSON(final Object object) {
        try {
            return mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException("Cannot convert " + object + " to json", e);
        }
    }

    public static <T> T toObject(String json, Class<T> clazz) {
        try {
            return mapper.readValue(json, clazz);
        } catch (IOException e) {
            throw new IllegalArgumentException("Cannot convert " + json + " to object type " + clazz.getSimpleName(), e);
        }
    }

    public static <T, U> U toObject(T from, Class<U> to) {
        try {
            return modelMapper.map(from, to);
        } catch (MappingException e) {
            throw new IllegalArgumentException("Cannot convert " + from.getClass().getSimpleName() + " to object type " + to.getSimpleName(), e);
        }
    }
}