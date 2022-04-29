package com.baeldung.crud.entities.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;

@Converter
public class DateToLongConverter implements AttributeConverter<LocalDate, Long> {

    @Override
    public Long convertToDatabaseColumn(LocalDate date) {
        return date == null ? 0L : date.atStartOfDay(ZoneId.systemDefault()).toEpochSecond();
    }

    @Override
    public LocalDate convertToEntityAttribute(Long time) {
        return time == 0L ? null : Instant.ofEpochSecond(time).atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
