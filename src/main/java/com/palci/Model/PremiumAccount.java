package com.palci.Model;

import com.palci.Exceptions.NotEnoughFundsException;

public class PremiumAccount extends BankAccount {


    public PremiumAccount(String owner, double initialDeposit) {
        super(owner, initialDeposit);
    }


}
