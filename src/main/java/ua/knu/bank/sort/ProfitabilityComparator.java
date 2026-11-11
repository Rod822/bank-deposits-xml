package ua.knu.bank.sort;

import java.util.Comparator;

import ua.knu.bank.model.Deposit;

// спочатку вклади з більшим річним відсотком,
// при однаковому відсотку за назвою банку
public class ProfitabilityComparator implements Comparator<Deposit> {

    @Override
    public int compare(Deposit first, Deposit second) {
        int result = Double.compare(second.getProfitability(),
                first.getProfitability());
        if (result == 0) {
            result = first.getName().compareTo(second.getName());
        }
        return result;
    }
}
