package com.github.kirikakis.rate.calculator.model;

import com.github.kirikakis.rate.calculator.model.CombinedLoan;
import com.github.kirikakis.rate.calculator.model.Investor;
import com.github.kirikakis.rate.calculator.model.Loan;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CombinedLoanTest {

    private List<Investor> investors;

    private Integer durationInMonths = 36;

    @Before
    public void setUpTest() {
        investors = new ArrayList<>();
        investors.add(new Investor("Bob", 0.075, 640d));
        investors.add(new Investor("Jane", 0.069, 480d));
        investors.add(new Investor("Fred", 0.071, 520d));
    }

    @Test
    public void calculateAverageRateForOneLoan() {
        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(investors.get(0), 0.069, 400d, durationInMonths));
        CombinedLoan combinedLoan = new CombinedLoan(loans, durationInMonths);

        assertEquals(new Double(6.9), combinedLoan.getAverageRate());
    }

    @Test
    public void calculateAverageRateForTwoLoans() {
        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(investors.get(1), 0.069, 480d, durationInMonths));
        loans.add(new Loan(investors.get(2), 0.071, 520d, durationInMonths));
        CombinedLoan combinedLoan = new CombinedLoan(loans, durationInMonths);

        assertEquals(new Double(7.0), combinedLoan.getAverageRate());
    }

    @Test
    public void calculateAverageRateForThreeLoans() {
        List<Loan> loans = new ArrayList<>();
        loans.add(new Loan(investors.get(1), 0.069, 480d, durationInMonths));
        loans.add(new Loan(investors.get(2), 0.071, 520d, durationInMonths));
        loans.add(new Loan(investors.get(0), 0.075, 500d, durationInMonths));
        CombinedLoan combinedLoan = new CombinedLoan(loans, durationInMonths);

        assertEquals(new Double(7.2), combinedLoan.getAverageRate());
    }
}
