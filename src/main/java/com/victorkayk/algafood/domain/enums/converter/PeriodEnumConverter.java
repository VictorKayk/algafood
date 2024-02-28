package com.victorkayk.algafood.domain.enums.converter;

import com.victorkayk.algafood.domain.enums.PeriodEnum;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.apache.commons.lang3.StringUtils;

import java.util.stream.Stream;

@Converter(autoApply = true)
public class PeriodEnumConverter implements AttributeConverter<PeriodEnum, String> {
    @Override
    public String convertToDatabaseColumn(PeriodEnum migrationStatus) {
        if (migrationStatus == null) {
            return null;
        }
        return migrationStatus.getValue();
    }

    @Override
    public PeriodEnum convertToEntityAttribute(String value) {
        if (value == null) {
            return null;
        }

        return Stream.of(PeriodEnum.values())
                .filter((c) -> StringUtils.equalsAnyIgnoreCase(c.getValue(), value))
                .findFirst()
                .orElse(null);
    }
}