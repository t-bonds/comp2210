import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;


public class CountPositives {

   /**
    * Returns the number of positive values in the given Collection.
    */
   public static int countPositives(Collection<Integer> collection) {
      
      if (collection == null) {
         
         throw new IllegalArgumentException();  
         
         
      }
      
      if (collection.isEmpty()) {
         
         throw new NoSuchElementException();  
         
      }
      
      if (collection.size() == 1 && collection.iterator().next() < 0 ) {
         
         return collection.iterator().next();  
         
      }
      
      int positive = 0;
      if (collection.size() >= 2 && collection.iterator().hasNext()) {
         
         for (Integer T : collection) {
           
            if (T > 0) {
            
               positive++;
              
            }
         }  
      } 
      return positive;
   }
}
