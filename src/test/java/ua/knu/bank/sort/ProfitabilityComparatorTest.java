package ua.knu.bank.sort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import ua.knu.bank.model.Deposit;
import ua.knu.bank.parser.SaxDepositParser;

class ProfitabilityComparatorTest {

    private final ProfitabilityComparator comparator =
            new ProfitabilityComparator();

    private Deposit createDeposit(String name, double profitability) {
        Deposit deposit = new Deposit();
        deposit.setName(name);
        deposit.setProfitability(profitability);
        return deposit;
    }

    @Test
    void biggerProfitabilityGoesFirst() {
        Deposit big = createDeposit("ПУМБ", 14.5);
        Deposit small = createDeposit("ПриватБанк", 13.5);

        assertTrue(comparator.compare(big, small) < 0);
        assertTrue(comparator.compare(small, big) > 0);
    }

    @Test
    void sameProfitabilityIsSortedByBankName() {
        Deposit sense = createDeposit("Sense Bank", 2.0);
        Deposit privat = createDeposit("ПриватБанк", 2.0);

        assertTrue(comparator.compare(sense, privat) < 0);
        assertEquals(0, comparator.compare(sense, sense));
    }

    @Test
    void sortsDepositsFromFile() {
        List<Deposit> deposits = new SaxDepositParser()
                .parse("src/main/resources/deposits.xml");

        deposits.sort(comparator);

        assertEquals(16.0, deposits.get(0).getProfitability());
        assertEquals("А-Банк", deposits.get(0).getName());
        assertEquals(0.1, deposits.get(15).getProfitability());
    }
}
