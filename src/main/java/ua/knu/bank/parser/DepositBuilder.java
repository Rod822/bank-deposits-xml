package ua.knu.bank.parser;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import ua.knu.bank.model.Deposit;
import ua.knu.bank.model.DepositType;

// спільна обробка для всіх парсерів: парсер тільки повідомляє,
// який елемент прочитано, а вклад збирається тут
public class DepositBuilder {

    public static final String DEPOSIT = "deposit";
    public static final String ACCOUNT_ID = "account-id";

    private static final Logger LOGGER =
            LogManager.getLogger(DepositBuilder.class);

    private final List<Deposit> deposits = new ArrayList<>();
    private Deposit current;

    public void startDeposit(String accountId) {
        current = new Deposit();
        current.setAccountId(accountId);
    }

    public void setField(String element, String value) {
        switch (element) {
            case "name" -> current.setName(value);
            case "country" -> current.setCountry(value);
            case "type" -> current.setType(DepositType.fromXmlValue(value));
            case "depositor" -> current.setDepositor(value);
            case "amount-on-deposit" ->
                    current.setAmountOnDeposit(Double.parseDouble(value));
            case "profitability" ->
                    current.setProfitability(Double.parseDouble(value));
            case "time-constraints" ->
                    current.setTimeConstraints(Integer.parseInt(value));
            default -> LOGGER.debug("Element {} is skipped", element);
        }
    }

    public void endDeposit() {
        deposits.add(current);
        current = null;
    }

    public List<Deposit> getDeposits() {
        return deposits;
    }
}
