package com.simple.serviceDeskApplication.model;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.Objects;

public enum ProcessStatus {
    OPEN,PROCESS,CLOSE;

    @Converter
    public static class EnumConverter implements AttributeConverter<ProcessStatus,String> {
        @Override
        public String convertToDatabaseColumn (ProcessStatus status) {
            return status.toString();
        }

        @Override
        public ProcessStatus convertToEntityAttribute(String dbData) {
            return !Objects.isNull(dbData) ? ProcessStatus.valueOf(dbData) : null;
        }
    }
}
