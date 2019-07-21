import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
*
*A program to implement a game engine for the game Boggle.
*
*@author Tanner Bonds - A4
*@version 3-27-18
*
*/

public class BoggleEngine implements WordSearchGame {


   private boolean lexLoaded;
   private TreeSet<String> lexicon; 
   private List<Integer> path;
   private List<Integer> actualPath;
   private int length;
   private int min;
   private SortedSet<String> words;
   private String[][] boardGame;
   private Boolean[][] prevPosition; 


/**Constructor for BoggleEngine.*/


   public BoggleEngine() {
   
      lexicon = new TreeSet<String>();
      words = new TreeSet<String>();
      path = new ArrayList<Integer>();
      actualPath = new ArrayList<Integer>();
      
   }





/**
    * Loads the lexicon into a data structure for later use. 
    * 
    * @param fileName A string containing the name of the file to be opened.
    * @throws IllegalArgumentException if fileName is null
    * @throws IllegalArgumentException if fileName cannot be opened.
    */
    
   public void loadLexicon(String fileName) {
   
   
      if (fileName == null) {
         throw new IllegalArgumentException();
      }
      
      try {
         Scanner s = 
            new Scanner(new BufferedReader(new FileReader(new File(fileName))));
        
         while (s.hasNext()) {
            String str = s.next();
            lexicon.add(s.next().toLowerCase());
            s.nextLine();
         }
         lexLoaded = true;
      }
      
      catch (Exception e) {
         throw new IllegalArgumentException("Error loading word list: " + fileName + ": " + e);
      }
   }

   
   
   
   
   
   
   
   
   /**
    * Stores the incoming array of Strings in a data structure that will make
    * it convenient to find words.
    * 
    * @param letterArray This array of length N^2 stores the contents of the
    *     game board in row-major order. Thus, index 0 stores the contents of board
    *     position (0,0) and index length-1 stores the contents of board position
    *     (N-1,N-1). Note that the board must be square and that the strings inside
    *     may be longer than one character.
    * @throws IllegalArgumentException if letterArray is null, or is  not
    *     square.
    */
   public void setBoard(String[] letterArray) {
   
      double boardSize = Math.sqrt(letterArray.length);
   
      if (letterArray == null || boardSize % 1 > 0) {
      
         throw new IllegalArgumentException();
      
      }
     
      length = (int) boardSize;
      prevPosition = new Boolean[length][length];
      boardGame = new String[length][length];
     
      int i = 0;
      for (int index = 0; index < length; index++) {
         for (int j = 0; j < length; j++) {
         
            prevPosition[index][j] = false;
            boardGame[index][j] = letterArray[i].toLowerCase();
            i++;
         
         
         }
      }
     
   }
   
   /**
    * Creates a String representation of the board, suitable for printing to
    *   standard out. Note that this method can always be called since
    *   implementing classes should have a default board.
    */
    
   public String getBoard() {
    
      String outcome = "";
    
      for (String[] string : boardGame) {
         for (String s : string) {
         
            outcome = outcome + s;
         }
      }
      return outcome;
    
   }
   
   /**
    * Retrieves all valid words on the game board, according to the stated game
    * rules.
    * 
    * @param minimumWordLength The minimum allowed length (i.e., number of
    *     characters) for any word found on the board.
    * @return java.util.SortedSet which contains all the words of minimum length
    *     found on the game board and in the lexicon.
    * @throws IllegalArgumentException if minimumWordLength < 1
    * @throws IllegalStateException if loadLexicon has not been called.
    */
    
   public SortedSet<String> getAllValidWords(int minimumWordLength) {
   
      words.clear();
   
      if (minimumWordLength < 1) {
      
         throw new IllegalArgumentException();
      
      }
   
      if (lexLoaded == false) {
      
         throw new IllegalStateException();
      
      }
   
      boolean[][] dejaVu = new boolean[length][length];
      TreeSet<String> set = new TreeSet<String>();
   
   
      for (int i = 0; i < length; i++) {
         for (int index = 0; index < length; index++) {
         
            getWord("", minimumWordLength, i , index, dejaVu, set);
         
         }
      }
      return words;
   }
   
  /**
   * Computes the cummulative score for the scorable words in the given set.
   * To be scorable, a word must (1) have at least the minimum number of characters,
   * (2) be in the lexicon, and (3) be on the board. Each scorable word is
   * awarded one point for the minimum number of characters, and one point for 
   * each character beyond the minimum number.
   *
   * @param words The set of words that are to be scored.
   * @param minimumWordLength The minimum number of characters required per word
   * @return the cummulative score of all scorable words in the set
   * @throws IllegalArgumentException if minimumWordLength < 1
   * @throws IllegalStateException if loadLexicon has not been called.
   */ 
    
