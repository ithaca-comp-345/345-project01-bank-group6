package edu.ithaca.dragon.bank;

import java.util.ArrayList;

public class Client{
    private ArrayList<SavingsAccount> sAccounts;
    private ArrayList<CheckingAccount> cAccounts;
    private String username;
    private String password;
    private String email;

    public Client(String username, String password, String email){
        if(BankAccount.isEmailValid(email)){
            if(isPasswordValid(password)){
                if(username.length()<7){
                    this.sAccounts=new ArrayList<SavingsAccount>();
                    this.cAccounts=new ArrayList<CheckingAccount>();
                    this.username = username;
                    this.password = password;
                    this.email = email;
                }
                else throw new IllegalArgumentException("Username too short.");
            }
            else {
                throw new IllegalArgumentException("Password is invalid, must contain one capital, lowercase, number and special character while being longer than 7 characters and shorter than 32.");
            }
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create member.");
        }
    }

    public void addSavingsAccount(SavingsAccount sa){
        sAccounts.add(sa);
    }

    public void addCheckingAccount(CheckingAccount ca){
        cAccounts.add(ca);
    }

    public boolean confirmUser(String u, String p){
        if(u == username && p == password){
            return true;
        }
        else return false;
    }

    public static boolean isPasswordValid(String password){
        if(password.length()<7 || password.length()>32){
            return false;
        }

        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasNumber = false;
        for(int i=0; i<password.length();i++){
            if(Character.isUpperCase(password.charAt(i))){
                hasUpper=true;
            }
            if(Character.isLowerCase(password.charAt(i))){
                hasLower = true;
            }
            if(Character.isDigit(password.charAt(i))){
                hasNumber = true;
            }

        }
        if(!hasUpper || !hasLower || !hasNumber){
            return false;
        }


        char[] uniqueChar = {'!','?','@','#','%'};
        int count = 0;
        for(int i=0; i<uniqueChar.length;i++){
            char u = uniqueChar[i];
            count += password.chars().filter(ch -> ch == u).count();
        }
        if (count==0){
            return false;
        }

        else return true;
    }

    public void setEmail(String email) {
        if(BankAccount.isEmailValid(email)){
            this.email = email;
        }
    }

    public void setPassword(String password) {
        if(isPasswordValid(password)){
            this.password = password;
        }
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    public SavingsAccount getSavingsAccountAt(int i){
        return sAccounts.get(i);
    }

    public CheckingAccount gCheckingAccountAt(int i){
        return cAccounts.get(i);
    }
}