package com.github.kirikakis.rate.calculator;

import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class LoanCalculatorServiceTest {

    static List<Investor> investors;

    @BeforeClass
    public static void setUpTest() {
        investors = new ArrayList<>();
        investors.add(new Investor("Bob", 0.075, 640d));
        investors.add(new Investor("Jane", 0.069, 480d));
        investors.add(new Investor("Fred", 0.071, 520d));
    }

    @Test
    public void getLoansRequiredOneInvestor() throws IOException, InefficientFundsException {
        LoanCalculatorService loanCalculatorService = new LoanCalculatorService(investors);
        List<Loan> returnedLoans = loanCalculatorService.getLoansRequired(400d);

        assertEquals(1, returnedLoans.size());
        assertEquals("Jane", returnedLoans.get(0).getInvestor().getName());
        assertEquals( new Double(0.069), returnedLoans.get(0).getAnnualRate());
        assertEquals(new Double(400), returnedLoans.get(0).getBalance());
    }

    @Test
    public void getLoansRequiredTwoInvestors() throws IOException, InefficientFundsException {
        LoanCalculatorService loanCalculatorService = new LoanCalculatorService(investors);
        List<Loan> returnedLoans = loanCalculatorService.getLoansRequired(500d);

        assertEquals(2, returnedLoans.size());

        assertEquals("Jane", returnedLoans.get(0).getInvestor().getName());
        assertEquals( new Double(0.069), returnedLoans.get(0).getAnnualRate());
        assertEquals(new Double(480), returnedLoans.get(0).getBalance());

        assertEquals("Fred", returnedLoans.get(1).getInvestor().getName());
        assertEquals( new Double(0.071), returnedLoans.get(1).getAnnualRate());
        assertEquals(new Double(20), returnedLoans.get(1).getBalance());
    }

    @Test
    public void getLoansRequiredThreeInvestors() throws IOException, InefficientFundsException {
        LoanCalculatorService loanCalculatorService = new LoanCalculatorService(investors);
        List<Loan> returnedLoans = loanCalculatorService.getLoansRequired(1100d);

        assertEquals(3, returnedLoans.size());

        assertEquals("Jane", returnedLoans.get(0).getInvestor().getName());
        assertEquals( new Double(0.069), returnedLoans.get(0).getAnnualRate());
        assertEquals(new Double(480), returnedLoans.get(0).getBalance());

        assertEquals("Fred", returnedLoans.get(1).getInvestor().getName());
        assertEquals( new Double(0.071), returnedLoans.get(1).getAnnualRate());
        assertEquals(new Double(520), returnedLoans.get(1).getBalance());

        assertEquals("Bob", returnedLoans.get(2).getInvestor().getName());
        assertEquals( new Double(0.075), returnedLoans.get(2).getAnnualRate());
        assertEquals(new Double(100), returnedLoans.get(2).getBalance());
    }
}