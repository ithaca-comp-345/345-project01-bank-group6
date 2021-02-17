package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
       SavingsAccount savings1= new SavingsAccount("a@b.com", 200, 50, 0.06);
       savings1.compoundInterest();
       assertEquals(savings1.getBalance(), 200.60);
       SavingsAccount savings2= new SavingsAccount("a@b.com", 400, 50, 0.06);
       savings2.compoundInterest();
       assertFalse(savings2.getBalance()== 450);
    }

}