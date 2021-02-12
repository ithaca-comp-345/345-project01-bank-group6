package edu.ithaca.dragon.bank;

import java.util.List;

public class Admin {
    List allAccounts; 
    
    public Admin(List allAccounts){
        this.allAccounts= allAccounts;
    }

    public double totalMoney(List allAccounts){
        return 0;
    }

    public boolean susActivity(List allAccounts){
        return false;
    }

    public static void freezeAccount(BankAccount toFreeze){

    }
}
