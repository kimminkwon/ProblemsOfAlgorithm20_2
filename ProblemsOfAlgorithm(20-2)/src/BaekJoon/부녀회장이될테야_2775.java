package BaekJoon;

import java.util.*;
import java.io.*;

public class 부녀회장이될테야_2775 {
	static int n;
	static int[] floor;
	static int[] houseNumber;
	static long[][] numOfPeoples;
	static long[] results;
	
	public static void main(String[] args) {
		makeInput();
		preprocessingNumOfPeoples();
		findNumOfPeoples();
		printResult();
	}
	
	private static void printResult() {
		for(int i = 0; i < n; i++) {
			System.out.println(results[i]);
		}
	}

	private static void findNumOfPeoples() {
		for(int i = 0; i < n; i++) {
			results[i] = findNumOfPeople(floor[i], houseNumber[i]);
		}
	}

	private static long findNumOfPeople(int floor, int houseNumber) {
		return numOfPeoples[floor][houseNumber];
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		
		floor = new int[n]; houseNumber = new int[n];
		numOfPeoples = new long[15][15]; results = new long[n];
		
		for(int i = 0; i < n; i++) {
			floor[i] = input.nextInt();
			houseNumber[i] = input.nextInt();
		}
	}
	
	private static void preprocessingNumOfPeoples() {
		for(int i = 1; i <= 14; i++) { // 0층 저장
			numOfPeoples[0][i] = i;
		}
		
		for(int i = 1; i < 15; i++) { // 1층부터 14층까지
			for(int j = 1; j < 15; j++) {
				// i층 j호
				numOfPeoples[i][j] = numOfPeoples[i][j-1] + numOfPeoples[i-1][j];
			}
		}
	}
}
