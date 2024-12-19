package com.featurerich.reservation;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import org.springframework.data.jdbc.core.mapping.JdbcValue;

import java.sql.JDBCType;
import java.util.Map;

@Configuration
@RequiredArgsConstructor
public class ReservationConfig {

    private final ObjectMapper objectMapper;

    @WritingConverter
    public class StateToIntegerConverter implements Converter<Map<String, String>, JdbcValue> {

        @Override
        public JdbcValue convert(Map<String, String> source) {
            try {
                return JdbcValue.of(objectMapper.writeValueAsString(source), JDBCType.VARCHAR);
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
