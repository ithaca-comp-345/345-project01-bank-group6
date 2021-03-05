package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TellerTest{

    @Test
    void createAccountTest(){
            Teller t =  new Teller();
            Client c = new Client("happyness","Gilmore12!", "charles@ithaca.edu");
            t.createCheckingAccount(c, new CheckingAccount(c.getEmail(), 500, 12340));
            t.createCheckingAccount(c, new CheckingAccount(c.getEmail(), 666, 12341));
            t.createCheckingAccount(c, new CheckingAccount(c.getEmail(), 750, 12342));
            t.createCheckingAccount(c, new CheckingAccount(c.getEmail(), 1000, 12343));
            t.createSavingsAccount(c, new SavingsAccount(c.getEmail(), 501, 23450));
            t.createSavingsAccount(c, new SavingsAccount(c.getEmail(), 667, 23451));
            t.createSavingsAccount(c, new SavingsAccount(c.getEmail(), 751, 23452));
            t.createSavingsAccount(c, new SavingsAccount(c.getEmail(), 1001, 23453));
            assertEquals(12341, c.getCheckingAccountAt(1).getAccountID());
            assertEquals(23451, c.getSavingsAccountAt(1).getAccountID());
    }

    @Test
    void closeAccount(){
        Teller t =  new Teller();
            Client c = new Client("happyness","Gilmore12!", "charles@ithaca.edu");
            t.createCheckingAccount(c, new CheckingAccount(c.getEmail(), 500, 12340));
            t.createCheckingAccount(c, new CheckingAccount(c.getEmail(), 666, 12341));
            t.createCheckingAccount(c, new CheckingAccount(c.getEmail(), 750, 12342));
            t.createCheckingAccount(c, new CheckingAccount(c.getEmail(), 1000, 12343));
            t.createSavingsAccount(c, new SavingsAccount(c.getEmail(), 501, 23450));
            t.createSavingsAccount(c, new SavingsAccount(c.getEmail(), 667, 23451));
            t.createSavingsAccount(c, new SavingsAccount(c.getEmail(), 751, 23452));
            t.createSavingsAccount(c, new SavingsAccount(c.getEmail(), 1001, 23453));
            assertEquals(12341, c.getCheckingAccountAt(1).getAccountID());
            assertEquals(23451, c.getSavingsAccountAt(1).getAccountID());
            t.closeSavingsAccount(c, 1);
            t.closeCheckingAccount(c, 1);
            assertEquals(12342, c.getCheckingAccountAt(1).getAccountID());
            assertEquals(23452, c.getSavingsAccountAt(1).getAccountID());
    }

}