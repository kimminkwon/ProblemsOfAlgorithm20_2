package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class 정수삼각형_1932 {
	
	private static int n;
	private static int[][] triangle;
	private static int[][] costOfTriangle;
	
	public static void main(String[] args) {
		makeInput();
		makeCostOfTriangle();
		printResult();
	}

	private static void printResult() {
		int maxValue = Integer.MIN_VALUE;
		for(int i = 0; i < n; i++)
			maxValue = maxValue < costOfTriangle[n - 1][i]? costOfTriangle[n - 1][i] : maxValue;
		System.out.println(maxValue);
	}

	private static void makeCostOfTriangle() {
		for(int i = 0; i < n; i++) {
			if(i == 0)
				costOfTriangle[i][0] = triangle[i][0];
			else {
				for(int j = 0; j < triangle[i].length; j++) {
					if(j == 0) {
						costOfTriangle[i][j] = costOfTriangle[i - 1][j] + triangle[i][j];
					}
					else if(j == triangle[i].length - 1) {
						costOfTriangle[i][j] = costOfTriangle[i - 1][j - 1] + triangle[i][j];
					}
					else {
						costOfTriangle[i][j] = Math.max(costOfTriangle[i- 1][j - 1], costOfTriangle[i - 1][j]) + triangle[i][j];
					}
				}	
			}
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		triangle = new int[n][n];
		costOfTriangle = new int[n][n];
		
		for(int i = 0; i < n; i++)
			Arrays.fill(triangle[i], -1);
		
		for(int i = 0; i < n; i++)
			for(int j = 0; j <= i; j++)
				triangle[i][j] = input.nextInt();		
	}

}
