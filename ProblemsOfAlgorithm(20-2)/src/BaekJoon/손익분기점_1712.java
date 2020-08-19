package BaekJoon;

import java.util.*;
import java.io.*;

public class 손익분기점_1712 {
	
	private static int a;
	private static int b;
	private static int c;
	private static int result;
	
	public static void main(String[] args) {
		makeInput();
		fineBreakEvenPoint();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void fineBreakEvenPoint() {
		// x = a / (c-b)
		if(b >= c) // 손익분기점을 넘지 못하는 케이스
			result = -1;
		else
			result = a/(c-b) + 1;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		a = input.nextInt();
		b = input.nextInt();
		c = input.nextInt();
	}

}