   public int getScoreForWords(SortedSet<String> words, int minimumWordLength) {
    
      int score = 0;
    
      if (minimumWordLength < 1) {
      
         throw new IllegalArgumentException();
      
      }
   
      if (lexLoaded == false) {
      
         throw new IllegalStateException();
      
      }
   
      for (String s : words) {
      
         int length = s.length();
         score += (length - minimumWordLength) + 1;
      
      }
    
      return score;
   }
   
   /**
    * Determines if the given word is in the lexicon.
    * 
    * @param wordToCheck The word to validate
    * @return true if wordToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidWord(String wordToCheck) {
   
      if (wordToCheck == null) {
      
         throw new IllegalArgumentException();
      
      }
   
      if (lexLoaded == false) {
      
         throw new IllegalStateException();
      
      }
   
      return lexicon.contains(wordToCheck.toLowerCase());
   
   }
   
   /**
    * Determines if there is at least one word in the lexicon with the 
    * given prefix.
    * 
    * @param prefixToCheck The prefix to validate
    * @return true if prefixToCheck appears in lexicon, false otherwise.
    * @throws IllegalArgumentException if prefixToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public boolean isValidPrefix(String prefixToCheck) {
   
      if (prefixToCheck == null) {
      
         throw new IllegalArgumentException();
      
      }
   
      if (lexLoaded == false) {
      
         throw new IllegalStateException();
      
      }
   
   
      return lexicon.ceiling(prefixToCheck).startsWith(prefixToCheck);
   
   }
      
   /**
    * Determines if the given word is in on the game board. If so, it returns
    * the path that makes up the word.
    * @param wordToCheck The word to validate
    * @return java.util.List containing java.lang.Integer objects with  the path
    *     that makes up the word on the game board. If word is not on the game
    *     board, return an empty list. Positions on the board are numbered from zero
    *     top to bottom, left to right (i.e., in row-major order). Thus, on an NxN
    *     board, the upper left position is numbered 0 and the lower right position
    *     is numbered N^2 - 1.
    * @throws IllegalArgumentException if wordToCheck is null.
    * @throws IllegalStateException if loadLexicon has not been called.
    */
   public List<Integer> isOnBoard(String wordToCheck) {
   
      if (wordToCheck == null) {
      
         throw new IllegalArgumentException();
      
      }
   
      if (lexLoaded == false) {
      
         throw new IllegalStateException();
      
      }
   
      boolean[][] dejaVu = new boolean[length][length];
      ArrayList<Integer> list = new ArrayList<Integer>();
   
      for (int index = 0; index < length; index++) {
         for (int j = 0; j < length; j++) {
         
            isItOnTheBoard(index, j, "", wordToCheck, dejaVu, list);
         
         
         }
      }
      return list;
   }
   
   /**
   *
   *Finds words in the getValidWords Method.
   *@param word is the word that is being searched.
   *@param xCoor is the x coordinate.
   *@param yCoor is the y coordinate.
   *
   */
   
   private void getWord(String word, int min, int xCoor, int yCoor,
       boolean[][] prev, TreeSet<String> set) {
   
      prev[xCoor][yCoor] = true;
   
      word = word + boardGame[xCoor][yCoor];
      
      if (isValidWord(word) && word.length() >= min) {
      
         set.add(word);
      
      }
   
      if (isValidWord(word)) {
       
         for (int a = xCoor - 1; a <= xCoor + 1 && a < length; a++) {
            for (int b = yCoor - 1; b <= yCoor + 1 && b < length; b++) {
               if (a >= 0 && b >= 0 && !(prev[xCoor][yCoor])) {
                    
                  getWord(word, min, xCoor, yCoor, prev, set);    
                    
               }
            }
         }
      }     
      word = word.substring(0, word.length() - 1);
      prev[xCoor][yCoor] = false;
   }
   
   
   
   /**Recursion for isOnBoard.*/
   private void isItOnTheBoard(int xCoor, int yCoor, String s,
      String check, boolean[][] beenHereBefore, ArrayList<Integer> list) {
   
      beenHereBefore[xCoor][yCoor] = true;
      s = s + boardGame[xCoor][yCoor];
      if (s.equals(check)) {
         for (int index = 0; index < length; index++) {
            for (int j = 0; j < length; j++) {
               if (beenHereBefore[index][j] == true) {
                  list.add(index * length + j);
               }
            }
         }
      }
      if (check.startsWith(s)) {
         for (int a = xCoor - 1; a <= xCoor + 1 && a < length; a++) {
            for (int b = yCoor - 1; b <= yCoor + 1 && b < length; b++) {
               if (a >= 0 && b >= 0 && (!beenHereBefore[a][b])) {
                  isItOnTheBoard(a, b, s, check, beenHereBefore, list);
               }
            }
         }
      }  
      s = s.substring(0, s.length() - 1);
      beenHereBefore[xCoor][yCoor] = false;
   }
   
   
   
   
}