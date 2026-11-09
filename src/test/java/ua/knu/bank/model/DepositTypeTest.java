package ua.knu.bank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class DepositTypeTest {

    @Test
    void everyTypeIsFoundByItsXmlValue() {
        for (DepositType type : DepositType.values()) {
            assertEquals(type, DepositType.fromXmlValue(type.getXmlValue()));
        }
    }

    @Test
    void xmlValueIsWrittenWithHyphen() {
        assertEquals("on-demand", DepositType.ON_DEMAND.getXmlValue());
    }

    @Test
    void unknownXmlValueIsRejected() {
        assertThrows(IllegalArgumentException.class,
                () -> DepositType.fromXmlValue("credit"));
    }
}
