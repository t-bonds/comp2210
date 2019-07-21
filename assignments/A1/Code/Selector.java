import java.util.Arrays;

/**
* Defines a library of selection methods
* on arrays of ints.
*
* @author   Tanner Bonds (tjb0057@auburn.edu)
* @author   Dean Hendrix (dh@auburn.edu)
* @version  2018-01-17
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
    * Selects the minimum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
    
   public static int min(int[] a) {
      
      if (a == null || a.length == 0) {
      
         throw new IllegalArgumentException();
         
      }
         
      int num = a[0];
      for (int i = 0; i < a.length; i++) {
         
         if (num > a[i]) {
            
            num = a[i];
            
         }
         
         
      }         
      
      return num;
      
   }


   /**
    * Selects the maximum value from the array a. This method
    * throws IllegalArgumentException if a is null or has zero
    * length. The array a is not changed by this method.
    */
    
   public static int max(int[] a) {
      
      if (a == null || a.length == 0) {
      
         throw new IllegalArgumentException();
      
      }
      
      int num = a[0];
      for (int i = 0; i < a.length; i++) {
         
          
          
         if (num < a[i]) {
            
            num = a[i];
            
         }
         
         
      }         
      
      return num;  
      
   }


   /**
    * Selects the kth minimum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth minimum value. Note that there is no kth
    * minimum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmin(int[] a, int k) {
      
      int index0 = 0;
      int index1 = 1;
      int notDouble = 0;
       
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
      
         throw new IllegalArgumentException(); 
      
      }
                
      if (k == 1 && a.length == 1) {
      
         return a[0];
      
      }
   
      int[] a1 = Arrays.copyOf(a, a.length);
      Arrays.sort(a1);
   
      
               
      if (a[0] != a[1] && k == 2 && a.length == 2) {
      
         return a1[1];
      
      }
      
            
      for (int num : a1) {
      
         if (a1[index1] == a1[index1 + 1]) {
         
            notDouble++;
         
         }
         
      }
      
      int trueValues = a1.length - notDouble;
      while (index1 < a.length) {         
      
         if (a1[index1] == a1[index0]) {
         
            index1++;
         
         }
         
         else {
            index0++;
            a1[index0] = a1[index1];
            index1++;
         }
      }
      
      
      
      int kMin = a1[k - 1];
      return kMin;
      
   }


   /**
    * Selects the kth maximum value from the array a. This method
    * throws IllegalArgumentException if a is null, has zero length,
    * or if there is no kth maximum value. Note that there is no kth
    * maximum value if k < 1, k > a.length, or if k is larger than
    * the number of distinct values in the array. The array a is not
    * changed by this method.
    */
   public static int kmax(int[] a, int k) {
      
      int index0 = 0;
      int index1 = 1;
      int notDouble = 0;
      if (a == null || a.length == 0 || k < 1 || k > a.length) {
      
         throw new IllegalArgumentException();
      
      }
   
      if (k == 1 && a.length == 1) {
      
         return a[0];
      
      }
   
      int[] a1 = Arrays.copyOf(a, a.length);
      Arrays.sort(a1);
   
   
   
      if (a[0] != a[1] && k == 2 && a.length == 2) {
      
         return a1[1];
      
      }
   
             
      for (int num : a1) {
      
         if (a1[index1] == a1[index1 + 1]) {
         
            notDouble++;
         
         }
         
      }
      
      int trueValues = a1.length - notDouble; 
      
      if (k > trueValues) {
      
         throw new IllegalArgumentException();
         
      } 
      while (index1 < a.length) {         
      
         if (a1[index1] == a1[index0]) {
         
            index1++;
         
         }
         
         else {
            index0++;
            a1[index0] = a1[index1];
            index1++;
         }
      }
   
      int kMax = a1[trueValues - k];
      return kMax;
      
   }


   /**
    * Returns an array containing all the values in a in the
    * range [low..high]; that is, all the values that are greater
    * than or equal to low and less than or equal to high,
    * including duplicate values. The length of the returned array
    * is the same as the number of values in the range [low..high].
    * If there are no qualifying values, this method returns a
    * zero-length array. Note that low and high do not have
    * to be actual values in a. This method throws an
    * IllegalArgumentException if a is null or has zero length.
    * The array a is not changed by this method.
    */
   public static int[] range(int[] a, int low, int high) {
      
      if (a == null || a.length == 0) {
      
         throw new IllegalArgumentException();
      
      }
      
      if (low <= high) {
      
         if (a.length == 1 && high <= a[0] && low >= a[0]) {
         
            return a;
         
         }
       
      }
         
      int[] b = new int[a.length];
      int index = 0;
      for (int num : a) {
           
         if (num <= high && num >= low) {
            
            b[index] = num;
            index++;
            
                
         } 
      }
      
      return Arrays.copyOf(b, index);
   
   }
   


   /**
    * Returns the smallest value in a that is greater than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int ceiling(int[] a, int key) {
      
      
      if (a == null || a.length == 0) {
      
         throw new IllegalArgumentException();
      
      }
      
      
      if (a[0] >= key && a.length == 1) {
      
         return a[0];
      
      }
       
      
      
      int ceil = 100;
      int i = 0;
      for (int num : a) {
         
         if (num >= key && num <= ceil) {
         
            ceil = num;
            i++;
         }
             
      }
      
      if (i == 0) {
      
         throw new IllegalArgumentException();
      
      }
      
      return ceil;
   }


   /**
    * Returns the largest value in a that is less than or equal to
    * the given key. This method throws an IllegalArgumentException if
    * a is null or has zero length, or if there is no qualifying
    * value. Note that key does not have to be an actual value in a.
    * The array a is not changed by this method.
    */
   public static int floor(int[] a, int key) {
      
      if (a == null || a.length == 0) {
      
         throw new IllegalArgumentException();
      
      }
   
       
      if (a[0] <= key && a.length == 1) {
      
         return a[0];
      
      }
      
      
      
      int floor = -100;
      int i = 0;
      for (int num : a) {
      
         if (num <= key && num >= floor) {
         
            floor = num;
            i++;
         }
            
      }
      
      if (i == 0) {
      
         throw new IllegalArgumentException(); 
      
      }
      
      return floor;
   }

}
