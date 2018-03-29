package com.hm.webstore.domain.entity;

public class Money {
    public static final Money ZERO = new Money(0, "NONE");
    private double amount;
    private String currency;
    
    public Money(double amount, String currency) {
        this.amount = amount;
        this.currency = currency;
    }
    
    public double getAmount() {
        return amount;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public Money plus(Money other) {
        return new Money(this.amount + other.amount, this.currency);
    }
    
    public Money dividedBy(long divisor) {
        return new Money(this.amount / divisor, this.currency);
    }
    
    public String toString() {
        return String.format("%s %.2f", currency, amount);
    }
    
    public Money multipliedBy(long quantity) {
        return new Money(this.amount * quantity, this.currency);
    }
}
