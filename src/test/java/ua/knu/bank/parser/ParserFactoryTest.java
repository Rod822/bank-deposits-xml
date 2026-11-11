package ua.knu.bank.parser;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

class ParserFactoryTest {

    private final ParserFactory factory = new ParserFactory();

    @Test
    void createsSaxParser() {
        assertInstanceOf(SaxDepositParser.class,
                factory.createParser(ParserType.SAX));
    }

    @Test
    void createsDomParser() {
        assertInstanceOf(DomDepositParser.class,
                factory.createParser(ParserType.DOM));
    }

    @Test
    void createsStaxParser() {
        assertInstanceOf(StaxDepositParser.class,
                factory.createParser(ParserType.STAX));
    }
}
