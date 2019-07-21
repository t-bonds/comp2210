import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Defines a library of selection methods on Collections.
 *
 * @author  Tanner Bonds (tjb0057@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2018-02-5
 *
 */
public final class Selector {

/**
 * Can't instantiate this class.
 *
 * D O   N O T   C H A N G E   T H I S   C O N S T R U C T O R
 *
 */
   private Selector() { }


   /**
    * Returns the minimum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the minimum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T min(Collection<T> coll, Comparator<T> comp) {
      
      if (coll == null || comp == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (coll.isEmpty()) {
      
         throw new NoSuchElementException();
      
      }
      
      
      
      if (coll.size() == 1) {
      
         return coll.iterator().next();
      
      }
      T min = coll.iterator().next();
      if (coll.size() >= 2 && coll.iterator().hasNext()) {
         
      
         for (T value : coll) {
         
            if (comp.compare(value, min) < 0) {
            
               min = value;
            
            }
         }
      }
      return min;
   }


   /**
    * Selects the maximum value in the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty, this method throws a
    * NoSuchElementException. This method will not change coll in any way.
    *
    * @param coll    the Collection from which the maximum is selected
    * @param comp    the Comparator that defines the total order on T
    * @return        the maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T max(Collection<T> coll, Comparator<T> comp) {
      if (coll == null || comp == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (coll.isEmpty()) {
      
         throw new NoSuchElementException();
      
      }
      
      
      
      if (coll.size() == 1) {
      
         return coll.iterator().next();
      
      }
      T max = coll.iterator().next();
      if (coll.size() >= 2 && coll.iterator().hasNext()) {
         
      
         for (T value : coll) {
         
            if (comp.compare(value, max) > 0) {
            
               max = value;
            
            }
         }
      }
      return max;
   
   }


   /**
    * Selects the kth minimum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth minimum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth minimum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth minimum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmin(Collection<T> coll, int k, Comparator<T> comp) {
      
               
      if (coll == null || comp == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (coll.isEmpty() || k > coll.size() || k < 1) {
      
         throw new NoSuchElementException();
      
      }
   
      ArrayList<T> coll1 = new ArrayList<T>(coll);
      java.util.Collections.sort(coll1, comp);
      int index = 0;
      int index1 = 0;
      int notDouble = coll.size();
   
      for (int i = 0; i < coll1.size() - 1; i++) {
      
         while (i < coll1.size() - 1
            && coll1.size() > 1 && coll1.get(i) == coll1.get(i + 1)) {
         
            coll1.remove(i);
            index++;
         
         
         }
      
      }
       
      index1 = notDouble - index;
   
      if (index1 < k) {
      
         throw new NoSuchElementException();
      
      }
   
      return coll1.get(k - 1);
   }


   /**
    * Selects the kth maximum value from the Collection coll as defined by the
    * Comparator comp. If either coll or comp is null, this method throws an
    * IllegalArgumentException. If coll is empty or if there is no kth maximum
    * value, this method throws a NoSuchElementException. This method will not
    * change coll in any way.
    *
    * @param coll    the Collection from which the kth maximum is selected
    * @param k       the k-selection value
    * @param comp    the Comparator that defines the total order on T
    * @return        the kth maximum value in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T kmax(Collection<T> coll, int k, Comparator<T> comp) {
      
      if (coll == null || comp == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (coll.isEmpty() || coll.size() < k || k < 1) {
      
         throw new NoSuchElementException();
      
      }
   
      ArrayList<T> coll1 = new ArrayList<T>(coll);
      java.util.Collections.sort(coll1, comp);
      int index = 0;
      int index1 = 0;
      int notDouble = coll.size();
   
      for (int i = 0; i < coll1.size() - 1; i++) {
      
         while (i < coll1.size() - 1
            && coll1.size() > 1 && coll1.get(i) == coll1.get(i + 1)) {
         
            coll1.remove(i);
            index++;
         
         
         }
      
      }
       
      index1 = notDouble - index;
   
      if (index1 < k) {
      
         throw new NoSuchElementException();
      
      }
   
      return coll1.get(index1 - k);
      
   }


   /**
    * Returns a new Collection containing all the values in the Collection coll
    * that are greater than or equal to low and less than or equal to high, as
    * defined by the Comparator comp. The returned collection must contain only
    * these values and no others. The values low and high themselves do not have
    * to be in coll. Any duplicate values that are in coll must also be in the
    * returned Collection. If no values in coll fall into the specified range or
    * if coll is empty, this method throws a NoSuchElementException. If either
    * coll or comp is null, this method throws an IllegalArgumentException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the range values are selected
    * @param low     the lower bound of the range
    * @param high    the upper bound of the range
    * @param comp    the Comparator that defines the total order on T
    * @return        a Collection of values between low and high
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> Collection<T> range(Collection<T> coll, T low, T high,
                                         Comparator<T> comp) {
      ArrayList<T> coll1 = new ArrayList<T>(coll);
      ArrayList<T> range = new ArrayList<T>(0);
      int index = 0;
   
      if (coll == null || comp == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (coll.isEmpty()) {
      
         throw new NoSuchElementException();
      
      }
   
               
      if (coll.size() <= 2 && comp.compare(low, high) <= 0) {
         
      
         for (T value : coll1) {
         
            if (comp.compare(value, low) >= 0 && comp.compare(value, high) <= 0) {
            
               range.add(value);
               index++;
            }
         }
      }
      
      for (int i = range.size() - 1; index - 1 < i; i--) {
      
         range.remove(i);
      
      }
      
      if (range.size() == 0) {
      
         throw new NoSuchElementException();
      
      }
               
      return range;
   }


   /**
    * Returns the smallest value in the Collection coll that is greater than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the ceiling value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the ceiling value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T ceiling(Collection<T> coll, T key, Comparator<T> comp) {
      
      if (coll == null || comp == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (coll.isEmpty()) {
      
         throw new NoSuchElementException();
      
      }
   
      T ceil = coll.iterator().next();
      int i = 0;
      if (coll.iterator().hasNext()) {
      
         for (T value : coll) {
         
            if (comp.compare(value, ceil) > 0) {
            
               ceil = value;
            
            } 
         }
      }
   
      for (T value : coll) {
         if (comp.compare(value, ceil) <= 0 && comp.compare(value, key) >= 0) {
         
            ceil = value;
            i++;
         }
      }
      
      if (i == 0) {
      
         throw new NoSuchElementException();
      
      }
      return ceil;
   }


   /**
    * Returns the largest value in the Collection coll that is less than
    * or equal to key, as defined by the Comparator comp. The value of key
    * does not have to be in coll. If coll or comp is null, this method throws
    * an IllegalArgumentException. If coll is empty or if there is no
    * qualifying value, this method throws a NoSuchElementException. This
    * method will not change coll in any way.
    *
    * @param coll    the Collection from which the floor value is selected
    * @param key     the reference value
    * @param comp    the Comparator that defines the total order on T
    * @return        the floor value of key in coll
    * @throws        IllegalArgumentException as per above
    * @throws        NoSuchElementException as per above
    */
   public static <T> T floor(Collection<T> coll, T key, Comparator<T> comp) {
      
      if (coll == null || comp == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (coll.isEmpty()) {
      
         throw new NoSuchElementException();
      
      }
   
      T floor = coll.iterator().next();
      int i = 0;
      if (coll.iterator().hasNext()) {
      
         for (T value : coll) {
         
            if (comp.compare(value, floor) < 0) {
            
               floor = value;
            
            
            } 
         }
      }
   
      for (T value : coll) {
      
         if (comp.compare(value, floor) >= 0 && comp.compare(value, key) <= 0) {
         
            floor = value;
            i++;
         }
      }
   
   
      if (i == 0) {
      
         throw new NoSuchElementException();
      
      }
      return floor;
   }
}