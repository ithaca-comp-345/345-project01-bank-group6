package edu.ithaca.dragon.bank;

import java.math.BigDecimal;
import java.util.ArrayList;

public class BankAccount {

    private String email;

    //format for elements is a space separated transaction
    private ArrayList<String> transactionHistory;

    private int accountID;

    protected double balance;

    protected boolean frozenStatus;



    /**
     * @throws IllegalArgumentException if email is invalid or if amount is invalid
     */
    public BankAccount(String email, double startingBalance, int acctID){
        if (isEmailValid(email) /*&& isAmountValid(startingBalance*/){
            this.email = email;
            //this.balance = startingBalance;
            this.transactionHistory=new ArrayList<String>();
            this.accountID=acctID;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }

        if(isAmountValid(startingBalance)){
            this.balance= startingBalance;
        }
        else{
            throw new IllegalArgumentException("Starting Balance: " + startingBalance + " is invalid, cannot create account");
        }
        frozenStatus= false;
    }

    public void setBalance(double amount){
        if(isAmountValid(amount)){
            balance=amount;
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }


    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1 || email.indexOf('.')== -1){
            return false;
        }
        //invalid if string is empty
        else if (email.isEmpty()){
            return false;
        }
        //invalid if '-' is in address. This is low boundary
        else if (email.indexOf('-') != -1){
            return false;
        }
        //invalid if # of characters after last '.' is 2 or less
        else if (email.length() - email.indexOf('.') <= 2){
            return false;
        }
        //invalid if '#' is before '@'
        
        else if (email.indexOf('#') != -1 || email.indexOf('*') != -1 || email.indexOf('$') != -1 || email.indexOf('!') != -1 || email.indexOf('^') != -1){
            return false;
        }
        //invalid if '..' is found. This could be for any 2 symbols
        else if (email.contains("..") || email.contains("@.")){
            return false;
        }

        else if(email.charAt(email.length() -2) == '.'){
            return false;
        }

        else if(email.indexOf('.') != -1 && email.indexOf('@') > email.lastIndexOf('.')){
            return false;
        }
        else {
            return true;
        }
    }

    public static boolean isAmountValid (double amount){
        if(BigDecimal.valueOf(amount).scale() > 2){
            return false;
        }
        else if(amount< 0){
            return false;
        }
        return true;
    }

<<<<<<< HEAD
    public boolean getFrozenStatus(){
        return frozenStatus;
    }

    public boolean setFrozenStatus(boolean status){
        frozenStatus= status;
        return frozenStatus;
    }
=======
    public ArrayList<String> getTransactionHistory(){
        return transactionHistory;
    }

    public int getAccountID(){return accountID;}
>>>>>>> 4ddbb8bf15b0dfab6691e6d73621465b4e475f9c
}
