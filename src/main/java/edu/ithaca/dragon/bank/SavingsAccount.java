package edu.ithaca.dragon.bank;

public class SavingsAccount extends BankAccount{

    private double dailyMaxWithdrawalLimit;
    private double interestRate;
    private double interest;

    public SavingsAccount(String email, double startingBalance, int acctID){
        super(email, startingBalance,acctID);
        dailyMaxWithdrawalLimit = 500;
        interestRate = 0.01;
    }


    public void compoundInterest(){
        interest=balance*interestRate;
        balance += interest;
        super.getTransactionHistory().add("interest "+interest+" "+" balance: "+super.getBalance());

    }
    public double getInterest(){
        return interest;
    }
    public double getBalance(){
        return super.getBalance();
    }

}