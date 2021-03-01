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
                this.sAccounts=new ArrayList<SavingsAccount>();
                this.cAccounts=new ArrayList<CheckingAccount>();
            }
            else {
                throw new IllegalArgumentException("Password is invalid, must contain one capital, lowercase, number and special character while being longer than 7 characters and shorter than 32.")
            }
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create member.");
        }
    }

    public void addSavingsAccount(){

    }

    public void addCheckingAccount(){

    }

    public static boolean isPasswordValid(String password){
        if(password.length()<7 || password.length()>32){
            return false;
        }
        boolean hasUpper = false;
        for(int i=0; i<password.length();i++){
            if(Character.isUpperCase(password.charAt(i))){
                hasUpper=true;
                break;
            }

        }
        if(!hasUpper){
            return false;
        }
        boolean hasUnique = false;
        
        for(int i=0; i<password.length();i++){

        }
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

    public BankAccount getChecking(){
        return Checking;
    }

    public BankAccount getSavings(){
        return Savings;
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
}