package com.acmecontracting.subcontractors.converters;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import java.util.Date;

@Converter
public class LongToDateConverter implements AttributeConverter<Long, Date> {
    @Override
    public Date convertToDatabaseColumn(Long value) {
        return value != null ? new Date(value) : null;
    }

    @Override
    public Long convertToEntityAttribute(Date value) {
        return value != null ? value.getTime() : null;
    }
}
