package edu.ithaca.dragon.bank;

public class ATM extends BankingTool{
    
    public void deposit(BankAccount account, double amount){
        super.deposit(account,amount);
    }
    public void withdraw(BankAccount account, double amount,boolean wasTransfer) throws InsufficientFundsException{
        super.withdraw(account, amount, wasTransfer);
    }
    public void transfer(BankAccount lender, BankAccount recipient, double amount) throws InsufficientFundsException{
        super.transfer(lender,recipient,amount);
    };
    public boolean confirmUser(BankAccount account, String clientUsername, String clientPassword){
        //Are we calling isEmailValid?
        //And comparing account email to client email?
        // If so, I'll include a client parameter
        return false;
    };
    
    public boolean isAmountValid(double amount){
        
        return super.isAmountValid(amount);
    };


}