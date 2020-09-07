package BaekJoon;

import java.util.Scanner;

public class 덩치_7568 {

	static int n;
	static int[][] weightHeight;
	static int[] grade;
	
	public static void main(String[] args) {
		makeInput();
		makeGrades();
		printResult();
	}
	
	private static void printResult() {
		for(int i = 0; i < n; i++) {
			System.out.print(grade[i] + " ");
		}
	}

	private static void makeGrades() {
		for(int i = 0; i < n; i++) {
			grade[i] = makeGrade(i);
		}
	}
	
	private static int makeGrade(int index) {
		int result = 1;
		
		for(int i = 0; i < n; i++) {
			if(i != index && isBigger(i, index) == true) {
				result++;
			}
		}
		
		return result;
	}

	private static boolean isBigger(int i, int index) {
		// i번째 사람이 index번째 사람보다 크다면 true
		if(weightHeight[i][0] > weightHeight[index][0] && weightHeight[i][1] > weightHeight[index][1]) {
			return true;
		}
		
		return false;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		
		weightHeight = new int[n][2];
		grade = new int[n];
		
		for(int i = 0; i < n; i++) {
			weightHeight[i][0] = input.nextInt();
			weightHeight[i][1] = input.nextInt();
		}
	}

}
