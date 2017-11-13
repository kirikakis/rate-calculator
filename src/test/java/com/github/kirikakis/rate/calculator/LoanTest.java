package com.github.kirikakis.rate.calculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class LoanTest {

    @Test
    public void getMonthlyRate() {
        Loan loan = new Loan(new Investor("Bob", 0.075, 640d),
                0.075,1000d);

        assertEquals(new Double(0.006), loan.getMonthlyRate());
    }

}