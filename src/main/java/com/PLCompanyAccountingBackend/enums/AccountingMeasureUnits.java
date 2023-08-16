package com.PLCompanyAccountingBackend.enums;

public enum AccountingMeasureUnits {


    GRAM("GRAM"), DECAGRAM("DEKAGRAM"), KILOGRAM("KILOGRAM"), TON("TONA"),

    MILLIMETER("MILIMETR"), CENTIMETER("CENTYMETR"), METER("METR"), KILOMETER("KILOMETR"),

    MILLILITER("MILILITR"), LITER("LITR"), CUBIC_DECIMETER("DECYMETR SZEŚCIENNY"), CUBIC_METER("METR SZEŚCIENNY"),

    SQUARE_MILLIMETER("MILIMETR KWADRATOWY"), SQUARE_CENTIMETER("CENTYMETR KWADRATOWY"), SQUARE_DECIMETER("DECYMETR_KWADRATOWY"), SQUARE_METER("METR_KWADRATOWY"),

    UNIT("SZTUKA"), TEN_UNITS("DZIESIEĆ SZTYK"), HUNDRED_UNITS("STO SZTUK"), THOUSAND_UNITS("TYSIAC_SZTUK");

    private final String displayName;

    AccountingMeasureUnits(final String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }

}
