package com.victorkayk.algafood.domain.enums.converter;

import com.victorkayk.algafood.domain.enums.StatusOrderEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class StatusOrderEnumConverter implements AttributeConverter<StatusOrderEnum, String> {
    @Override
    public String convertToDatabaseColumn(StatusOrderEnum migrationStatus) {
        if (migrationStatus == null) {
            return null;
        }
        return migrationStatus.getValue();
    }

    @Override
    public StatusOrderEnum convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        return Stream.of(StatusOrderEnum.values())
                .filter((c) -> StringUtils.equalsAnyIgnoreCase(c.getValue(), value))
                .findFirst()
                .orElse(null);
    }
}