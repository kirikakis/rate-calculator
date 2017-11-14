package com.github.kirikakis.rate.calculator;

import com.github.kirikakis.rate.calculator.exceptions.InefficientFundsException;
import com.github.kirikakis.rate.calculator.exceptions.WrongLoanAmountException;
import com.github.kirikakis.rate.calculator.model.Investor;
import com.github.kirikakis.rate.calculator.model.Loan;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LoanCalculatorServiceTest {

    private List<Investor> investors;
    Integer increment = 100;
    Integer loanAmountMinimum = 1000;
    Integer loanAmountMaximum = 15000;

    private Integer durationInMonths = 36;

    LoanCalculatorService loanCalculatorService;

    @Rule
    public ExpectedException expectedEx = ExpectedException.none();

    @Before
    public void setUpTest() {
        investors = new ArrayList<>();
        investors.add(new Investor("Bob", 0.075, 640d));
        investors.add(new Investor("Jane", 0.069, 480d));
        investors.add(new Investor("Fred", 0.071, 520d));

        loanCalculatorService = new LoanCalculatorService(
                investors, increment, loanAmountMinimum, loanAmountMaximum);
    }

    @Test
    public void checkAmountIsCorrectNoError() throws WrongLoanAmountException {
        loanCalculatorService.checkAmountIsCorrect(1000);
    }

    @Test(expected = WrongLoanAmountException.class)
    public void checkAmountIsCorrectWrongIncrement() throws WrongLoanAmountException {
        loanCalculatorService.checkAmountIsCorrect(1001);
        expectedEx.expectMessage("The amount is not between increment values");
    }

    @Test(expected = WrongLoanAmountException.class)
    public void checkAmountIsCorrectAmountLessThanExpected() throws WrongLoanAmountException {
        loanCalculatorService.checkAmountIsCorrect(999);
        expectedEx.expectMessage("The amount is less that the minimum allowed.");
    }

    @Test(expected = WrongLoanAmountException.class)
    public void checkAmountIsCorrectAmountMoreThanExpected() throws WrongLoanAmountException {
        loanCalculatorService.checkAmountIsCorrect(15001);
        expectedEx.expectMessage("The amount is more that the maximum allowed.");
    }

    @Test
    public void getLoansRequiredOneInvestor() throws IOException, InefficientFundsException {
        List<Loan> returnedLoans = loanCalculatorService.getLoansRequired(400d, durationInMonths);

        assertEquals(1, returnedLoans.size());
        assertEquals("Jane", returnedLoans.get(0).getInvestor().getName());
        assertEquals( new Double(0.069), returnedLoans.get(0).getAnnualRate());
        assertEquals(new Double(400), returnedLoans.get(0).getAmount());
    }

    @Test
    public void getLoansRequiredTwoInvestors() throws IOException, InefficientFundsException {
        List<Loan> returnedLoans = loanCalculatorService.getLoansRequired(500d, durationInMonths);

        assertEquals(2, returnedLoans.size());

        assertEquals("Jane", returnedLoans.get(0).getInvestor().getName());
        assertEquals( new Double(0.069), returnedLoans.get(0).getAnnualRate());
        assertEquals(new Double(480), returnedLoans.get(0).getAmount());

        assertEquals("Fred", returnedLoans.get(1).getInvestor().getName());
        assertEquals( new Double(0.071), returnedLoans.get(1).getAnnualRate());
        assertEquals(new Double(20), returnedLoans.get(1).getAmount());
    }

    @Test
    public void getLoansRequiredThreeInvestors() throws IOException, InefficientFundsException {
        List<Loan> returnedLoans = loanCalculatorService.getLoansRequired(1100d, durationInMonths);

        assertEquals(3, returnedLoans.size());

        assertEquals("Jane", returnedLoans.get(0).getInvestor().getName());
        assertEquals( new Double(0.069), returnedLoans.get(0).getAnnualRate());
        assertEquals(new Double(480), returnedLoans.get(0).getAmount());

        assertEquals("Fred", returnedLoans.get(1).getInvestor().getName());
        assertEquals( new Double(0.071), returnedLoans.get(1).getAnnualRate());
        assertEquals(new Double(520), returnedLoans.get(1).getAmount());

        assertEquals("Bob", returnedLoans.get(2).getInvestor().getName());
        assertEquals( new Double(0.075), returnedLoans.get(2).getAnnualRate());
        assertEquals(new Double(100), returnedLoans.get(2).getAmount());
    }

    @Test(expected = InefficientFundsException.class)
    public void getLoansRequiredThowsInefficientFundsException() throws IOException, InefficientFundsException {
        loanCalculatorService.getLoansRequired(2000d, durationInMonths);
    }

    @Test
    public void getCombinedLoan() throws InefficientFundsException, WrongLoanAmountException {
        loanCalculatorService.getCombinedLoan(1000, durationInMonths);
    }
}