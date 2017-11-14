package com.github.kirikakis.rate.calculator;

import com.github.kirikakis.rate.calculator.exceptions.InefficientFundsException;
import com.github.kirikakis.rate.calculator.exceptions.WrongLoanAmountException;
import com.github.kirikakis.rate.calculator.model.CombinedLoan;
import com.github.kirikakis.rate.calculator.model.Investor;
import com.github.kirikakis.rate.calculator.model.Loan;

import java.util.ArrayList;
import java.util.List;

public class LoanCalculatorService {
    private List<Investor> investors;
    private Integer increment;
    private Integer loanAmountMinimum;
    private Integer loanAmountMaximum;

    public LoanCalculatorService(List<Investor> investors,
                                 Integer increment, Integer loanAmountMinimum, Integer loanAmountMaximum) {
        this.investors = investors;
        this.increment = increment;
        this.loanAmountMinimum = loanAmountMinimum;
        this.loanAmountMaximum = loanAmountMaximum;
    }

    void checkAmountIsCorrect(Integer amount) throws WrongLoanAmountException {
        if(amount < loanAmountMinimum) {
            throw new WrongLoanAmountException("The amount is less that the minimum allowed.");
        }
        if(amount > loanAmountMaximum) {
            throw new WrongLoanAmountException("The amount is more that the maximum allowed.");
        }
        if((amount % increment) != 0) {
            throw new WrongLoanAmountException("The amount is not between increment values");
        }
    }

    List<Loan> getLoansRequired(Double loanAmount, Integer durationInMonths) throws InefficientFundsException {
        List<Loan> loans = new ArrayList<>();

        //Sorting investors according to their rate;
        this.investors.sort((a, b) -> b.compareToRate(a));
        for(Investor investor : investors) {
            if(investor.getBalance() < loanAmount) {
                loans.add(new Loan(investor, investor.getRate(), investor.getBalance(), durationInMonths));
                loanAmount -= investor.getBalance();
                investor.setBalance(0d);
            }
            else {
                investor.setBalance(investor.getBalance() - loanAmount);
                loans.add(new Loan(investor, investor.getRate(), loanAmount, durationInMonths));
                loanAmount = 0d;
                break;
            }
        }
        if(loanAmount > 0) {
            throw new InefficientFundsException();
        }
        return loans;
    }

    CombinedLoan getCombinedLoan(Integer amount, Integer durationInMonths)
            throws InefficientFundsException, WrongLoanAmountException {
        checkAmountIsCorrect(amount);
        List<Loan> loans = getLoansRequired(amount.doubleValue(), durationInMonths);
        return new CombinedLoan(loans, durationInMonths);
    }
}
