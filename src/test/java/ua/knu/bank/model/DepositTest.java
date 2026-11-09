package ua.knu.bank.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class DepositTest {

    private Deposit createDeposit() {
        Deposit deposit = new Deposit();
        deposit.setAccountId("UA213223130000026007233566001");
        deposit.setName("ПриватБанк");
        deposit.setCountry("Україна");
        deposit.setType(DepositType.TERM);
        deposit.setDepositor("Іваненко Петро");
        deposit.setAmountOnDeposit(50000);
        deposit.setProfitability(13.5);
        deposit.setTimeConstraints(12);
        return deposit;
    }

    @Test
    void gettersReturnSetValues() {
        Deposit deposit = createDeposit();

        assertEquals("UA213223130000026007233566001", deposit.getAccountId());
        assertEquals("ПриватБанк", deposit.getName());
        assertEquals("Україна", deposit.getCountry());
        assertEquals(DepositType.TERM, deposit.getType());
        assertEquals("Іваненко Петро", deposit.getDepositor());
        assertEquals(50000, deposit.getAmountOnDeposit());
        assertEquals(13.5, deposit.getProfitability());
        assertEquals(12, deposit.getTimeConstraints());
    }

    @Test
    void depositsWithSameDataAreEqual() {
        Deposit first = createDeposit();
        Deposit second = createDeposit();

        assertEquals(first, first);
        assertEquals(first, second);
        assertEquals(first.hashCode(), second.hashCode());
    }

    @Test
    void depositsWithDifferentDataAreNotEqual() {
        Deposit other = createDeposit();
        other.setAccountId("UA903052992990004149123456789");

        assertNotEquals(createDeposit(), other);
        assertNotEquals(createDeposit(), null);
        assertNotEquals(createDeposit(), "ПриватБанк");
    }

    @Test
    void toStringContainsDepositData() {
        String text = createDeposit().toString();

        assertTrue(text.contains("ПриватБанк"));
        assertTrue(text.contains("TERM"));
        assertTrue(text.contains("13.5"));
    }
}
