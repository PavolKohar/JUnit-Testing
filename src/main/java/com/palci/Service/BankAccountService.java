package com.palci.Service;

import com.palci.Exceptions.NotEnoughFundsException;
import com.palci.Model.BankAccount;
import com.palci.Model.StandardAccount;

public class BankAccountService {


    public void deposit(BankAccount account ,double amount){
        if (amount<=0){
            throw new IllegalArgumentException("Deposit must be positive");
        }
        account.setBalance(account.getBalance() + amount );
    }

    public void withDraw(BankAccount account,double amount){
        if (account instanceof StandardAccount){
            if (amount> StandardAccount.MAX_WITHDRAW_AMOUNT){
                throw new IllegalArgumentException("Standard account can't withdraw more than 1000€ at once");
            }
        }

        if (amount>account.getBalance()){
            throw new NotEnoughFundsException("Not enough funds");
        }
        if (amount<0){
            throw new IllegalArgumentException("Amount must be positive");
        }

        account.setBalance(account.getBalance()-amount);
    }


    public void transfer(BankAccount from,BankAccount to , double amount){
        if (from instanceof StandardAccount){
            if (amount>StandardAccount.MAX_TRANSFER_AMOUNT){
                throw new IllegalArgumentException("Standard account can't send more than 500€ at once");
            }
        }
        if (amount>from.getBalance()){
            throw new NotEnoughFundsException("Not enough funds");
        }
        if (amount<=0){
            throw new IllegalArgumentException("Amount must be positive");
        }

        from.setBalance(from.getBalance()-amount);
        to.setBalance(to.getBalance()+amount);
    }
}
