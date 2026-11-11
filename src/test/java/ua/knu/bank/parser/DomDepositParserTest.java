package ua.knu.bank.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.List;

import org.junit.jupiter.api.Test;

import ua.knu.bank.model.Deposit;
import ua.knu.bank.model.DepositType;

class DomDepositParserTest {

    private static final String XML = "src/main/resources/deposits.xml";

    private final DepositParser parser = new DomDepositParser();

    @Test
    void readsAllDepositsFromFile() {
        assertEquals(16, parser.parse(XML).size());
    }

    @Test
    void readsFirstDepositCorrectly() {
        List<Deposit> deposits = parser.parse(XML);

        Deposit first = deposits.get(0);
        assertEquals("UA577463706433044529403940290", first.getAccountId());
        assertEquals("ПриватБанк", first.getName());
        assertEquals("Україна", first.getCountry());
        assertEquals(DepositType.TERM, first.getType());
        assertEquals("Олена Коваленко", first.getDepositor());
        assertEquals(150000.0, first.getAmountOnDeposit());
        assertEquals(13.5, first.getProfitability());
        assertEquals(12, first.getTimeConstraints());
    }

    @Test
    void brokenFileGivesParserException() {
        assertThrows(ParserException.class,
                () -> parser.parse("src/test/resources/broken.xml"));
    }

    @Test
    void missingFileGivesParserException() {
        assertThrows(ParserException.class,
                () -> parser.parse("src/test/resources/no-such-file.xml"));
    }
}
