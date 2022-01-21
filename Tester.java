import java.util.Scanner;
/**
 * Write a description of class Tester here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Tester
{
  public static void main(String[] args) {
      //Book redwall = new Book("Redwall", "Mice fighting rats", "Brian Jacques", 1977);
      //Book shades = new Book("Shade's Children", "Evil Dystopian Future", "Garth Nix", 1989);
      
      String name = "Jonathan Hempstreet";
      
      Scanner sc = new Scanner(name);
      
      String first = sc.next();
      String last = sc.next();
      
      System.out.println(first);
      System.out.println(last);
      
  }
}
