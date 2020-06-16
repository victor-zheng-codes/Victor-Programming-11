import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//test for Deposit.toString()
public class DepositTest {
    Deposit myTest;
    String test = "";

    @Before
    public void setup(){
        //public Date(int year,int month,int date,int hrs,int min, int sec)
        myTest = new Deposit(200.0, new Date(118,10,4,0,0,0),Customer.SAVING);
    }

    @Test
    public void testDepositToString(){
        //Print out the length of the test
        System.out.println(test.length());
        //Check that test is empty
        assertTrue(test.isEmpty());
        //Assign variable to test the toString method
        test = myTest.toString();
        System.out.println(myTest);

        //AssertFalse that test is not empty
        assertFalse(test.isEmpty());
    }

}

