package ua.knu.bank.parser;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.knu.bank.model.Deposit;

public abstract class DepositParser {

    private static final Logger LOGGER =
            LogManager.getLogger(DepositParser.class);

    // однаково для SAX, DOM і StAX, різниться тільки readFile
    public List<Deposit> parse(String xmlPath) {
        DepositBuilder builder = new DepositBuilder();
        try {
            readFile(xmlPath, builder);
        } catch (Exception e) {
            LOGGER.error("Cannot parse {}: {}", xmlPath, e.getMessage());
            throw new ParserException("Cannot parse " + xmlPath, e);
        }
        LOGGER.info("{} read {} deposits", getClass().getSimpleName(),
                builder.getDeposits().size());
        return builder.getDeposits();
    }

    protected abstract void readFile(String xmlPath, DepositBuilder builder)
            throws Exception;
}
