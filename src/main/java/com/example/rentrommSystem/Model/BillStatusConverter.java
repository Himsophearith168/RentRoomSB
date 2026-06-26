package com.example.rentrommSystem.Model;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter(autoApply = true)
public class BillStatusConverter implements AttributeConverter<BillStatus, String> {

    @Override
    public String convertToDatabaseColumn(BillStatus attribute) {
        if (attribute == null) {
            return null;
        }
        return switch (attribute) {
            case Draft -> "Draft";
            case Unpaid -> "Unpaid";
            case Partially_Paid -> "Partially Paid";
            case Paid -> "Paid";
        };
    }

    @Override
    public BillStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }
        return switch (dbData) {
            case "Draft" -> BillStatus.Draft;
            case "Unpaid" -> BillStatus.Unpaid;
            case "Partially Paid" -> BillStatus.Partially_Paid;
            case "Paid" -> BillStatus.Paid;
            default -> throw new IllegalArgumentException("Unknown database value for BillStatus: " + dbData);
        };
    }
}
