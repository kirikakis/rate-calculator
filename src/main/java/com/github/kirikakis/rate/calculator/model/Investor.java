package com.github.kirikakis.rate.calculator.model;

public class Investor {
    private String name;
    private Double rate;
    private Double balance;

    public Investor(String name, Double rate, Double balance) {
        this.name = name;
        this.rate = rate;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public Double getRate() {
        return rate;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Double getBalance() {
        return balance;
    }

    public int compareToRate(Investor investor) {
        if(this.rate < investor.getRate()) {
            return 1;
        }
        else if(this.getRate().equals(investor.getRate())) {
            return 0;
        }
        return -1;
    }
}
