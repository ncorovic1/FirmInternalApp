package com.vacations.models;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
 
@Converter
public class StatusConverter implements AttributeConverter<Status, String> {

	@Override
    public String convertToDatabaseColumn(Status attribute) {
        switch (attribute) {
            case PENDING:
                return "P";
            case APPROVED:
                return "A";
            case REJECTED:
                return "R";
            default:
                throw new IllegalArgumentException("Unknown" + attribute);
        }
    }
 
    @Override
    public Status convertToEntityAttribute(String columnValue) {
        switch (columnValue) {
            case "P":
                return Status.PENDING;
            case "A":
                return Status.APPROVED;
            case "R":
                return Status.REJECTED;
            default:
                throw new IllegalArgumentException("Unknown" + columnValue);
        }
    }
}