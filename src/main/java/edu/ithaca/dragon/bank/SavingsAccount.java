package edu.ithaca.dragon.bank;

public class SavingsAccount extends BankAccount{

    private double dailyMaxWithdrawalLimit;
    private double interestRate;
    private double interest;

    public SavingsAccount(String email, double startingBalance, double dailyMaxWithdrawalLimitIn, double ir){
        super(email, startingBalance);
        dailyMaxWithdrawalLimit = dailyMaxWithdrawalLimitIn;
        interestRate = ir;
    }
    public void compoundInterest(){
        interest= balance*interestRate;
        balance = balance+interest;
    }
    public double getInterest(){
        return interest;
    }

}