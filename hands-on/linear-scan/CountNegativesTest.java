import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class CountNegativesTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

   @Test public void countNegativesTest() {
   
      int[] a = {-1, -4, 0, 3, 9,};
   
      Assert.assertEquals(2, a.countNegatives());
   
   
   }



}
