package com.palci.Service;

import com.palci.Exceptions.NotEnoughFundsException;
import com.palci.Model.BankAccount;
import com.palci.Model.PremiumAccount;
import com.palci.Model.StandardAccount;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountServiceTest {

    private BankAccountService service;
    private BankAccount standard;
    private BankAccount premium;

    @BeforeEach
    void setUp() {
        service = new BankAccountService();
        standard = new StandardAccount("John",2000);
        premium = new PremiumAccount("David",10000);
    }

    @Test
    void standardAccountWithdraw(){
        service.withDraw(standard,500);
        assertEquals(1500,standard.getBalance());
    }

    @Test
    void standardAccountCannotWithdrawOverLimit(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->{
            service.withDraw(standard,1300);
        });

        assertEquals("Standard account can't withdraw more than 1000€ at once",exception.getMessage());
    }

    @Test
    void premiumAccountWithdrawWithNoLimit(){
        service.withDraw(premium,5000);
        assertEquals(5000,premium.getBalance());
    }

    @Test
    void standardDepositTest(){
        service.deposit(standard,300);
        assertEquals(2300,standard.getBalance());
    }

    @Test
    void premiumDepositTest(){
        service.deposit(premium,3000);
        assertEquals(13000,premium.getBalance());
    }

    @Test
    void standardCannotDepositNegativeNumber(){
        assertThrows(IllegalArgumentException.class,()->{
            service.deposit(standard,-200);
        });
    }

    @Test
    void premiumCannotDepositNegativeNumber(){
        assertThrows(IllegalArgumentException.class,()->{
            service.deposit(premium,-400);
        });
    }
    @Test
    void standardAccountWithdrawNegativeNumber(){
        assertThrows(IllegalArgumentException.class,()->{
            service.withDraw(standard,-300);
        });
    }

    @Test
    void premiumAccountWithdrawNegativeNumber(){
        assertThrows(IllegalArgumentException.class,()->{
            service.withDraw(premium,-300);
        });
    }

    @Test
    void transferFromStandardToPremium(){
        service.transfer(standard,premium,300);
        assertEquals(1700,standard.getBalance());
        assertEquals(10300,premium.getBalance());
    }

    @Test
    void transferFromPremiumToStandard(){
        service.transfer(premium,standard,300);
        assertEquals(2300,standard.getBalance());
        assertEquals(9700,premium.getBalance());
    }

    @Test
    void standardTransferAmountOverLimit(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->{
            service.transfer(standard,premium,1000);
        });
        assertEquals("Standard account can't send more than 500€ at once",exception.getMessage());
    }

    @Test
    void premiumTransferOverBalance(){
        Exception exception = assertThrows(NotEnoughFundsException.class,()->{
            service.transfer(premium,standard,15000);
        });
        assertEquals("Not enough funds",exception.getMessage());
    }

    @Test
    void standardTransferNegativeNumber(){
        Exception exception = assertThrows(IllegalArgumentException.class,()->{
            service.transfer(standard,premium,-300);
        });
        assertEquals("Amount must be positive",exception.getMessage());
    }



    @AfterEach
    void tearDown() {
    }
}