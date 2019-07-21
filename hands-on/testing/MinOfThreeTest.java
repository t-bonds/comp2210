import org.junit.Assert;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class MinOfThreeTest {


   /** Fixture initialization (common initialization
    *  for all tests). **/
   @Before public void setUp() {
   }

/**Test of the min1 method.*/

   @Test public void min1Test() {
   
      int a = 3;
      int b = 1;
      int c = 2;
      
   
      Assert.assertEquals("min1 test", b, MinOfThree.min1(a, b, c));
   
      int d = 0;
      Assert.assertEquals("min1 test 2", d, MinOfThree.min1(d, b, c));
   
      int e = -2;
      Assert.assertEquals("min1 test 3", e, MinOfThree.min1(d, b, e));
   
   }

/**Test of the min2 method.*/

   @Test public void min2Test() {
   
      int a = 3;
      int b = 1;
      int c = 2;
   
      Assert.assertEquals("min2 test", b, MinOfThree.min2(a, b, c));
      
      int d = 0;
      Assert.assertEquals("min2 test 2", d, MinOfThree.min1(d, b, c));
   
      int e = -2;
      Assert.assertEquals("min2 test 3", e, MinOfThree.min1(d, b, e));
   
      
   
   }

}