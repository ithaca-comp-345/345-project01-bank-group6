package edu.ithaca.dragon.bank;

import java.util.List;

public class Bank {
    List<ATM> atmList;
    List<Teller> tellerList;
    List<Client> clientList;
    List<Admin> adminList;
    List<BankAccount> accountList;

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
    
}
