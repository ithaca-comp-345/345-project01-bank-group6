package edu.ithaca.dragon.bank;

public interface BankingInterface{
    public void withdraw(BankAccount account, double amount);
    public void deposit(BankAccount account, double amount);
    public void transfer(BankAccount lender, BankAccount recipient, double amount);
    public boolean confirmUser(BankAccount account, String clientInfo);
    public String displayTransactionHistory(BankAccount account);

}