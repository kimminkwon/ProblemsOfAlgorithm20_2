package BaekJoon;

import java.util.*;
import java.io.*;

public class FlyMeToTheAlphaCentauri_1011 {
	static int n;
	static long[] startLocations;
	static long[] endLocations;
	static long[] results;
	
	public static void main(String[] args) {
		makeInput();
		solveResults();
		printResult();
	}
	
	private static void printResult() {
		for(int i = 0; i < n; i++) {
			System.out.println(results[i]);
		}
	}
	
	private static void solveResults() {
		for(int i = 0; i < n; i++) {
			results[i] = findMinumumMove(startLocations[i], endLocations[i]);
		}
	}

	private static long findMinumumMove(long startLocation, long endLocation) {
		long result = 1;
		long length = endLocation - startLocation;
		long started = 0, end = 0, flag = 0;
		
		while(true) {	
			// 같은 flag로 2번 반복
			started = end + 1;
			end = started + flag;
			if(length >= started && length <= end) {
				break;
			}
			result++;
			
			started = end + 1;
			end = started + flag;
			if(length >= started && length <= end) {
				break;
			}
			result++;
			
			flag++;			
		}
		
		return result;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		
		n = input.nextInt();
		startLocations = new long[n];
		endLocations = new long[n];
		results = new long[n];
		
		for(int i = 0; i < n; i++) {
			startLocations[i] = input.nextLong();
			endLocations[i] = input.nextLong();
		}
	}
}
