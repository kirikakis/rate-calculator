package com.github.kirikakis.rate.calculator.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

public class CombinedLoan {
    private List<Loan> loans;
    private Double averageRate = 0d;
    private Double totalAmount = 0d;
    private Double totalMonthlyRepayment = 0d;
    private Integer durationInMonths = 0;
    private Double totalInterest = 0d;

    public CombinedLoan(List<Loan> loans, Integer durationInMonths) {
        this.loans = loans;
        this.durationInMonths = durationInMonths;
        calculateAverageRateAndTotalAmount();
    }

    public List<Loan> getLoans() {
        return loans;
    }

    public Double getAverageRate() {
        return BigDecimal.valueOf(averageRate * 100)
                .setScale(1, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public Double getTotalAmount() {
        return BigDecimal.valueOf(totalAmount)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public Integer getDurationInMonths() {
        return durationInMonths;
    }

    public Double getTotalMonthlyRepayment() {
        return BigDecimal.valueOf(totalMonthlyRepayment)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }


    private void calculateAverageRateAndTotalAmount() {
        totalInterest = 0d;
        totalAmount = 0d;
        for(Loan loan : loans) {
            totalInterest += loan.getAmount() * loan.getAnnualRate();
            totalAmount += loan.getAmount();
            totalMonthlyRepayment += loan.getMonthlyPayment();
        }
        averageRate = totalInterest / totalAmount;
    }

    @Override
    public String toString() {

        return new StringBuilder()
        .append("\nRequested Amount: " + totalAmount)
        .append("\nRate: " + getAverageRate())
        .append("\nMonthly repayment:" + getTotalMonthlyRepayment())
        .append("\nTotal repayment:" +
                BigDecimal.valueOf(totalMonthlyRepayment * durationInMonths)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue())
        .toString();
    }
}
