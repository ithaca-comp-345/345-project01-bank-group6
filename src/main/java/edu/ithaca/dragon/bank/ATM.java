package edu.ithaca.dragon.bank;

public class ATM extends BankingTool{

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance. If amount is negative, throws IllegalArgumentException
     */
    public void withdraw (BankAccount account, double amount) throws InsufficientFundsException{
        if(isAmountValid(amount)){
            double balance = account.getBalance();
            if (amount <= balance){
                balance -= amount;
                account.setBalance(balance);
            }
            else if(amount > balance){
                throw new InsufficientFundsException("Not enough money");
            }
        }
        else{
            throw new IllegalArgumentException("Amount is invalid");
        }
    }
    /**
     * @post If amount is valid, adds the amount to the current balance of the bank account. 
     */
    public void deposit(BankAccount account, double amount){
        if(isAmountValid(amount)){
            double balance = account.getBalance();
            balance +=amount;
            account.setBalance(balance);
        }
        else{
            throw new IllegalArgumentException("Amount is invalid");
        }

    }
    public void transfer(BankAccount lender, BankAccount recipient, double amount) throws InsufficientFundsException{
        super.transfer(lender,recipient,amount);
    };
    public boolean confirmUser(BankAccount account, String clientInfo){
        //Are we calling isEmailValid?
        //And comparing account email to client email?
        // If so, I'll include a client parameter
        return false;
    };
    public String displayTransactionHistory(BankAccount account){
        //Not sure how we want to format transaction history
        return "";
    };
    public boolean isAmountValid(double amount){
        //I'm calling isAmountValid with a default implementation in BankingTool 
        //However, I have a setBalance function in BankAccount that should use it
        //How do we want to use this method in our implementation?
        //Should it just be a static account method or a static/nonstatic method in the abstract class?
        return super.isAmountValid(amount);
    };


}