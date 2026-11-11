package ua.knu.bank.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

import ua.knu.bank.model.Deposit;

class SameResultTest {

    private static final String XML = "src/main/resources/deposits.xml";

    private final ParserFactory factory = new ParserFactory();

    @Test
    void allParsersGiveSameDeposits() {
        List<Deposit> sax = factory.createParser(ParserType.SAX).parse(XML);
        List<Deposit> dom = factory.createParser(ParserType.DOM).parse(XML);
        List<Deposit> stax =
                factory.createParser(ParserType.STAX).parse(XML);

        assertEquals(16, sax.size());
        assertEquals(sax, dom);
        assertEquals(sax, stax);
    }
}
