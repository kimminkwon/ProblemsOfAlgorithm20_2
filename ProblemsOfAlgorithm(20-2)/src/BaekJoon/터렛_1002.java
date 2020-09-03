package BaekJoon;

import java.util.Scanner;

public class 터렛_1002 {
	
	static int T;
	static int[] x1, y1, x2, y2;
	static int[] r1, r2, results;
	
	public static void main(String[] args) {
		makeInput();
		findNumOfCrossPoints();
		printResult();
	}
	
	private static void printResult() {
		for(int i = 0; i < T; i++) {
			System.out.println(results[i]);
		}
	}

	private static void findNumOfCrossPoints() {
		for(int i = 0; i < T; i++) {
			results[i] = findNumOfCrossPoint(x1[i], y1[i], r1[i], x2[i], y2[i], r2[i]);
		}
	}
	
	private static int findNumOfCrossPoint(int x1, int y1, int r1, int x2, int y2, int r2) {
		double d = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2));
		if(d == 0 && r1 == r2)
			return -1;
		else if(d > r1 + r2)
			return 0;
		else if(d == r1 + r2)
			return 1;
		else {
			if(d > r2 - r1)
				return 2;
			else if(d == r2 - r1)
				return 1;
			else
				return 0;
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		T = input.nextInt();
		x1 = new int[T]; y1 = new int[T]; x2 = new int[T]; y2 = new int[T];
		r1 = new int[T]; r2 = new int[T];
		results = new int[T];
		for(int i = 0; i < T; i++) {
			x1[i] = input.nextInt(); y1[i] = input.nextInt(); r1[i] = input.nextInt();
			x2[i] = input.nextInt(); y2[i] = input.nextInt(); r2[i] = input.nextInt();
			if(r1[i] > r2[i]) { // r2가 r1보다 클수 있도록 Swap
				int x_box = x1[i]; int y_box = y1[i]; int r_box = r1[i];
				x1[i] = x2[i]; y1[i] = y2[i]; r1[i] = r2[i];
				x2[i] = x_box; y2[i] = y_box; r2[i] = r_box;
			}
		}
	}

}
