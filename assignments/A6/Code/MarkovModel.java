import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

/**
 * MarkovModel.java Creates an order K Markov model of the supplied source
 * text. The value of K determines the size of the "kgrams" used to generate
 * the model. A kgram is a sequence of k consecutive characters in the source
 * text.
 *
 * @author     Tanner Bonds (tjb0057@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2018-04-17
 *
 */
public class MarkovModel {

   // Map of <kgram, chars following> pairs that stores the Markov model.
   private HashMap<String, String> model;

   // add other fields as you need them ...
   String firstStr;

   /**
    * Reads the contents of the file sourceText into a string, then calls
    * buildModel to construct the order K model.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int kInt, File sourceText) {
      model = new HashMap<>();
      try {
         String text = new Scanner(sourceText).useDelimiter("\\Z").next();
         buildModel(kInt, text);
      }
      catch (IOException e) {
         System.out.println("Error loading source text: " + e);
      }
   }


   /**
    * Calls buildModel to construct the order K model of the string sourceText.
    *
    * DO NOT CHANGE THIS CONSTRUCTOR.
    *
    */
   public MarkovModel(int kInt, String sourceText) {
      model = new HashMap<>();
      buildModel(kInt, sourceText);
   }


   /**
    * Builds an order K Markov model of the string sourceText.
    */
   private void buildModel(int kInt, String sourceText) {
      int a = 0;
      int b = 0;
   
      firstStr = sourceText.substring(0, kInt);
   
      while (a + kInt <= sourceText.length()) {
      
      
         String e = "";
         String gram = sourceText.substring(a, a + kInt);
      
         if (!model.containsKey(gram)) {
         
         
            int c = kInt;
         
            while (b + c < sourceText.length()) {
            
               String gr = sourceText.substring(b, b + c);
            
               if (b + kInt >= sourceText.length()) {
               
               
                  e += '\u0000';
               
               }
            
               if (gram.equals(gr)) {
               
                  e += sourceText.substring(b + c, b + c + 1);
               
               }
               
               b++;
               
            }
            
            model.put(gram, e);
            
         }
         
         b = 0;
         a++;
         
      }
   }


   /** Returns the first kgram found in the source text. */
   public String getFirstKgram() {
      return firstStr;
   }


   /** Returns a kgram chosen at random from the source text. */
   public String getRandomKgram() {
      int a = model.size();
      int b = 0;
      
      Random random = new Random();
      int i = random.nextInt(a);
      
      for (String s : model.keySet()) {
      
      
         if (i == b) {
         
            return s;
         
         }
         
         b++;
         
      }
      
      return null;
      
   }


   /**
    * Returns the set of kgrams in the source text.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   public Set<String> getAllKgrams() {
      return model.keySet();
   }


   /**
    * Returns a single character that follows the given kgram in the source
    * text. This method selects the character according to the probability
    * distribution of all characters that follow the given kgram in the source
    * text.
    */
   public char getNextChar(String kgram) {
      Random random = new Random();
      String e = "";
      int a = 0;
      
      for (String s: model.keySet()) {
      
         if (s.equals(kgram)) {
         
            e = model.get(kgram);
            int b = e.length();
            
            
            if (b > 0) {
            
               a = random.nextInt(b) + 1;
               
            }
         }
      }
      
      int d = a - 1;
      
      if (!e.equals("")) {
      
         return e.charAt(d);
         
      }
      return '\u0000';
   }
   


   /**
    * Returns a string representation of the model.
    * This is not part of the provided shell for the assignment.
    *
    * DO NOT CHANGE THIS METHOD.
    *
    */
   @Override
    public String toString() {
      return model.toString();
   }

}
