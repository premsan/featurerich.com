package com.featurerich.scheduled;

import java.sql.JDBCType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;
import org.springframework.data.jdbc.repository.config.AbstractJdbcConfiguration;

@Configuration
@RequiredArgsConstructor
public class ScheduledJobConfigurationAttributesConverter extends AbstractJdbcConfiguration {

    private final ObjectMapper objectMapper;

    @Override
    protected List<?> userConverters() {
        return Arrays.asList(new HashMapConverter(objectMapper), new HashMapConverter2(objectMapper));
    }

    @RequiredArgsConstructor
    public static class HashMapConverter implements Converter<Map<String, String>, JdbcValue> {

        private final ObjectMapper objectMapper;

        @Override
        public JdbcValue convert(Map<String, String> source) {
            try {
                return JdbcValue.of(objectMapper.writeValueAsString(source), JDBCType.VARCHAR);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @RequiredArgsConstructor
    public static class HashMapConverter2 implements Converter<String, Map<String, String>> {

        private final ObjectMapper objectMapper;

        @Override
        public HashMap<String, String> convert(String source) {
            try {
                return objectMapper.readValue(source, new TypeReference<>() {
                });
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
