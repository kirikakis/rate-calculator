package com.github.kirikakis.rate.calculator;

import org.junit.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BootstrapUtilitiesTest {

    @Test
    public void fetchCsvDataFromFile() throws Exception {
        List<Investor> returnedInvestors =
                new BootstrapUtilities().FetchCsvDataFromFile("src/test/resources/sample-data.csv");

        assertEquals(3, returnedInvestors.size());

        assertThat(returnedInvestors.get(0).getName(), is("Bob"));
        assertThat(returnedInvestors.get(0).getRate(), is(0.075));
        assertThat(returnedInvestors.get(0).getBalance(), is(640d));

        assertThat(returnedInvestors.get(1).getName(), is("Jane"));
        assertThat(returnedInvestors.get(1).getRate(), is(0.069));
        assertThat(returnedInvestors.get(1).getBalance(), is(480d));

        assertThat(returnedInvestors.get(2).getName(), is("Fred"));
        assertThat(returnedInvestors.get(2).getRate(), is(0.071));
        assertThat(returnedInvestors.get(2).getBalance(), is(520d));
    }

}