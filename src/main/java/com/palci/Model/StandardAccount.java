package com.palci.Model;


public class StandardAccount extends BankAccount {

    public static final double MAX_WITHDRAW_AMOUNT = 1000;
    public static final double MAX_TRANSFER_AMOUNT = 500;

    public StandardAccount(String owner,double initialDeposit){
        super(owner,initialDeposit);
    }



}
