package edu.ithaca.dragon.bank;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

public class AdminTest {

    @Test
    void freezeAccountTest(){
        BankAccount bankAccount1= new BankAccount("a@b.com", 200, 12345);
        BankAccount bankAccount2= new BankAccount("a@b.com", 300, 12345);
        BankAccount bankAccount3= new BankAccount("a@b.com", 400.05, 12345);
        ArrayList<BankAccount> bankAccounts= new ArrayList<BankAccount>();
        bankAccounts.add(bankAccount1);
        bankAccounts.add(bankAccount2);
        bankAccounts.add(bankAccount3);
        Admin.freezeAccount(bankAccount1);
        assertEquals(true, bankAccount1.getFrozenStatus());
        assertEquals(false, bankAccount2.getFrozenStatus());
        Admin.freezeAccount(bankAccount2);
        assertEquals(true, bankAccount2.getFrozenStatus());
        assertFalse(bankAccount3.getFrozenStatus());
        assertThrows(IllegalArgumentException.class, ()-> Admin.freezeAccount(bankAccount2));
    }

    @Test 
    void unfreezeAccountTest(){
        BankAccount bankAccount1= new BankAccount("a@b.com", 200, 12345);
        BankAccount bankAccount2= new BankAccount("a@b.com", 300, 23456);
        BankAccount bankAccount3= new BankAccount("a@b.com", 400.05, 34567);
        ArrayList<BankAccount> bankAccounts= new ArrayList<BankAccount>();
        bankAccounts.add(bankAccount1);
        bankAccounts.add(bankAccount2);
        bankAccounts.add(bankAccount3);
        Admin.freezeAccount(bankAccount1);
        assertTrue(bankAccount1.getFrozenStatus());
        Admin.unfreezeAccount(bankAccount1);
        assertFalse(bankAccount1.getFrozenStatus());
        assertThrows(IllegalArgumentException.class, () -> Admin.unfreezeAccount(bankAccount2));
        Admin.freezeAccount(bankAccount2);
        assertTrue(bankAccount2.getFrozenStatus());
        Admin.unfreezeAccount(bankAccount2);
        assertFalse(bankAccount2.getFrozenStatus());
    }

    @Test
    void requestSuspiciousAcctReportTest() throws InsufficientFundsException {
        ATM atm = new ATM();
        BankAccount bankAccount1= new BankAccount("a@b.com", 200, 45678);
        BankAccount bankAccount2= new BankAccount("a@b.com", 800, 56789);
        BankAccount bankAccount3= new CheckingAccount("a@b.com", 600, 67890);
        ArrayList<BankAccount> bankAccounts= new ArrayList<BankAccount>();
        bankAccounts.add(bankAccount1);
        bankAccounts.add(bankAccount2);
        bankAccounts.add(bankAccount3);
        Admin admin1= new Admin(bankAccounts);
        atm.deposit(bankAccount1, 1000,false);
        atm.withdraw(bankAccount1, 100, false);
        atm.transfer(bankAccount1, bankAccount2, 50.00);
        atm.withdraw(bankAccount1, 550.00, false);
        atm.transfer(bankAccount2, bankAccount1, 678);
        assertTrue(admin1.requestSuspiciousAcctReport(bankAccount1));
        assertTrue(admin1.requestSuspiciousAcctReport(bankAccount2));
        atm.withdraw(bankAccount3, 465.75, false);
        atm.transfer(bankAccount3, bankAccount1, 50);
        atm.deposit(bankAccount3, 100, false);
        atm.withdraw(bankAccount3, 100, false);
        assertFalse(admin1.requestSuspiciousAcctReport(bankAccount3));
    }

    @Test
    void susReportV2Test() throws InsufficientFundsException {
        ATM atm = new ATM();
        BankAccount bankAccount1= new BankAccount("a@b.com", 200, 45678);
        BankAccount bankAccount2= new BankAccount("a@b.com", 800, 56789);
        BankAccount bankAccount3= new CheckingAccount("a@b.com", 600, 67890);
        ArrayList<BankAccount> bankAccounts= new ArrayList<BankAccount>();
        bankAccounts.add(bankAccount1);
        bankAccounts.add(bankAccount2);
        bankAccounts.add(bankAccount3);
        Admin admin1= new Admin(bankAccounts);
        atm.deposit(bankAccount1, 1000,false);
        atm.withdraw(bankAccount1, 100, false);
        atm.transfer(bankAccount1, bankAccount2, 50.00);
        atm.withdraw(bankAccount1, 550.00, false);
        atm.transfer(bankAccount2, bankAccount1, 678);
        assertTrue(admin1.requestSuspiciousAcctReport(bankAccount1));
        assertTrue(admin1.requestSuspiciousAcctReport(bankAccount2));
        atm.withdraw(bankAccount3, 465.75, false);
        atm.transfer(bankAccount3, bankAccount1, 50);
        atm.deposit(bankAccount3, 100,false);
        atm.withdraw(bankAccount3, 100, false);
        assertFalse(admin1.requestSuspiciousAcctReport(bankAccount3));
        ArrayList<BankAccount> badAccounts= admin1.susReportV2(bankAccounts);
        assertEquals(badAccounts.get(0), bankAccount1);
        assertEquals(badAccounts.get(1), bankAccount2);
    }

    @Test
    void totalMoneyTest(){
        BankAccount bankAccount1= new BankAccount("a@b.com", 200, 12345);
        BankAccount bankAccount2= new BankAccount("a@b.com", 300, 12345);
        BankAccount bankAccount3= new BankAccount("a@b.com", 400.05, 23456);
        ArrayList<BankAccount> bankAccounts= new ArrayList<BankAccount>();
        bankAccounts.add(bankAccount1);
        bankAccounts.add(bankAccount2);
        bankAccounts.add(bankAccount3);
        Admin admin1= new Admin(bankAccounts);
        assertEquals(admin1.totalMoney(), 900.05);
        BankAccount bankAccount4= new BankAccount("a@b.com", 10.00, 34567);
        bankAccounts.add(bankAccount4);
        assertEquals(admin1.totalMoney(), 910.00);

    }
    
}
