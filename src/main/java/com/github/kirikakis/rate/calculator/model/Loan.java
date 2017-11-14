package com.github.kirikakis.rate.calculator.model;


public class Loan {
    private Investor investor;
    private Double annualRate;
    private Double amount;
    private Integer durationInMonths;
    private Double monthlyRate;
    private Double monthlyPayment;

    public Loan(Investor investor, Double annualRate, Double amount, Integer durationInMonths) {
        this.investor = investor;
        this.annualRate = annualRate;
        this.amount = amount;
        this.durationInMonths = durationInMonths;

        this.monthlyRate = annualRate / 12d;

        calculateMonthlyPayment();
    }

    public Investor getInvestor() {
        return investor;
    }

    public Double getAmount() {
        return amount;
    }

    public Double getAnnualRate() {
        return annualRate;
    }

    public Double getMonthlyRate() {
        return monthlyRate;
    }

    public Double getMonthlyPayment() {
        return monthlyPayment;
    }

    public void calculateMonthlyPayment() {
        monthlyPayment = (amount * monthlyRate) /
                            (1 - Math.pow(1 + monthlyRate, -durationInMonths));

    }
}
