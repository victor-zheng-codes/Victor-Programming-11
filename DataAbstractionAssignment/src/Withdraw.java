import java.util.Date;

public class Withdraw {
    private double amount;
    private Date date;
    private String account;


    Withdraw(double amount, Date date, String account){
        this.amount = amount;
        this.date = date;
        this.account = account;
    }

    //Requires: nothing
    //Modifies: this
    //Effects: overwrites normal toString() method to print out what we want
    public String toString(){
        //your code here
        return ("Withdrawal of: $" + amount + " Date: " + date + " into account: " + account);

    }
}
