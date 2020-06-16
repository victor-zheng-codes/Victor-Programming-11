import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;


//test class for Customer.deposit() and Customer.withdraw()
public class CustomerTests {
    Customer myTest;

    @Before
    public void setup(){
        myTest = new Customer("Thomas",1325,300.0, 200.0);
    }

    @Test
    public void testCustomerDeposit(){
        //Check that array list is empty
        assertEquals(myTest.getDeposits().size(),0);

        //Deposit some money into Checking account
        myTest.deposit(300.0, new Date(120,3,12,22,43,22),Customer.CHECKING);

        //Check that the size of the arrayList increased by 1
        assertEquals(myTest.getDeposits().size(),1);

        //Deposit some money into savings account
        myTest.deposit(200.0, new Date(116,3,15,13,34,33), Customer.SAVING);

        //Check that the size of the arrayList increased by 1
        assertEquals(myTest.getDeposits().size(), 2);

    }

    @Test
    public void testCustomerWithdraw(){
        //Check that arrayList is empty
        assertEquals(myTest.getWithdraws().size(), 0) ;

        //Withdraw some money from Savings account
        myTest.withdraw(300.0, new Date(115,11,5,22,43,33),Customer.SAVING);

        //Check that a new withdrawal is in the list
        assertEquals(myTest.getWithdraws().size(), 1);

        //Withdraw some money from Checking account
        myTest.withdraw(300.0, new Date(117,1,30,5,34,2),Customer.CHECKING);

        //Check that a new withdrawal is in the list
        assertEquals(myTest.getWithdraws().size(), 2);

        //Check OVERDRAFT by withdrawing some money from Checking account
        myTest.withdraw(300.0, new Date(119,2,1,3,12,54),Customer.CHECKING);
        //Check OVERDRAFT by withdrawing some money from Savings account
        myTest.withdraw(300.0, new Date(119,6,23,3,43,54),Customer.SAVING);

        //Since we are not adding anything, it should still only have 2 withdrawals inside
        assertEquals(myTest.getWithdraws().size(), 2);
    }
}
