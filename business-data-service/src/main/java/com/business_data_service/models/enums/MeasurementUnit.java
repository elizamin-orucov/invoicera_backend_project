package com.business_data_service.models.enums;

public enum MeasurementUnit {
    KILOGRAM("kg"),
    KILOMETER("km"),
    ƏDƏD("ədəd"),
    CUBIC_METER("m³"),
    METER("m"),
    SQUARE_METER("m²"),
    LITER("l");

    private final String label;

    MeasurementUnit(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static MeasurementUnit fromLabel(String label) {
        for (MeasurementUnit unit : MeasurementUnit.values()) {
            if (unit.label.equalsIgnoreCase(label)) {
                return unit;
            }
        }
        throw new IllegalArgumentException("Bilinməyən ölçü vahidi: " + label);
    }
}

