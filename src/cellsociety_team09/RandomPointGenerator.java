package cellsociety_team09;

import java.util.Scanner;

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
