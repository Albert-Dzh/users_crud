package com.users.crud.entities.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Converter
public class DateToStringConverter implements AttributeConverter<LocalDate, String> {
    @Override
    public String convertToDatabaseColumn(LocalDate date) {
        return date == null ? "" : String.valueOf(date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond());
    }

    @Override
    public LocalDate convertToEntityAttribute(String time) {
        return time.isEmpty() ? null : Instant.ofEpochSecond(Integer.parseInt(time)).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
