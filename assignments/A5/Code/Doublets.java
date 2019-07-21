import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

import java.util.stream.Collectors;

/**
 * Provides an implementation of the WordLadderGame interface. The lexicon
 * is stored as a HashSet of Strings.
 *
 * @author Tanner Bonds (tjb0057@auburn.edu)
 * @author Dean Hendrix (dh@auburn.edu)
 * @version 2018-04-12
 */
public class Doublets implements WordLadderGame {

   // The word list used to validate words.
   // Must be instantiated and populated in the constructor.
   private HashSet<String> lexicon;


   /**
    * Instantiates a new instance of Doublets with the lexicon populated with
    * the strings in the provided InputStream. The InputStream can be formatted
    * in different ways as long as the first string on each line is a word to be
    * stored in the lexicon.
    */
   public Doublets(InputStream in) {
      try {
         lexicon = new HashSet<String>();
         Scanner s =
            new Scanner(new BufferedReader(new InputStreamReader(in)));
         while (s.hasNext()) {
            String str = s.next();
            /////////////////////////////////////////////////////////////
            // INSERT CODE HERE TO APPROPRIATELY STORE str IN lexicon. //
            /////////////////////////////////////////////////////////////
            lexicon.add(str.toLowerCase());
            s.nextLine();
         }
         in.close();
      }
      catch (java.io.IOException e) {
         System.err.println("Error reading from InputStream.");
         System.exit(1);
      }
   }


   //////////////////////////////////////////////////////////////
   // ADD IMPLEMENTATIONS FOR ALL WordLadderGame METHODS HERE  //
   //////////////////////////////////////////////////////////////

/**Allows for implementation of nodes.*/

   public class Node {
   
      String location;
      Node previous;
   
      /**Constructor for Nodes.*/
   
      public Node(String str, Node prev) {
      
         str = location;
         previous = prev;
      
      }
   }



/**
    * Returns the Hamming distance between two strings, str1 and str2. The
    * Hamming distance between two strings of equal length is defined as the
    * number of positions at which the corresponding symbols are different. The
    * Hamming distance is undefined if the strings have different length, and
    * this method returns -1 in that case. See the following link for
    * reference: https://en.wikipedia.org/wiki/Hamming_distance
    *
    * @param  str1 the first string
    * @param  str2 the second string
    * @return      the Hamming distance between str1 and str2 if they are the
    *                  same length, -1 otherwise
    */

    




   public int getHammingDistance(String str1, String str2) {
   
      int distance = 0;
   
      if (str1.length() != str2.length()) {
      
         return -1;
      
      }
      
      else {
         for (int a = 0; a < str1.length(); a++) {
            if (str1.charAt(a) != str2.charAt(a)) {
            
               distance++;
            
            }
         }
      }
      return distance;
   }
   
   
   /**
   * Returns a minimum-length word ladder from start to end. If multiple
   * minimum-length word ladders exist, no guarantee is made regarding which
   * one is returned. If no word ladder exists, this method returns an empty
   * list.
   *
   * Breadth-first search must be used in all implementing classes.
   *
   * @param  start  the starting word
   * @param  end    the ending word
   * @return        a minimum length word ladder from start to end
   */

   public List<String> getMinLadder(String start, String end) {
      
      List<String> empty = new ArrayList<>();
      List<String> list = new ArrayList<String>();
      Deque<Node> que = new ArrayDeque<>();
      TreeSet<String> set = new TreeSet<>();
   
      if (start.equals(end)) {
      
         list.add(start);
         return list;
      
      }
      
               
      
      set.add(start);
      que.addLast(new Node(start, null));
   
      while (!que.isEmpty()) {
      
         Node node = que.removeFirst();
         String location = node.location;
      
                  
         for (String str1 : getNeighbors(location)) {
         
            if (!set.contains(str1)) {
            
               set.add(str1);
               que.addLast(new Node(str1, node));
            
            }
         
            if (str1.equals(end)) {
            
               Node node1 = que.removeLast();
            
               while (node1 != null) {
               
                  list.add(0, node1.location);
                  node1 = node1.previous;
               
               }
               
            }
         }
      }
      return list;
   }

/**
    * Returns all the words that have a Hamming distance of one relative to the
    * given word.
    *
    * @param  word the given word
    * @return      the neighbors of the given word
    */
    
   public List<String> getNeighbors(String word) {
   
      List<String> list = new ArrayList<String>();
      List<String> empty = new ArrayList<>();
   
      if (word == null) {
      
         return empty;
      
      }
   
      for (String str : lexicon) {
         if (getHammingDistance(word, str) == 1) {
         
            list.add(str);
         
         }
      }
      return list;
   }

/**
    * Returns the total number of words in the current lexicon.
    *
    * @return number of words in the lexicon
    */
   public int getWordCount() {
   
      return lexicon.size();
   
   }

/**
    * Checks to see if the given string is a word.
    *
    * @param  str the string to check
    * @return     true if str is a word, false otherwise
    */
    
   public boolean isWord(String str) {
   
      if (lexicon.contains(str)) {
      
         return true;
      
      }
      return false;
   }
   
    /**
    * Checks to see if the given sequence of strings is a valid word ladder.
    *
    * @param  sequence the given sequence of strings
    * @return          true if the given sequence is a valid word ladder,
    *                       false otherwise
    */
    
   public boolean isWordLadder(List<String> sequence) {
   
   
   
      if (sequence == null || sequence.isEmpty()) {
      
         return false;
      
      }
   
      for (int a = 0; a < sequence.size() - 1; a++) {
         if (getHammingDistance(sequence.get(a), sequence.get(a + 1)) != 1) {
            
            return false;
            
         }
      }
     
      for (int b = 0; b < sequence.size(); b++) {
         if (!lexicon.contains(sequence.get(b))) {
            
            return false;
         }
         
      }
      return true;
   }
}

