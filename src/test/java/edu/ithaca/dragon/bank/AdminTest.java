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
        BankAccount bankAccount1= new BankAccount("a@b.com", 200);
        BankAccount bankAccount2= new BankAccount("a@b.com", 300);
        BankAccount bankAccount3= new BankAccount("a@b.com", 400.05);
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
        BankAccount bankAccount1= new BankAccount("a@b.com", 200);
        BankAccount bankAccount2= new BankAccount("a@b.com", 300);
        BankAccount bankAccount3= new BankAccount("a@b.com", 400.05);
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
    void requestSuspiciousAcctReportTest(){
        BankAccount bankAccount1= new BankAccount("a@b.com", 200);
        BankAccount bankAccount2= new BankAccount("a@b.com", 300);
        BankAccount bankAccount3= new BankAccount("a@b.com", 400.05);
        ArrayList<BankAccount> bankAccounts= new ArrayList<BankAccount>();
        bankAccounts.add(bankAccount1);
        bankAccounts.add(bankAccount2);
        bankAccounts.add(bankAccount3);
        Admin admin1= new Admin(bankAccounts);
        admin1.requestSuspiciousAcctReport(bankAccount1);
        admin1.requestSuspiciousAcctReport(bankAccount2);
        admin1.requestSuspiciousAcctReport(bankAccount3);
    }

    @Test
    void totalMoneyTest(){
        BankAccount bankAccount1= new BankAccount("a@b.com", 200);
        BankAccount bankAccount2= new BankAccount("a@b.com", 300);
        BankAccount bankAccount3= new BankAccount("a@b.com", 400.05);
        ArrayList<BankAccount> bankAccounts= new ArrayList<BankAccount>();
        bankAccounts.add(bankAccount1);
        bankAccounts.add(bankAccount2);
        bankAccounts.add(bankAccount3);
        Admin admin1= new Admin(bankAccounts);
        assertEquals(admin1.totalMoney(), 900.05);
        BankAccount bankAccount4= new BankAccount("a@b.com", 10.00);
        bankAccounts.add(bankAccount4);
        assertEquals(admin1.totalMoney(), 910.00);

    }
    
}
