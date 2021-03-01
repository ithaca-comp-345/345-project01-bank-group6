package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class BankingToolTest {

    @Test
    void withdrawTest() throws InsufficientFundsException{
        for(int i=0;i<2;i++){
            BankingTool bt;
            if(i==1)
                bt = new ATM();
            else{bt=new Teller();}
            BankAccount bankAccount = new BankAccount("a@b.com", 200,123);
            bt.withdraw(bankAccount, 100,false);

            assertEquals(100, bankAccount.getBalance());
            assertThrows(InsufficientFundsException.class, () -> bt.withdraw(bankAccount,300,false));

            BankAccount bankAccount2 = new BankAccount("ab@c.com", 400,1234);

            //equivalence case- the amount to withdraw is smaller than the balance
            //boundary case- the amount to withdraw is just under the balance
            bt.withdraw(bankAccount2,399,false);
            assertEquals(1, bankAccount2.getBalance());
            //boundary case- the amount to withdraw is 0
            bt.withdraw(bankAccount2,0,false);
            assertEquals(1, bankAccount2.getBalance());

            //equivalence case- the amount to withdraw is larger than the balance

            //boundary case- the amount to withdraw is just over the balance
            BankAccount bankAccount3= new BankAccount ("cb@bc.com", 400,12345);
            assertThrows(InsufficientFundsException.class, () -> bt.withdraw(bankAccount3,401,false));
            //boundary case- the amount to withdraw is much larger than the balance
            assertThrows(InsufficientFundsException.class, () -> bt.withdraw(bankAccount3,1000,false));

            //equivalence case- the amount ot withdraw is the same as the balance

            bt.withdraw(bankAccount3,400,false);
            assertEquals(0, bankAccount3.getBalance());

            //equivalence case- the amount to withdraw is negative. Should throw exception
            assertThrows(IllegalArgumentException.class, () -> bt.withdraw(bankAccount3,-10,false));
            assertThrows(IllegalArgumentException.class, ()-> bt.withdraw(bankAccount3,500.009,false));
            BankAccount bankAccount4= new BankAccount("a@b.com", 500, 12345);
            bankAccount4.setFrozenStatus(true);
            assertThrows(IllegalArgumentException.class, () -> bt.withdraw(bankAccount4, 50, false));
        }

    }

    @Test
    void depositTest(){
        for(int i=0;i<2;i++){
            BankingTool bt;
            if(i==0)
                bt=new ATM();
            else{bt= new Teller();}
            BankAccount bankAccount= new BankAccount("a@b.com", 200,12345);
            bt.deposit(bankAccount,100);
            assertEquals(bankAccount.getBalance(), 300);
            bt.deposit(bankAccount,0);
            assertEquals(bankAccount.getBalance(), 300);
            bt.deposit(bankAccount,100.01);
            assertEquals(bankAccount.getBalance(), 400.01);
            assertThrows(IllegalArgumentException.class, ()-> bt.deposit(bankAccount,-100));
            assertThrows(IllegalArgumentException.class, ()-> bt.deposit(bankAccount,100.001));
            bt.deposit(bankAccount,0.99);
            assertEquals(bankAccount.getBalance(), 401.00);
            BankAccount bankAccount4= new BankAccount("a@b.com", 500, 12345);
            bankAccount4.setFrozenStatus(true);
            assertThrows(IllegalArgumentException.class, () -> bt.deposit(bankAccount4, 50));
        }

    }


    @Test
    void transferTest() throws InsufficientFundsException {
        for(int i=0;i<2;i++){
            BankingTool bt;
            if(i==0)
                bt=new ATM();
            else{bt= new Teller();}
            BankAccount bankAccount1= new BankAccount("a@b.com", 200,12345);
            BankAccount bankAccount2= new BankAccount("b@c.com", 200,123456);
            bt.transfer(bankAccount1, bankAccount2,100);
            assertEquals(bankAccount1.getBalance(), 100);
            assertEquals(bankAccount2.getBalance(), 300);
            assertThrows(InsufficientFundsException.class, ()-> bt.transfer(bankAccount1,bankAccount2,200));
            assertThrows(IllegalArgumentException.class, ()-> bt.transfer(bankAccount1,bankAccount2,-100));
            bt.transfer(bankAccount2, bankAccount1,100.1);
            assertEquals(bankAccount1.getBalance(), 200.1);
            assertEquals(bankAccount2.getBalance(), 199.9);
            bt.transfer(bankAccount2, bankAccount1,.05);
            assertEquals(bankAccount1.getBalance(), 200.15);
            assertEquals(bankAccount2.getBalance(), 199.85);
            assertThrows(IllegalArgumentException.class, ()-> bt.transfer(bankAccount1, bankAccount2,0.001));

            BankAccount bankAccount4= new BankAccount("a@b.com", 500, 12345);
            bankAccount4.setFrozenStatus(true);
            assertThrows(IllegalArgumentException.class, () -> bt.transfer(bankAccount4, bankAccount1, 50));
        }


    }
    @Test
    void displayTransactionHistoryTest() throws InsufficientFundsException {
        //not sure how to test
        //does this need a test?
        for(int i=0;i<2;i++){
            BankingTool bt;
            if(i==0)
                bt=new ATM();
            else{bt= new Teller();}
            BankAccount checking1 = new CheckingAccount("a@b.com",500,12345);
            BankAccount checking2 = new CheckingAccount("a@bc.com",500,12345);
            //valid transactions
            bt.deposit(checking1,100);
            bt.withdraw(checking1,50,false);
            bt.transfer(checking1,checking2,50);
            bt.deposit(checking1,2.50);
            bt.withdraw(checking1,1.25,false);
            bt.transfer(checking1,checking2,1.25);

            try{
                bt.deposit(checking1,100.789);
                bt.deposit(checking1,-100.789);
                bt.deposit(checking1,-100);
                bt.withdraw(checking1,800.789,false);
                bt.withdraw(checking1,800,false);
                bt.withdraw(checking1,100.789,false);
                bt.withdraw(checking1,-100.789,false);
                bt.withdraw(checking1,-100,false);
                bt.transfer(checking1,checking2,800.789);
                bt.transfer(checking1,checking2,800);
                bt.transfer(checking1,checking2,100.789);
                bt.transfer(checking1,checking2,-100.789);
                bt.transfer(checking1,checking2,-100);

            }
            catch(Exception e){}
            
            SavingsAccount savings = new SavingsAccount("a@b.com",500,12345);
            savings.compoundInterest();

            BankAccount checking3 = new CheckingAccount("ab@c.net",600,54321);

            bt.displayTransactionHistory(checking1);
            bt.displayTransactionHistory(checking2);
            bt.displayTransactionHistory(checking3);
            bt.displayTransactionHistory(savings);
        }
    }

}