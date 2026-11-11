package ua.knu.bank;

import java.util.List;
import java.util.Locale;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.knu.bank.model.Deposit;
import ua.knu.bank.parser.ParserFactory;
import ua.knu.bank.parser.ParserType;
import ua.knu.bank.sort.ProfitabilityComparator;
import ua.knu.bank.transform.XslTransformer;
import ua.knu.bank.validation.XmlValidator;

public class BankApplication {

    private static final Logger LOGGER =
            LogManager.getLogger(BankApplication.class);

    private final String xmlPath;
    private final String xsdPath;
    private final String xslPath;
    private final String outputPath;

    public BankApplication(String xmlPath, String xsdPath, String xslPath,
            String outputPath) {
        this.xmlPath = xmlPath;
        this.xsdPath = xsdPath;
        this.xslPath = xslPath;
        this.outputPath = outputPath;
    }

    // аргумент sax, dom або stax вибирає парсер, без аргументу SAX
    public static void main(String[] args) {
        ParserType parserType = ParserType.SAX;
        if (args.length > 0) {
            try {
                parserType = ParserType.valueOf(
                        args[0].toUpperCase(Locale.ROOT));
            } catch (IllegalArgumentException e) {
                LOGGER.error("Unknown parser {}, SAX is used", args[0]);
            }
        }
        BankApplication application = new BankApplication(
                "src/main/resources/deposits.xml",
                "src/main/resources/deposits.xsd",
                "src/main/resources/group-by-type.xsl",
                "deposits-by-type.xml");
        application.run(parserType);
    }

    public boolean run(ParserType parserType) {
        XmlValidator validator = new XmlValidator();
        if (!validator.isValid(xmlPath, xsdPath)) {
            LOGGER.error("Program stopped, {} is not valid", xmlPath);
            return false;
        }

        ParserFactory factory = new ParserFactory();
        List<Deposit> deposits =
                factory.createParser(parserType).parse(xmlPath);

        deposits.sort(new ProfitabilityComparator());
        LOGGER.info("Deposits sorted by profitability:");
        for (Deposit deposit : deposits) {
            LOGGER.info("{}", deposit);
        }

        XslTransformer transformer = new XslTransformer();
        return transformer.transform(xmlPath, xslPath, outputPath);
    }
}
