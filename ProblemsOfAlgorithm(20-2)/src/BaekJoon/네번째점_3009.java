package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class 네번째점_3009 {

	static int[] x;
	static int[] y;
	
	public static void main(String[] args) {
		makeInput();
		findAndPrintCoordinate();
	}

	private static void findAndPrintCoordinate() {
		int x_four, y_four;
		
		if(x[0] == x[1]) x_four = x[2];
		else if(x[1] == x[2]) x_four = x[0];
		else x_four = x[1];
		
		if(y[0] == y[1]) y_four = y[2];
		else if(y[1] == y[2]) y_four = y[0];
		else y_four = y[1];
		
		System.out.printf("%d %d\n", x_four, y_four);
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		x = new int[3]; y = new int[3];
		for(int i = 0; i < 3; i++) {
			x[i] = input.nextInt();
			y[i] = input.nextInt();
		}
	}

}
