package edu.ithaca.dragon.bank;

import java.math.BigDecimal;

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
    public abstract boolean confirmUser(BankAccount account, String clientInfo);
    public abstract String displayTransactionHistory(BankAccount account);
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