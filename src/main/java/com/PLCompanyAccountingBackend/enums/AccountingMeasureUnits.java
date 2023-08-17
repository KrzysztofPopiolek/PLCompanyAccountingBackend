package com.PLCompanyAccountingBackend.enums;

public enum AccountingMeasureUnits {


    GRAM("GRAM", AccountingMeasureUnitsType.WEIGHT), DECAGRAM("DEKAGRAM", AccountingMeasureUnitsType.WEIGHT),
    KILOGRAM("KILOGRAM", AccountingMeasureUnitsType.WEIGHT), TON("TONA", AccountingMeasureUnitsType.WEIGHT),

    MILLIMETRE("MILIMETR", AccountingMeasureUnitsType.LENGTH), CENTIMETER("CENTYMETR", AccountingMeasureUnitsType.LENGTH),
    METRE("METR", AccountingMeasureUnitsType.LENGTH), KILOMETRE("KILOMETR", AccountingMeasureUnitsType.LENGTH),

    MILLILITRE("MILILITR", AccountingMeasureUnitsType.VOLUME), LITRE("LITR", AccountingMeasureUnitsType.VOLUME),
    CUBIC_DECIMETRE("DECYMETR SZEŚCIENNY", AccountingMeasureUnitsType.VOLUME), CUBIC_METRE("METR SZEŚCIENNY", AccountingMeasureUnitsType.VOLUME),

    SQUARE_MILLIMETRE("MILIMETR KWADRATOWY", AccountingMeasureUnitsType.SURFACE_AREA), SQUARE_CENTIMETRE("CENTYMETR KWADRATOWY", AccountingMeasureUnitsType.SURFACE_AREA),
    SQUARE_DECIMETRE("DECYMETR_KWADRATOWY", AccountingMeasureUnitsType.SURFACE_AREA), SQUARE_METRE("METR_KWADRATOWY", AccountingMeasureUnitsType.SURFACE_AREA),

    UNIT("SZTUKA", AccountingMeasureUnitsType.UNIT_QUANTITY), TEN_UNITS("DZIESIEĆ SZTYK", AccountingMeasureUnitsType.UNIT_QUANTITY),
    HUNDRED_UNITS("STO SZTUK", AccountingMeasureUnitsType.UNIT_QUANTITY), THOUSAND_UNITS("TYSIAC_SZTUK", AccountingMeasureUnitsType.UNIT_QUANTITY);

    private final String displayName;
    private final AccountingMeasureUnitsType unitType;

    AccountingMeasureUnits(final String displayName, AccountingMeasureUnitsType unitType) {
        this.displayName = displayName;
        this.unitType = unitType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getTypeName() {
        return unitType.getDisplayTypeName();
    }

}
