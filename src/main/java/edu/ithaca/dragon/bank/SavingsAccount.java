package edu.ithaca.dragon.bank;

public class SavingsAccount extends BankAccount{

    private double dailyMaxWithdrawalLimit;
    private double interestRate;
    private double interest;

    public SavingsAccount(String email, double startingBalance){
        super(email, startingBalance);
        dailyMaxWithdrawalLimit = 500;
        interestRate = 0.01;
    }


    public void compoundInterest(){
        interest=balance*interestRate;
        balance += interest;

    }
    public double getInterest(){
        return interest;
    }
    public double getBalance(){
        return super.getBalance();
    }

}