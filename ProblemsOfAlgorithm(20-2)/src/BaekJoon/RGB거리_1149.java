package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class RGB거리_1149 {
	
	private static int numOfHouse;
	private static int[][] costOfColors;
	private static int[][] costArr;

	public static void main(String[] args) {
		makeInput();
		findMinimunCost();
		printResult();
	}

	private static void printResult() {
		int minValue = Integer.MAX_VALUE;
		for(int i = 0; i < 3; i++)
			if(minValue > costArr[numOfHouse-1][i])
				minValue = costArr[numOfHouse-1][i];
		
		System.out.println(minValue);
	}

	private static void findMinimunCost() {
		for(int i = 0; i < numOfHouse; i++) {
			if(i == 0) {
				costArr[i][0] = costOfColors[i][0];
				costArr[i][1] = costOfColors[i][1];
				costArr[i][2] = costOfColors[i][2];
			}
			else {
				costArr[i][0] = Math.min(costArr[i-1][1], costArr[i-1][2]) + costOfColors[i][0];
				costArr[i][1] = Math.min(costArr[i-1][0], costArr[i-1][2]) + costOfColors[i][1];
				costArr[i][2] = Math.min(costArr[i-1][0], costArr[i-1][1]) + costOfColors[i][2];
			}
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		numOfHouse = input.nextInt();
		costOfColors = new int[numOfHouse][3];
		costArr = new int[numOfHouse][3];
		for(int i = 0; i < numOfHouse; i++) {
			costOfColors[i][0] = input.nextInt();
			costOfColors[i][1] = input.nextInt();
			costOfColors[i][2] = input.nextInt();
		}
	}

}
