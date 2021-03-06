package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200,12345);
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
        BankAccount bankAccount = new BankAccount("a@b.com", 200,12345);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100,12345));

       assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 100.001,12345));
       assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 100.999,12345));
       assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 100.0000000005,12345));
       assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100,12345));
       assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100.999,12345));
       assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -100.99,12345));
       assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.", 100.99,12345));
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


        SavingsAccount sa = new SavingsAccount("a@b.com", 200,12345);
        sa.compoundInterest();
        assertEquals(202, sa.getBalance());
        SavingsAccount sa2 = new SavingsAccount("a@b.com", 0,12345);
        sa2.compoundInterest();
        assertEquals(0, sa2.getBalance());
        SavingsAccount sa3 = new SavingsAccount("a@b.com", 200,12345);
        sa3.setFrozenStatus(true);
        sa3.compoundInterest();
        assertEquals(200, sa3.getBalance());
    }
    @Test
    void getTransactionHistoryTest() throws InsufficientFundsException, IllegalArgumentException{
        ATM atm = new ATM();
        BankAccount checking1 = new CheckingAccount("a@b.com",500,12345);
        BankAccount checking2 = new CheckingAccount("a@bc.com",500,12345);
        ArrayList<Double> balances=new ArrayList<>();
        //valid transactions
        atm.deposit(checking1,100,false);
        balances.add(checking1.getBalance());
        atm.withdraw(checking1,50,false);
        balances.add(checking1.getBalance());
        atm.transfer(checking1,checking2,50);
        balances.add(checking1.getBalance());
        atm.deposit(checking1,2.50,false);
        balances.add(checking1.getBalance());
        atm.withdraw(checking1,1.25,false);
        balances.add(checking1.getBalance());
        atm.transfer(checking1,checking2,1.25);
        balances.add(checking1.getBalance());
        String [] types = new String[]{"deposit","withdraw","transfer-to","deposit","withdraw","transfer-to"};
        String [] amounts = new String[]{"100.0","50.0","50.0","2.5","1.25","1.25"};
        ArrayList<String> transactions1 = checking1.getTransactionHistory();
        for (int i=0;i<transactions1.size();i++){
            String[] transaction1 = transactions1.get(i).split(" ");
            assertEquals(types[i],transaction1[0]);
            if(types[i]=="transfer-to"||types[i]=="transfer-from"){
                assertEquals(Integer.toString(checking2.getAccountID()),transaction1[1]);
                assertEquals(amounts[i],transaction1[2]);
            }
            else{
                assertEquals(amounts[i],transaction1[1]);
            }
            assertEquals(Double.toString(balances.get(i)),transaction1[transaction1.length-1]);
        }
        ArrayList<String> transactions2 = checking2.getTransactionHistory();
        
        for (int i=0;i<transactions2.size();i++){
            String [] transaction2 = transactions2.get(i).split(" ");
            assertEquals("transfer-from",transaction2[0]);
            assertEquals(Integer.toString(checking1.getAccountID()),transaction2[1]);
            if(i==0){
                assertEquals(amounts[2],transaction2[2]);
                assertEquals("550.0",transaction2[transaction2.length-1]);
            }
            else{
                assertEquals(amounts[5],transaction2[2]);
                assertEquals("551.25",transaction2[transaction2.length-1]);
            }
        }
        try{
            atm.deposit(checking1,100.789,false);
            atm.deposit(checking1,-100.789,false);
            atm.deposit(checking1,-100,false);
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
        //invalid transactions aren't added to the history
        ArrayList<String> newTransactions1 = checking1.getTransactionHistory();
        for(int i=0;i<transactions1.size();i++){
            assertEquals(transactions1.get(i),newTransactions1.get(i));
        }
        //test for savings account and interest when implemented
        SavingsAccount savings = new SavingsAccount("a@b.com",500,12345);
        savings.compoundInterest();
        ArrayList<String> transactionLog = savings.getTransactionHistory();
        String[] t1 = transactionLog.get(0).split(" ");
        assertEquals("interest",t1[0]);
        assertEquals(Double.toString(savings.getInterest()),t1[1]);
        assertEquals(Double.toString(savings.getBalance()),t1[t1.length-1]);

        //atm.displayTransactionHistory(checking1);
        //atm.displayTransactionHistory(savings);
    }

}