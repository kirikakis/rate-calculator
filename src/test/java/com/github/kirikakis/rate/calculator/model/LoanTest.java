package com.github.kirikakis.rate.calculator.model;

import com.github.kirikakis.rate.calculator.model.Investor;
import com.github.kirikakis.rate.calculator.model.Loan;

import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.*;

public class LoanTest {

    static Loan loan;

    @BeforeClass
    public static void setUpClass() {
        loan = new Loan(new Investor("Bob", 0.070, 1000d),
                0.075,1000d, 36);
    }

    @Test
    public void testLoanConstructor() {
        assertEquals(new Double(0.0062499999999999995), loan.getMonthlyRate());
        assertEquals(new Double(31.10621816065604), loan.getMonthlyPayment());
    }
}