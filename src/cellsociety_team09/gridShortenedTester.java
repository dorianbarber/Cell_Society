package cellsociety_team09;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class gridShortenedTester {
	private static int size;
	
	
	private static List<List<Integer>> shorten(List<List<Integer>> original){
		
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
	 * 
	 *  
	 *  SOLUTION:
	 *  DO NOT USE SUBLISTS IN ANY SORT OF LOOPING CONTEXT, TENDS TO THROW BUGS
	 *  AND EXCEPTIONS THAT ARE ANNOYING TO HANDLE. INSTEAD, CREATE A NEW 2D
	 *  ARRAY AND ADD ONLY THE ELEMENTS FROM 1 TO SIZE - 1.
	 * @param args
	 */
	
	public static void main(String[] args) {
		List<List<Integer>> example = new ArrayList<List<Integer>>();
		size = 42;
		for(int i = 0; i < size; i++) {
			example.add(new ArrayList<Integer>());
			for(int j = 0; j < size; j++) {
				example.get(i).add(j);
			}
		}
		List<List<Integer>> boom = new ArrayList<List<Integer>>(example);
		
		boom = shorten(boom);
		
		List<List<Integer>> testCase = new ArrayList<List<Integer>>(example);
		
		for(List<Integer> lists : boom) {
			for(int numb : lists) {
				System.out.print(numb + " ");
			}
			System.out.println();
		}
		
		
	}
}
