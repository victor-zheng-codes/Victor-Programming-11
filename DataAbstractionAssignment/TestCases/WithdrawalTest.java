import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

//Test code for Withdraw.toString()
public class WithdrawalTest {
    Withdraw myTest;
    String test = "";

    @Before
    public void setup(){
        //public Date(int year,int month,int date,int hrs,int min, int sec)
        myTest = new Withdraw(200.0, new Date(110,0,23,1,0,0),Customer.CHECKING);
    }

    @Test
    public void testWithdrawToString(){
        //Print out the length of the test
        System.out.println(test.length());
        //Check that test is empty
        assertTrue(test.isEmpty());
        //Assign variable test to the toString method
        String test = myTest.toString();
        System.out.println(myTest);

        //AssertFalse that test is not empty
        assertFalse(test.isEmpty());
    }
}
