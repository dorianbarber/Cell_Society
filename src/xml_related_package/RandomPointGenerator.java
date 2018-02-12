package xml_related_package;

import java.util.Scanner;

/**
 * Purpose of this class is to generate random points
 * to be used in the excel file. Has no implications 
 * outside of generating print statements. 
 * 
 * In the advent of the xml file builder this program
 * is essentially useless. The XMLBuilder will create xml files
 * for any of the simulations. 
 * 
 * @author Dorian
 *
 */

public class RandomPointGenerator {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int points = scan.nextInt();
		int grid = scan.nextInt();
		scan.close();
		
		for(int i = 0; i < points; i++) {
			int x = (int)(Math.random()*grid);
			int y = (int)(Math.random()*grid);
			System.out.println("<point>" + x + " " + y + " " + "0</point>");
		}
	}
}
