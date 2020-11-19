package de.hfu;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Scanner s = new Scanner(System.in);
	System.out.println("Enter a word in lower case.");
	String string = s.nextLine();
	System.out.println(string.toUpperCase());
    }
}
