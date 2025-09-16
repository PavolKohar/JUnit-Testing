package com.palci.Model;

public abstract class BankAccount {
    protected String owner;
    protected double balance;

    public BankAccount(String owner,double initialBalance){
        this.owner = owner;
        this.balance = initialBalance;
    }
    // Getters

    public String getOwner() {
        return owner;
    }

    public double getBalance() {
        return balance;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
