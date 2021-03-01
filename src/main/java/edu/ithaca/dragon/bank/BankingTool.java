package edu.ithaca.dragon.bank;

import java.math.BigDecimal;
import java.util.ArrayList;

public abstract class BankingTool{
    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance. If amount is negative, throws IllegalArgumentException
     */
    public void withdraw (BankAccount account, double amount,boolean wasTransfer) throws InsufficientFundsException{
       if(account.getFrozenStatus()== false){ 
        if(isAmountValid(amount)){
            double balance = account.getBalance();
            if (amount <= balance){
                balance -= amount;
                if(isAmountValid(balance)){
                    account.setBalance(balance);
                    if(!wasTransfer)
                        account.getTransactionHistory().add("withdraw "+Double.toString(amount)+" balance: "+Double.toString(account.getBalance()));
                }
                else{
                    throw new IllegalArgumentException("Balance error");
                }
            }
            else if(amount > balance){
                throw new InsufficientFundsException("Not enough money");
            }
        }
    
        else{
            throw new IllegalArgumentException("Amount is invalid");
        }
       }
       else{
           throw new IllegalArgumentException("This account is frozen");
       }
    }
    /**
     * @post If amount is valid, adds the amount to the current balance of the bank account. 
     */
    public void deposit(BankAccount account, double amount){
      if(account.getFrozenStatus()== false){
        if(isAmountValid(amount)){
            double balance = account.getBalance();
            balance +=amount;
            account.setBalance(balance);
            account.getTransactionHistory().add("deposit "+amount+" balance: "+Double.toString(account.getBalance()));
        }
        else{
            throw new IllegalArgumentException("Amount is invalid");
        }
    }
    else{
        throw new IllegalArgumentException("This account is frozen");
    }
    }
    
    /**
      * @throws InsufficientFundsException
      * @post If amount is valid, withdraws amount from one bank account and deposits
      *       it in another
      */
    public void transfer(BankAccount lender, BankAccount recipient, double amount) throws InsufficientFundsException {
        if(lender.getFrozenStatus()== false && recipient.getFrozenStatus()== false){
        if(isAmountValid(amount)){
            withdraw(lender,amount,true);
            lender.getTransactionHistory().add("transfer "+Integer.toString(recipient.getAccountID())+" "+Double.toString(amount)+" balance: "+Double.toString(lender.getBalance()));
            deposit(recipient,amount);
            //potentially might want to change
            recipient.getTransactionHistory().add("deposit "+Double.toString(amount)+" balance: "+Double.toString(recipient.getBalance()));
        }
        else{
            throw new IllegalArgumentException("Amount is invalid");
        }
    }
        else{
            throw new IllegalArgumentException("One of these accounts is frozen");
        }
    }
    public abstract boolean confirmUser(BankAccount account, String clientUsername, String password);
    public void displayTransactionHistory(BankAccount account){
        ArrayList<String> transactions= account.getTransactionHistory();
        if(transactions.size()==0){
            System.out.println("No transactions have been made.");
        }
        else{
            for(int i=0;i<transactions.size();i++){
                System.out.println(transactions.get(i));
            }
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
    }

    public void checkBalance(BankAccount account){
        System.out.print("Current balance: "+account.getBalance());
    }

}