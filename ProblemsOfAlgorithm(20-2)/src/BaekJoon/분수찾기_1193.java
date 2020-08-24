package BaekJoon;

import java.util.*;
import java.io.*;

public class 분수찾기_1193 {

	static long x;
	static String result;
	
	public static void main(String[] args) {
		makeInput();
		findFraction();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void findFraction() {
		int floor = 0;
		int flag = 0;
		while(true) {
			floor++;
			if(flag + floor >= x)
				break;
			flag = flag + floor;
		}
		result = makeFraction(flag, floor);
	}
	
	private static String makeFraction(long flag, long floor) {
		long upNum, downNum;
		if(floor % 2 == 0) { // 층수가 짝수: 분자가 작은 수부터
			upNum = x - flag;
			downNum = floor - upNum + 1;
		}
		else { // 층수가 홀수: 분모가 작은 수부터
			downNum = x - flag;
			upNum = floor - downNum + 1;
		}
		return String.valueOf(upNum) + "/" + String.valueOf(downNum);
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		x = input.nextLong();
	}

}
