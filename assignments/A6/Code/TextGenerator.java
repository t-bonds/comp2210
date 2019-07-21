import java.io.File;
import java.io.IOException;

/**
 * TextGenerator.java. Creates an order K Markov model of the supplied source
 * text, and then outputs M characters generated according to the model.
 *
 * @author     Your Name (you@auburn.edu)
 * @author     Dean Hendrix (dh@auburn.edu)
 * @version    2018-04-17
 *
 */
public class TextGenerator {

   /** Drives execution. */
   public static void main(String[] args) {
   
      if (args.length < 3) {
         System.out.println("Usage: java TextGenerator k length input");
         return;
      }
   
      // No error checking! You may want some, but it's not necessary.
      int kInt = Integer.parseInt(args[0]);
      int mInt = Integer.parseInt(args[1]);
      if ((kInt < 0) || (mInt < 0)) {
         System.out.println("Error: Both K and M must be non-negative.");
         return;
      }
   
      File text;
      try {
         text = new File(args[2]);
         if (!text.canRead()) {
            throw new Exception();
         }
      }
      catch (Exception e) {
         System.out.println("Error: Could not open " + args[2] + ".");
         return;
      }
   
   
      // instantiate a MarkovModel with the supplied parameters and
      // generate sample output text ...
   
   
   
      String result = "";
      MarkovModel model = new MarkovModel(kInt, text);
      String random = model.getRandomKgram();
      int integer = 0;
      
      result += random;
      while (integer < mInt - kInt) {
         result += model.getNextChar(random);
         integer++;
         random = result.substring(integer, integer + kInt);
      }
      System.out.println(result);
   }
}
   
   

