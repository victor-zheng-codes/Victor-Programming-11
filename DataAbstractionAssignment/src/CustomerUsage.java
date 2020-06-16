//Class to test Customer usage
import java.util.Date;

public class CustomerUsage {
    public static void main(String[] args) {
        Customer newCustomer = new Customer("Josh",15364,200.0,300.0);

        /*
        Deposit of: $400.0 Date: Sun Nov 04 00:00:00 PDT 2018 into account: Checking
        Deposit of: $500.0 Date: Thu Aug 16 10:52:17 PDT 2018 into account: Saving
        */

        newCustomer.deposit(400.0, new Date(118, 10,4,0,0,0),Customer.CHECKING);
        newCustomer.deposit(500.0, new Date(118,7,16,10,52,17),Customer.SAVING);
        //newCustomer.withdraw(701, new Date(118,9,16,10,52,17),Customer.CHECKING);

        newCustomer.displayDeposits();
        newCustomer.displayWithdraws();

    }
}
