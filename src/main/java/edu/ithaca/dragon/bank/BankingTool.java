package edu.ithaca.dragon.bank;

import java.math.BigDecimal;
import java.util.ArrayList;

public abstract class BankingTool{
    public abstract void withdraw(BankAccount account, double amount) throws InsufficientFundsException;
    public abstract void deposit(BankAccount account, double amount);
    /**
      * @throws InsufficientFundsException
      * @post If amount is valid, withdraws amount from one bank account and deposits
      *       it in another
      */
    public void transfer(BankAccount lender, BankAccount recipient, double amount) throws InsufficientFundsException {
        if(isAmountValid(amount)){
            withdraw(lender,amount);
            deposit(recipient,amount);
        }
        else{
            throw new IllegalArgumentException("Amount is invalid");
        }

    }
    public abstract boolean confirmUser(BankAccount account, String clientUsername, String password);
    public void displayTransactionHistory(BankAccount account){
        ArrayList<String> transactions= account.getTransactionHistory();
        for(int i=0;i<transactions.size();i++){
            System.out.println(transactions.get(i));
        }
    };
    public boolean isAmountValid(double amount){
        if(BigDecimal.valueOf(amount).scale() > 2){
            return false;
        }
        else if(amount< 0){
            return false;
        }
        return true;
    };

}