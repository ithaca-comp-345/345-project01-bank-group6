package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());
        //equivalence case- amount is larger than balance
        assertFalse(bankAccount.getBalance()==400);
        assertFalse(bankAccount.getBalance()==201);
        assertFalse(bankAccount.getBalance()==200.01);
        //equivalence case- amount is smaller than balance
        assertFalse(bankAccount.getBalance()==199);
        assertFalse(bankAccount.getBalance()==20.0);
        assertFalse(bankAccount.getBalance()==199.99);
    }

    

    @Test
    void isEmailValidTest(){
        //valid if characters before '@' and between '@' and '.' are 1 or more in number.
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        //invalid if string is empty. This is boundary case
        assertFalse( BankAccount.isEmailValid(""));
        //invalid if there is no letter or number between - and @. Equivalence case- any non-letter or number character must be followed by a letter or number
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        //invalid if # of characters after last '.' is 1 or less. This is boundary case.
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.co"));
        //invalid symbols
        assertFalse(BankAccount.isEmailValid("abc#def@mail.com"));
        assertFalse(BankAccount.isEmailValid("a^bc@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc!g@mail.com"));
        assertFalse(BankAccount.isEmailValid("$$$abc@mail.com"));
        assertFalse(BankAccount.isEmailValid("a*b*c@mail.com"));

        //invalid if '..' is found. This could be for any 2 symbols and be boundary case depending on implimentation
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));

        assertFalse(BankAccount.isEmailValid("abc.def@.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@m.com"));

        assertFalse(BankAccount.isEmailValid("#*$!^@mail.com"));
        assertTrue(BankAccount.isEmailValid("abcde@mail.com"));

        assertFalse(BankAccount.isEmailValid("abc.def@mail"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));

        


    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));

       assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 100.001));
       assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 100.999));
       assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 100.0000000005));
       assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100));
       assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100.999));
       assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100.99));
       assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.", 100.99));
    }

    @Test
    void isAmountValidTest(){
        //equivalence cases: two or less decimal places, more than two decimal places, positive doubles, negative doubles
        assertTrue(BankAccount.isAmountValid(20));
        assertTrue(BankAccount.isAmountValid(20.0));
        assertTrue(BankAccount.isAmountValid(20.01));
        assertTrue(BankAccount.isAmountValid(20.50));
        assertTrue(BankAccount.isAmountValid(20.99));
        assertFalse(BankAccount.isAmountValid(21.001));
        assertFalse(BankAccount.isAmountValid(21.501));
        assertFalse(BankAccount.isAmountValid(21.999));
        assertFalse(BankAccount.isAmountValid(-20.00));
        assertFalse(BankAccount.isAmountValid(-20.999));
        assertFalse(BankAccount.isAmountValid(20.0000000006));
    }

    @Test
    void compoundInterestTest(){


        SavingsAccount sa = new SavingsAccount("a@b.com", 200);
        sa.compoundInterest();
        assertEquals(210, sa.getBalance());
        SavingsAccount sa2 = new SavingsAccount("a@b.com", 0);
        sa2.compoundInterest();
        assertEquals(0, sa2.getBalance());
        SavingsAccount sa3 = new SavingsAccount("a@b.com", 200);
        sa3.compoundInterest();
        assertEquals(400, sa3.getBalance());
    }
    @Test
    void getTransactionHistoryTest() throws InsufficientFundsException{
        ATM atm = new ATM(10000);
        BankAccount checking1 = new CheckingAccount("a@b.com",500);
        BankAccount checking2 = new CheckingAccount("a@bc.com",500);
        ArrayList<Double> balances=new ArrayList<>();
        //valid transactions
        atm.deposit(checking1,100);
        balances.add(checking1.getBalance());
        atm.withdraw(checking1,50);
        balances.add(checking1.getBalance());
        atm.transfer(checking1,checking2,50);
        balances.add(checking1.getBalance());
        atm.deposit(checking1,2.50);
        balances.add(checking1.getBalance());
        atm.withdraw(checking1,1.25);
        balances.add(checking1.getBalance());
        atm.transfer(checking1,checking2,1.25);
        balances.add(checking1.getBalance());
        String [] types = new String[]{"deposit","withdraw","transfer","deposit","withdraw","transfer"};
        String [] amounts = new String[]{"100","50","50","2.50","1.25","1.25"};
        ArrayList<String> transactions = checking1.getTransactionHistory();
        for (int i=0;i<transactions.size();i++){
            assertEquals(types[i],transactions.get(i).split(" ")[0]);
            if(types[i]=="transfer"){
                assertEquals("checking2",transactions.get(i).split(" ")[1]);
                assertEquals(amounts[i],transactions.get(i).split(" ")[2]);
            }
            else{
                assertEquals(amounts[i],transactions.get(i).split(" ")[1]);
            }
            assertEquals(balances.get(i),transactions.get(i).split(" ")[-1]);
        }
        try{
            atm.deposit(checking1,100.789);
            atm.deposit(checking1,-100.789);
            atm.deposit(checking1,-100);
            atm.withdraw(checking1,800.789);
            atm.withdraw(checking1,800);
            atm.withdraw(checking1,100.789);
            atm.withdraw(checking1,-100.789);
            atm.withdraw(checking1,-100);
            atm.transfer(checking1,checking2,800.789);
            atm.transfer(checking1,checking2,800);
            atm.transfer(checking1,checking2,100.789);
            atm.transfer(checking1,checking2,-100.789);
            atm.transfer(checking1,checking2,-100);

        }
        catch(Exception e){}
        //invalid transactions aren't added to the history
        ArrayList<String> newTransactions = checking1.getTransactionHistory();
        for(int i=0;i<transactions.size();i++){
            assertEquals(transactions.get(i),newTransactions.get(i));
        }
        //test for savings account and interest when implemented
        SavingsAccount savings = new SavingsAccount("a@b.com",500);
        savings.compoundInterest();
        ArrayList<String> transactionLog = savings.getTransactionHistory();
        assertEquals("compounded interest",transactionLog.get(0).split(" ")[0]);
        assertEquals(Double.toString(savings.getBalance()),transactionLog.get(0).split(" ")[1]);
    }

}