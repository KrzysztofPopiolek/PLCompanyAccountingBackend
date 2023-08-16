package com.PLCompanyAccountingBackend.enums;

public enum AccountingMeasureUnitsType {

    WEIGHT("WAGA"), LENGTH("DŁUGOŚĆ"), VOLUME("OBJĘTOŚĆ"),
    SURFACE_AREA("POWIERZCHNIA"), UNIT_QUANTITY("SZTUKA/ILOŚĆ");
    private final String displayTypeName;

    AccountingMeasureUnitsType(String displayTypeName) {
        this.displayTypeName = displayTypeName;
    }

    public String getDisplayTypeName() {
        return displayTypeName;
    }

}
