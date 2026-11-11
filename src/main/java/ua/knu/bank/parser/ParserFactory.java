package ua.knu.bank.parser;

// Factory Method: за типом повертає потрібний парсер
public class ParserFactory {

    public DepositParser createParser(ParserType type) {
        return switch (type) {
            case SAX -> new SaxDepositParser();
            case DOM -> new DomDepositParser();
            case STAX -> new StaxDepositParser();
        };
    }
}
