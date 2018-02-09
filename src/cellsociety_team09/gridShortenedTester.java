package cellsociety_team09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class gridShortenedTester {
	
	
	
	private static List<List<Integer>> shorten(List<List<Integer>> original){
		int size = original.size();
		
		List<List<Integer>> temp = original.subList(1, size - 1);
		
		System.out.println(temp.get(1).size());
		System.out.println(size);
		
		for(int i = 0; i < size - 2; i++) {
			List<Integer> row = temp.get(i).subList(1, size-1);
			temp.set(i, row);
		}
		
		return Collections.unmodifiableList(temp);
	}
	
	/**
	 * PROBLEM STATEMENT IS THAT WHEN SHORTEN IS CALLED MORE THAN ONCE
	 * (HERE IT IS CALLED TWICE) IT AFFECTS THE SIZE OF TEMPORARY SUBLIST 
	 * EXPLICITLY IN LINE 16. THE QUESTION IS HOW TO MAINTAIN THE SIZE
	 * BUT BEING ABLE TO PASS THESE SUBLISTS. 
	 * @param args
	 */
	
	public static void main(String[] args) {
		List<List<Integer>> example = new ArrayList<List<Integer>>();
		for(int i = 0; i < 42; i++) {
			example.add(new ArrayList<Integer>());
			for(int j = 0; j < 42; j++) {
				example.get(i).add(j);
			}
		}
		List<List<Integer>> boom = shorten(example);
		List<List<Integer>> boom2 = shorten(example);
		
		List<List<Integer>> testCase = shorten(example);
		
		for(List<Integer> lists : testCase) {
			for(int numb : lists) {
				System.out.print(numb + " ");
			}
			System.out.println();
		}
		
		
	}
}
