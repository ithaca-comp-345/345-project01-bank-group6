package edu.ithaca.dragon.bank;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bank {
    List<ATM> atmList;
    List<Teller> tellerList;
    List<Client> clientList;
    List<Admin> adminList;
    List<BankAccount> accountList;

    public Bank(){}

    public void addATM(ATM toAdd){
        atmList.add(toAdd);
    }

    public void addTeller(Teller toAdd){
        tellerList.add(toAdd);
    }

    public void addClient(Client toAdd){
        clientList.add(toAdd);
    }

    public void addAdmin(Admin toAdd){
        adminList.add(toAdd);
    }

    public void addAccount(BankAccount toAdd){
       accountList.add(toAdd);
    }

    public List<BankAccount> getAccounts(){
        return accountList;
    }

    public static void main (String[] args) throws InsufficientFundsException {
        Scanner scan= new Scanner(System.in);
        System.out.println("Welcome to the bank!");
        System.out.println("Enter your email address: ");
        String myEmail= scan.nextLine();
        System.out.println("Enter the starting balance for your account:");
        String myBalance= scan.nextLine();
        double balance= Double.parseDouble(myBalance);
        System.out.println("Enter your account ID: ");
        String myID= scan.nextLine();
        int ID= Integer.parseInt(myID);
        BankAccount testAccount= new BankAccount(myEmail, balance, ID);
        ATM atm= new ATM();
        System.out.println("Would you like to withdraw or deposit?");
        String firstAction= scan.nextLine();
        if(firstAction.equals("withdraw")){
            System.out.println("How much would you like to withdraw?");
            String myamount= scan.nextLine();
            double amount= Double.parseDouble(myamount);
            atm.withdraw(testAccount, amount, false);
            System.out.println("Your balance is now " + testAccount.getBalance());
        }
        else if(firstAction.equals("deposit")){
            System.out.println("How much would you like to deposit?");
            String myamount= scan.nextLine();
            double amount= Double.parseDouble(myamount);
            atm.deposit(testAccount, amount);
            System.out.println("Your balance is now " + testAccount.getBalance());
        }
        System.out.println("Let's make another account! Enter your email: ");
        String email2= scan.nextLine();
        System.out.println("Enter the starting balance: ");
        String myBalance2= scan.nextLine();
        double balance2= Double.parseDouble(myBalance2);
        System.out.println("Enter your account ID: ");
        String myID2= scan.nextLine();
        int id2= Integer.parseInt(myID2);
        BankAccount testAccount2= new BankAccount(email2, balance2, id2);

        System.out.println("Transfer money from your new account to the old one. How much would you like to transfer?");
        String myTransferAmount= scan.nextLine();
        double transferAmount= Double.parseDouble(myTransferAmount);
        atm.transfer(testAccount2, testAccount, transferAmount);

        Admin admin1= new Admin();
        System.out.println("An Admin will now check all your accounts for suspicious activity. Any accounts with suspicious actiity will be frozen");
        ArrayList<BankAccount> bankAccounts= new ArrayList<BankAccount>();
        bankAccounts.add(testAccount);
        bankAccounts.add(testAccount2);
        ArrayList<BankAccount> susAccounts= admin1.susReportV2(bankAccounts);
        if(susAccounts.size()!=0){
            System.out.println("Suspicious activity has been found on the following accounts:");
            for(int i=0; i<susAccounts.size(); i++){
                System.out.println(susAccounts.get(i).getAccountID());
                Admin.freezeAccount(susAccounts.get(i));
            }
            System.out.println("These accounts have now been frozen. Would you like to unfreeze?");
            String answer= scan.nextLine();
            if(answer.equals("yes")){
                System.out.println("Enter the ID of the account to unfreeze:");
                String newID= scan.nextLine();
                int ID1= Integer.parseInt(newID);
                for(int i=0; i<susAccounts.size(); i++){
                    if(susAccounts.get(i).getAccountID()== ID1){
                        Admin.unfreezeAccount(susAccounts.get(i));
                    }
                }
            }
            else if(answer.equals("no")){
                System.out.println("Contact an administrator if you would like to unfreeze your accounts");
            }
        }
        }


    
    
}
