import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
      boardGame = new String[50][50];
      prevPosition = new Boolean[50][50];
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
         Scanner s = new Scanner(new FileReader(fileName));  
         while (s.hasNext()) {
         
            String str = s.nextLine();
            Scanner lineScan = new Scanner(str);
            lineScan.useDelimiter(" ");
            while (lineScan.hasNext()) {
            
               lexicon.add(lineScan.next().toLowerCase());
            
            }
         }
         
      }
      
      catch (Exception e) {
         throw new IllegalArgumentException("Error loading word list: " + fileName + ": " + e);
      }
      
      lexLoaded = true;         
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
   
            
      if (letterArray == null) {
      
         throw new IllegalArgumentException();
      
      }
      
      double boardSize = Math.sqrt(letterArray.length);
   
      
      if (boardSize % 1 > 0) {
      
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
   
            
      if (lexLoaded == false) {
      
         throw new IllegalStateException();
      
      }
      
      if (minimumWordLength < 1) {
      
         throw new IllegalArgumentException();
      
      }
   
   
           
   
      for (int a = 0; a < length; a++) {
         for (int b = 0; b < length; b++) {
         
            getWord(boardGame[a][b], a, b);
         
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
    
            
      if (lexLoaded == false) {
      
         throw new IllegalStateException();
      
      }
      
      if (minimumWordLength < 1) {
      
         throw new IllegalArgumentException();
      
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
   
            
      if (lexLoaded == false) {
      
         throw new IllegalStateException();
      
      }
      
      if (wordToCheck == null) {
      
         throw new IllegalArgumentException();
      
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
   
            
      if (lexLoaded == false) {
      
         throw new IllegalStateException();
      
      }
      
      if (prefixToCheck == null) {
      
         throw new IllegalArgumentException();
      
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
   
            
      if (lexLoaded == false) {
      
         throw new IllegalStateException();
      
      }
      
      if (wordToCheck == null) {
      
         throw new IllegalArgumentException();
      
      }
   
      path.clear();
      actualPath.clear();
   
            
      for (int a = 0; a < length; a++) {
         for (int b = 0; b < length; b++) {
         
            isItOnTheBoard(wordToCheck, boardGame[a][b], a, b);
            
            if (!actualPath.isEmpty()) {
               return actualPath;
            }
            path.clear();
            actualPath.clear();
         
         }
      }
      return path;
   }
   
   /**
   *
   *Finds words in the getValidWords Method.
   *@param word is the word that is being searched.
   *@param xCoor is the x coordinate.
   *@param yCoor is the y coordinate.
   *
   */
   
   private void getWord(String word, int xCoor, int yCoor) {
   
      
      
      if (!(isValidPrefix(word))) {
      
         return;
      
      }
      
      prevPosition[xCoor][yCoor] = true;
   
      
      if (isValidWord(word) && word.length() >= min) {
      
         words.add(word.toUpperCase());
      
      }
   
      if (isValidPrefix(word)) {
       
         for (int a = -1; a <= 1; a++) {
            for (int b = -1; b <= 1; b++) {
            
               int x = xCoor + a;
               int y = yCoor + b;
            
            
               if (x >= 0 && y >= 0 && !(prevPosition[x][y])
                     && x <= ((int) length - 1) && y <= ((int) length - 1)) {
                  prevPosition[x][y] = true; 
                  getWord(word + boardGame[x][y], x, y);    
                  prevPosition[x][y] = false;   
               }
            }
         }
      }     
      
      prevPosition[xCoor][yCoor] = false;
   }
   
   
   
   /**Recursion for isOnBoard.*/
   private void isItOnTheBoard(String s, 
      String check, int xCoor, int yCoor) {
   
      prevPosition[xCoor][yCoor] = true;
         
      if (!(isValidPrefix(s))) {
      
         return;
      
      }
      
      
      for (int a = -1; a <= 1; a++) {
         for (int b = -1; b <= 1; b++) {
            
            if (s.equals(check)) {
            
               return;
            
            }
            
            int x = xCoor + a;
            int y = yCoor + b;
         
            if (x >= 0 && y >= 0 && !(prevPosition[x][y])
                     && x <= (length - 1) && y <= (length - 1)) {
               prevPosition[x][y] = true;
               int v = x * length + y;
               path.add(v); 
               isItOnTheBoard(check, s + boardGame[x][y], x, y);    
               prevPosition[x][y] = false; 
               path.remove(path.size() - 1);
                    
            
            
            }
         }
      
      }  
      
      prevPosition[xCoor][yCoor] = false;
      return;
   }
   
   
   
   
}