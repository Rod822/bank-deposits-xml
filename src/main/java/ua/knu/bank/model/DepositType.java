package ua.knu.bank.model;

public enum DepositType {
    ON_DEMAND("on-demand"),
    TERM("term"),
    SETTLEMENT("settlement"),
    ACCUMULATIVE("accumulative"),
    SAVINGS("savings"),
    METAL("metal");

    private final String xmlValue;

    DepositType(String xmlValue) {
        this.xmlValue = xmlValue;
    }

    public String getXmlValue() {
        return xmlValue;
    }

    // в XML тип записаний так, як у схемі, наприклад "on-demand"
    public static DepositType fromXmlValue(String value) {
        for (DepositType type : values()) {
            if (type.xmlValue.equals(value)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown deposit type: " + value);
    }
}
