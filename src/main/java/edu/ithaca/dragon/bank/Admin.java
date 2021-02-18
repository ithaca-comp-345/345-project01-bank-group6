package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    List<BankAccount> allAccounts= new ArrayList<BankAccount>(); 
    
    public Admin(ArrayList<BankAccount> allAccounts){
        this.allAccounts= allAccounts;
    }

    public double totalMoney(){
        double totalSum=0;
        for(int i=0; i< allAccounts.size(); i++){
            totalSum= totalSum + allAccounts.get(i).getBalance();
        }
        return totalSum;
    }

    public boolean requestSuspiciousAcctReport(BankAccount suspicious){
        //suspicious.transactionHistory();
        return false;
    }

    public static void freezeAccount(BankAccount toFreeze){
        
        
    }

    public static void unfreezeAccount(BankAccount unfreeze){
        
    }
}
