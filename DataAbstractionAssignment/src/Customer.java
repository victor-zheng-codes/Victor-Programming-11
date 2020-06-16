import javax.security.sasl.SaslClient;
import java.util.ArrayList;
import java.util.Date;
//Remember to do specifications

public class Customer {
    private int accountNumber;
    //I added an initializer for these arraylists because they were giving NullPointerException
    private ArrayList<Deposit> deposits = new ArrayList<Deposit>();
    private ArrayList<Withdraw> withdraws = new ArrayList<Withdraw>();
    private double checkBalance;
    private double savingBalance;
    private double savingRate;
    private String name;
    public static final String CHECKING = "Checking";
    public static final String SAVING = "Saving";
    private final int OVERDRAFT = -100;

    Customer(){
        //create default constructor
        accountNumber = 0;
        checkBalance = 0;
        savingBalance = 0;
        savingRate = 0;
        name = "";
    }
    Customer(String name, int accountNumber, double checkDeposit, double savingDeposit){
        //constructor code here
        this.accountNumber = accountNumber;
        this.checkBalance = checkBalance + checkDeposit;
        this.savingBalance = savingBalance + savingDeposit;
        this.name = name;
    }

    //Requires: double, Date, String
    //Modifies: checkBalance, savingBalance, arrayList deposits
    //Effects: inserts a new deposits in arrayList deposits, adds amount to savingBalance or checkBalance
    public double deposit(double amt, Date date, String account){
        //your code here
        new Deposit(amt,date,account);
        if(account.equals(CHECKING)){
            this.checkBalance = this.checkBalance + amt;
            deposits.add(new Deposit(amt,date,account));
        }
        else if(account.equals(SAVING)){
            this.savingBalance = this.savingBalance + amt;
            deposits.add(new Deposit(amt,date,account));
        }
        return 0;
    }

    //Requires: double, Date, String
    //Modifies: checkBalance, savingBalance, arrayList withdraws
    //Effects: inserts a new withdraw in arrayList withdraws, subtracts amount from savingBalance or checkBalance
    public double withdraw(double amt, Date date, String account){
        //your code here
        new Withdraw(amt,date,account);
        if(account.equals(CHECKING)){
            //Check overdraft is false
            if(!checkOverdraft(amt,CHECKING)) {
                this.checkBalance = this.checkBalance - amt;
                withdraws.add(new Withdraw(amt, date, account));
            }
            else{
                System.out.println("OVERDRAFT error in Customer.withdraw() method");
            }
        }
        if(account.equals(SAVING)) {
            //check overdraft is false
            if(!checkOverdraft(amt,SAVING)) {
                this.savingBalance = this.savingBalance - amt;
                withdraws.add(new Withdraw(amt, date, account));
            }
            else{
                System.out.println("OVERDRAFT error in Customer.withdraw() method");
            }
        }
        return 0;
    }

    //Requires: double, String
    //Modifies: Nothing
    //Effects: Overdraft is when account goes below 0, or in this cass, -100. Checks that the checkBalance or
    //         savingBalance - amount is not less than OVERDRAFT.

    private boolean checkOverdraft(double amt, String account){
        //your code here
        if(account.equals(CHECKING)){
            if((checkBalance - amt) < OVERDRAFT){
                return true;
            }
        }
        else if(account.equals(SAVING)){
            if((savingBalance - amt) < OVERDRAFT){
                return true;
            }
        }
        return false;
    }
    //do not modify
    public void displayDeposits(){
        for(Deposit d : deposits){
            System.out.println(d);
        }
    }
    //do not modify
    public void displayWithdraws(){
        for(Withdraw w : withdraws){
            System.out.println(w);
        }
    }

    //Getter and Setters
    public ArrayList<Deposit> getDeposits() {
        return deposits;
    }

    public void setDeposits(ArrayList<Deposit> deposits) {
        this.deposits = deposits;
    }

    public ArrayList<Withdraw> getWithdraws() {
        return withdraws;
    }

    public void setWithdraws(ArrayList<Withdraw> withdraws) {
        this.withdraws = withdraws;
    }
}
