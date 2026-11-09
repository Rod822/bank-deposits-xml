package ua.knu.bank.model;

import java.util.Objects;

public class Deposit {

    private String accountId;
    private String name;
    private String country;
    private DepositType type;
    private String depositor;
    private double amountOnDeposit;
    private double profitability;
    private int timeConstraints;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public DepositType getType() {
        return type;
    }

    public void setType(DepositType type) {
        this.type = type;
    }

    public String getDepositor() {
        return depositor;
    }

    public void setDepositor(String depositor) {
        this.depositor = depositor;
    }

    public double getAmountOnDeposit() {
        return amountOnDeposit;
    }

    public void setAmountOnDeposit(double amountOnDeposit) {
        this.amountOnDeposit = amountOnDeposit;
    }

    public double getProfitability() {
        return profitability;
    }

    public void setProfitability(double profitability) {
        this.profitability = profitability;
    }

    public int getTimeConstraints() {
        return timeConstraints;
    }

    public void setTimeConstraints(int timeConstraints) {
        this.timeConstraints = timeConstraints;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Deposit deposit = (Deposit) o;
        return Double.compare(amountOnDeposit, deposit.amountOnDeposit) == 0
                && Double.compare(profitability, deposit.profitability) == 0
                && timeConstraints == deposit.timeConstraints
                && Objects.equals(accountId, deposit.accountId)
                && Objects.equals(name, deposit.name)
                && Objects.equals(country, deposit.country)
                && type == deposit.type
                && Objects.equals(depositor, deposit.depositor);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, name, country, type, depositor,
                amountOnDeposit, profitability, timeConstraints);
    }

    @Override
    public String toString() {
        return "Deposit{accountId='" + accountId + "', name='" + name
                + "', country='" + country + "', type=" + type
                + ", depositor='" + depositor + "', amountOnDeposit="
                + amountOnDeposit + ", profitability=" + profitability
                + ", timeConstraints=" + timeConstraints + "}";
    }
}
