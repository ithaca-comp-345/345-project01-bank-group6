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
        ArrayList<String> susActivity= suspicious.getTransactionHistory();
        //String[] split;
        for(int i=0; i<susActivity.size(); i++){
        String[]split= susActivity.get(i).split(" ");   
        if(split[0].equals("withdraw")){
            double value= Double.parseDouble(split[1]);
            if(value>=500){
                System.out.println("Suspcious activity detected");
                return true;
            }
        }
        if(split[0].equals("transfer")){
            double value= Double.parseDouble(split[2]);
            if(value>=500){
                System.out.println("Suspcious activity detected");
                return true;
            }
        }
        }
        return false;
    }

    public static void freezeAccount(BankAccount toFreeze){
        if(toFreeze.getFrozenStatus()== true){
            throw new IllegalArgumentException("Bank account is already frozen");
        }
        else{
            toFreeze.setFrozenStatus(true);
            System.out.println("This bank account has been frozen");
        }
        
    }

    public static void unfreezeAccount(BankAccount unfreeze){
        if(unfreeze.getFrozenStatus()== false){
            throw new IllegalArgumentException("Bank account is not frozen");
        }
        else{
            unfreeze.setFrozenStatus(false);
            System.out.println("This bank account has been unfrozen");
        }
    }
}
