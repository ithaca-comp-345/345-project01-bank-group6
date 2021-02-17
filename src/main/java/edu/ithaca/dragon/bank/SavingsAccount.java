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
    public void compoundInterest() throws Exception{
        double balanceWithInterest = balance*interestRate*(1/(double)365);
        if(isAmountValid(balanceWithInterest)){
            throw new Exception("invalid amount");
        }
        else{
            balance = balanceWithInterest;
        }
    }
    public double getInterest(){
        return interest;
    }
    public double getBalance(){
        return super.getBalance();
    }

}