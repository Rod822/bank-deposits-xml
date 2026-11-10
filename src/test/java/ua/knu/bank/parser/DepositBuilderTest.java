package ua.knu.bank.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import ua.knu.bank.model.Deposit;
import ua.knu.bank.model.DepositType;

class DepositBuilderTest {

    private final DepositBuilder builder = new DepositBuilder();

    @Test
    void buildsDepositFromElements() {
        builder.startDeposit("UA577463706433044529403940290");
        builder.setField("name", "ПриватБанк");
        builder.setField("country", "Україна");
        builder.setField("type", "term");
        builder.setField("depositor", "Олена Коваленко");
        builder.setField("amount-on-deposit", "150000.00");
        builder.setField("profitability", "13.5");
        builder.setField("time-constraints", "12");
        builder.endDeposit();

        List<Deposit> deposits = builder.getDeposits();
        assertEquals(1, deposits.size());
        Deposit deposit = deposits.get(0);
        assertEquals("UA577463706433044529403940290", deposit.getAccountId());
        assertEquals("ПриватБанк", deposit.getName());
        assertEquals("Україна", deposit.getCountry());
        assertEquals(DepositType.TERM, deposit.getType());
        assertEquals("Олена Коваленко", deposit.getDepositor());
        assertEquals(150000.0, deposit.getAmountOnDeposit());
        assertEquals(13.5, deposit.getProfitability());
        assertEquals(12, deposit.getTimeConstraints());
    }

    @Test
    void depositsAreKeptInFileOrder() {
        builder.startDeposit("UA000000000000000000000000001");
        builder.endDeposit();
        builder.startDeposit("UA000000000000000000000000002");
        builder.endDeposit();

        List<Deposit> deposits = builder.getDeposits();
        assertEquals("UA000000000000000000000000001",
                deposits.get(0).getAccountId());
        assertEquals("UA000000000000000000000000002",
                deposits.get(1).getAccountId());
    }

    @Test
    void otherElementsAreSkipped() {
        builder.setField("bank", "");

        assertTrue(builder.getDeposits().isEmpty());
    }
}
