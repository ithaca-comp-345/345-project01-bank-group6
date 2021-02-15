package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.List;

public class Admin {
    List<BankAccount> allAccounts= new ArrayList<BankAccount>(); 
    
    public Admin(ArrayList<BankAccount> allAccounts){
        this.allAccounts= allAccounts;
    }

    public double totalMoney(List<BankAccount> allAccounts){
        double totalSum=0;
        for(int i=0; i< allAccounts.size(); i++){
            totalSum= totalSum + allAccounts.get(i).getBalance();
        }
        return totalSum;
    }

    public boolean requstSuspiciousAcctReport(BankAccount suspicious){
        suspicious.transactionHistory();
        //What exactly will suspicious activity look like? Withdrawals over a certain amount maybe? What will the transaction history return?
        return false;
    }

    public static void freezeAccount(BankAccount toFreeze){
        
        
    }

    public static void unfreezeAccount(BankAccount unfreeze){
        
    }
}
