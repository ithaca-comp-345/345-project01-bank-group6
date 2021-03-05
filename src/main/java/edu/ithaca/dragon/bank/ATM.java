package edu.ithaca.dragon.bank;

public class ATM extends BankingTool{
    
    public void deposit(BankAccount account, double amount, boolean wasTransfer){
        super.deposit(account,amount, wasTransfer);
    }
    public void withdraw(BankAccount account, double amount,boolean wasTransfer) throws InsufficientFundsException{
        super.withdraw(account, amount, wasTransfer);
    }
    public void transfer(BankAccount lender, BankAccount recipient, double amount) throws InsufficientFundsException{
        super.transfer(lender,recipient,amount);
    };
    
    public boolean isAmountValid(double amount){
        
        return super.isAmountValid(amount);
    };

    public void checkBalance(BankAccount account){
        super.checkBalance(account);
    }


}