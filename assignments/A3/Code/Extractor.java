import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Extractor.java. Implements feature extraction for collinear points in
 * two dimensional data.
 *
 * @author  Tanner Bonds (tjb0057@auburn.edu)
 * @author  Dean Hendrix (dh@auburn.edu)
 * @version 2018-03-1
 *
 */
public class Extractor {
   
   /** raw data: all (x,y) points from source data. */
   private Point[] points;
   
   /** lines identified from raw data. */
   private SortedSet<Line> lines;
  
   /**
    * Builds an extractor based on the points in the file named by filename. 
    */
   public Extractor(String filename) {
   
      try {
      
         Scanner in = new Scanner(new File(filename));
      
         int size = in.nextInt();
         points = new Point[size];
         
         int i = 0;
         while (in.hasNext()) {
         
            int xCoor = in.nextInt();
            int yCoor = in.nextInt();
         
            Point coor = new Point(xCoor, yCoor);
            points[i] = coor;
            i++;
         }
      }
      
      catch (Exception ex) {
      
         System.out.println("File not found");
      
      }
      
   }
  
   /**
    * Builds an extractor based on the points in the Collection named by pcoll. 
    *
    * THIS METHOD IS PROVIDED FOR YOU AND MUST NOT BE CHANGED.
    */
   public Extractor(Collection<Point> pcoll) {
      points = pcoll.toArray(new Point[]{});
   }
  
   /**
    * Returns a sorted set of all line segments of exactly four collinear
    * points. Uses a brute-force combinatorial strategy. Returns an empty set
    * if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesBrute() {
      lines = new TreeSet<Line>();
      Iterator iter = lines.iterator();
      
      double slope;
      double slope1;
      double slope2; 
      
      Point[] array = Arrays.copyOf(points, points.length);
      
      for (int i = 3; i < array.length; i++) {
         for (int j = 2; j < i; j++) {
            for (int k = 1; k < j; k++) {
               for (int l = 0; l < k; l++) {
                  
                  slope = array[i].slopeTo(array[j]);
                  slope1 = array[i].slopeTo(array[k]);
                  slope2 = array[i].slopeTo(array[l]);
                  
                  if ((slope == slope1) && (slope1 == slope2)) {
                  
                     Line line = new Line();
                     line.add(array[i]);
                     line.add(array[j]);
                     line.add(array[k]);
                     line.add(array[l]);
                     lines.add(line);
                  
                  
                  }
               }
            }
         }
      }
                  
      return lines;
      
   }
  
   /**
    * Returns a sorted set of all line segments of at least four collinear
    * points. The line segments are maximal; that is, no sub-segments are
    * identified separately. A sort-and-scan strategy is used. Returns an empty
    * set if there are no qualifying line segments.
    */
   public SortedSet<Line> getLinesFast() {
      lines = new TreeSet<Line>();
      Iterator iter1 = lines.iterator();
      
      
      Point[] array = Arrays.copyOf(points, points.length);
   
      for (int i = 0; i < points.length; i++) {
      
         int eq = 0;
      
         Arrays.sort(array, points[i].slopeOrder);
      
      
         for (int j = 0; j < points.length - 1; j = eq + j) {
            
            int count = 0;
            eq = 0;
            
            while (((j + count) < points.length) && (points[i].slopeOrder.compare(array[j], array[j + count]) == 0)) {
            
               eq++;
               count++;
            
            }
         
            if (eq > 2) {
            
               Line line = new Line();
               line.add(points[i]);
            
               for (int index = 0; index < eq; index++) {
               
                  line.add(array[j + index]);
               
                  
               }
               
               lines.add(line);
               
            }
         }
      }
      return lines;
      
   }
   
}
