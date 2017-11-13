package com.github.kirikakis.rate.calculator;


import java.math.BigDecimal;
import java.math.RoundingMode;

public class Loan {
    private Investor investor;
    private Double balance;
    private Double annualRate;
    private Double monthlyRate;
    private Double effectiveRate;
    private Double monthlyPayment;

    public Loan(Investor investor, Double annualRate, Double balance) {
        this.investor = investor;
        this.annualRate = annualRate;
        this.balance = balance;

        this.monthlyRate = BigDecimal.valueOf(annualRate / 12)
                .setScale(3, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public Investor getInvestor() {
        return investor;
    }

    public Double getBalance() {
        return balance;
    }

    public Double getAnnualRate() {
        return annualRate;
    }

    public Double getMonthlyRate() {
        return monthlyRate;
    }

    public Double getEffectiveRate() {
        return effectiveRate;
    }

    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void calculateMonthlyPayment() {

    }
}
