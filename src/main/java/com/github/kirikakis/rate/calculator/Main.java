package com.github.kirikakis.rate.calculator;

import com.github.kirikakis.rate.calculator.exceptions.InefficientFundsException;
import com.github.kirikakis.rate.calculator.exceptions.WrongLoanAmountException;
import com.github.kirikakis.rate.calculator.model.CombinedLoan;
import com.github.kirikakis.rate.calculator.model.Loan;
import com.github.kirikakis.rate.calculator.utilities.BootstrapUtilities;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException, InefficientFundsException, WrongLoanAmountException {
        LoanCalculatorService loanCalculatorService = new LoanCalculatorService(
                BootstrapUtilities.FetchCsvDataFromFile(args[0]),
                        100,
                1000,
                15000);
        System.out.println(
            getCombinedLoan(loanCalculatorService, Integer.parseInt(args[1]), args.length > 2 ? Integer.parseInt(args[2]): 36 ).toString()
        );
    }

    static CombinedLoan getCombinedLoan(
            LoanCalculatorService calculatorService, Integer amount, Integer durationInMonths)
            throws InefficientFundsException, WrongLoanAmountException {
        calculatorService.checkAmountIsCorrect(amount);
        List<Loan> loans = calculatorService.getLoansRequired(amount.doubleValue(), durationInMonths);
        return new CombinedLoan(loans, durationInMonths);
    }
}
