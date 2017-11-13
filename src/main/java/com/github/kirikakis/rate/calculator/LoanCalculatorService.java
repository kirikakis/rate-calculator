package com.github.kirikakis.rate.calculator;

import java.util.ArrayList;
import java.util.List;

public class LoanCalculatorService {
    List<Investor> investors;

    public LoanCalculatorService(List<Investor> investors) {
        this.investors = investors;
    }

    public List<Loan> getLoansRequired(Double loanAmount) throws InefficientFundsException {
        List<Loan> loans = new ArrayList<>();

        //Sorting investors according to their rate;
        this.investors.sort((a, b) -> b.compareToRate(a));
        for(Investor investor : investors) {
            if(investor.getBalance() < loanAmount) {
                loans.add(new Loan(investor, investor.getBalance(), investor.getRate()));
                loanAmount -= investor.getBalance();
                investor.setBalance(0d);
            }
            else {
                investor.setBalance(investor.getBalance() - loanAmount);
                loans.add(new Loan(investor, loanAmount, investor.getRate()));
                break;
            }
        }
        return loans;
    }
}
