public class Client{
    private BankAccount Checking;
    private BankAccount Savings;

    public Client(){
        Checking = null;
        Savings = null;
    }

    public void setChecking(BankAccount c){
        Checking = c;
    }

    public void setSavings(BankAccount s){
        Savings = s;
    }

    public BankAccount getChecking(){
        return Checking;
    }

    public BankAccount getSavings(){
        return Savings;
    }

}