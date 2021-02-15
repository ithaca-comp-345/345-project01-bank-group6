public class Client{
    private BankAccount Checking;
    private BankAccount Savings;
    private String username;
    private String password;
    private String email;

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

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
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