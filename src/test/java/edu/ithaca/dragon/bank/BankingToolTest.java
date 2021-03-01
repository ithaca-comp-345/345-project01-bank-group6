package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class BankingToolTest {

    @Test
    void withdrawTest() throws InsufficientFundsException{
        ATM atm = new ATM();
        BankAccount bankAccount = new BankAccount("a@b.com", 200,123);
        atm.withdraw(bankAccount, 100,false);

        assertEquals(100, bankAccount.getBalance());
        assertThrows(InsufficientFundsException.class, () -> atm.withdraw(bankAccount,300,false));

        BankAccount bankAccount2 = new BankAccount("ab@c.com", 400,1234);

        //equivalence case- the amount to withdraw is smaller than the balance
        //boundary case- the amount to withdraw is just under the balance
        atm.withdraw(bankAccount2,399,false);
        assertEquals(1, bankAccount2.getBalance());
        //boundary case- the amount to withdraw is 0
        atm.withdraw(bankAccount2,0,false);
        assertEquals(1, bankAccount2.getBalance());

        //equivalence case- the amount to withdraw is larger than the balance

        //boundary case- the amount to withdraw is just over the balance
        BankAccount bankAccount3= new BankAccount ("cb@bc.com", 400,12345);
        assertThrows(InsufficientFundsException.class, () -> atm.withdraw(bankAccount3,401,false));
        //boundary case- the amount to withdraw is much larger than the balance
        assertThrows(InsufficientFundsException.class, () -> atm.withdraw(bankAccount3,1000,false));

        //equivalence case- the amount ot withdraw is the same as the balance

        atm.withdraw(bankAccount3,400,false);
        assertEquals(0, bankAccount3.getBalance());

        //equivalence case- the amount to withdraw is negative. Should throw exception
        assertThrows(IllegalArgumentException.class, () -> atm.withdraw(bankAccount3,-10,false));
        assertThrows(IllegalArgumentException.class, ()-> atm.withdraw(bankAccount3,500.009,false));
        BankAccount bankAccount4= new BankAccount("a@b.com", 500, 12345);
        bankAccount4.setFrozenStatus(true);
        assertThrows(IllegalArgumentException.class, () -> atm.withdraw(bankAccount4, 50, false));

    }

    @Test
    void depositTest(){
        ATM atm = new ATM();
        BankAccount bankAccount= new BankAccount("a@b.com", 200,12345);
        atm.deposit(bankAccount,100);
        assertEquals(bankAccount.getBalance(), 300);
        atm.deposit(bankAccount,0);
        assertEquals(bankAccount.getBalance(), 300);
        atm.deposit(bankAccount,100.01);
        assertEquals(bankAccount.getBalance(), 400.01);
        assertThrows(IllegalArgumentException.class, ()-> atm.deposit(bankAccount,-100));
        assertThrows(IllegalArgumentException.class, ()-> atm.deposit(bankAccount,100.001));
        atm.deposit(bankAccount,0.99);
        assertEquals(bankAccount.getBalance(), 401.00);
        BankAccount bankAccount4= new BankAccount("a@b.com", 500, 12345);
        bankAccount4.setFrozenStatus(true);
        assertThrows(IllegalArgumentException.class, () -> atm.deposit(bankAccount4, 50));

    }


    @Test
    void transferTest() throws InsufficientFundsException {
        ATM atm = new ATM();
        BankAccount bankAccount1= new BankAccount("a@b.com", 200,12345);
        BankAccount bankAccount2= new BankAccount("b@c.com", 200,123456);
        atm.transfer(bankAccount1, bankAccount2,100);
        assertEquals(bankAccount1.getBalance(), 100);
        assertEquals(bankAccount2.getBalance(), 300);
        assertThrows(InsufficientFundsException.class, ()-> atm.transfer(bankAccount1,bankAccount2,200));
        assertThrows(IllegalArgumentException.class, ()-> atm.transfer(bankAccount1,bankAccount2,-100));
        atm.transfer(bankAccount2, bankAccount1,100.1);
        assertEquals(bankAccount1.getBalance(), 200.1);
        assertEquals(bankAccount2.getBalance(), 199.9);
        atm.transfer(bankAccount2, bankAccount1,.05);
        assertEquals(bankAccount1.getBalance(), 200.15);
        assertEquals(bankAccount2.getBalance(), 199.85);
        assertThrows(IllegalArgumentException.class, ()-> atm.transfer(bankAccount1, bankAccount2,0.001));

        BankAccount bankAccount4= new BankAccount("a@b.com", 500, 12345);
        bankAccount4.setFrozenStatus(true);
        assertThrows(IllegalArgumentException.class, () -> atm.transfer(bankAccount4, bankAccount1, 50));


    }
    @Test
    void displayTransactionHistoryTest() throws InsufficientFundsException {
        ATM atm = new ATM();
        //not sure how to test
        //does this need a test?
        BankAccount checking1 = new CheckingAccount("a@b.com",500,12345);
        BankAccount checking2 = new CheckingAccount("a@bc.com",500,12345);
        //valid transactions
        atm.deposit(checking1,100);
        atm.withdraw(checking1,50,false);
        atm.transfer(checking1,checking2,50);
        atm.deposit(checking1,2.50);
        atm.withdraw(checking1,1.25,false);
        atm.transfer(checking1,checking2,1.25);

        try{
            atm.deposit(checking1,100.789);
            atm.deposit(checking1,-100.789);
            atm.deposit(checking1,-100);
            atm.withdraw(checking1,800.789,false);
            atm.withdraw(checking1,800,false);
            atm.withdraw(checking1,100.789,false);
            atm.withdraw(checking1,-100.789,false);
            atm.withdraw(checking1,-100,false);
            atm.transfer(checking1,checking2,800.789);
            atm.transfer(checking1,checking2,800);
            atm.transfer(checking1,checking2,100.789);
            atm.transfer(checking1,checking2,-100.789);
            atm.transfer(checking1,checking2,-100);

        }
        catch(Exception e){}
        
        SavingsAccount savings = new SavingsAccount("a@b.com",500,12345);
        savings.compoundInterest();

        BankAccount checking3 = new CheckingAccount("ab@c.net",600,54321);

        atm.displayTransactionHistory(checking1);
        atm.displayTransactionHistory(checking2);
        atm.displayTransactionHistory(checking3);
        atm.displayTransactionHistory(savings);
    }

}