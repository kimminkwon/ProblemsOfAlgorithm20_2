package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class 회의실배정_1931 {

	private static int n;
	private static long[][] times; 
	private static int result;

	public static void main(String[] args) {
		makeInput();
		makeSortedTimes();
		findMaximumTimes();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void findMaximumTimes() {
		long flag = 0;
		for(int i = 0; i < n; i++) {
			if(times[i][0] >= flag) {
				flag = times[i][1];
				result++;
			}
		}
	}

	private static void makeSortedTimes() {
		Arrays.sort(times, 
				(t1, t2) -> { return (int) (t1[1] - t2[1] == 0? t1[0] - t2[0] : t1[1] - t2[1]);}
		);
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		times = new long[n][2];
		
		for(int i = 0; i < n; i++) {
			times[i][0] = input.nextLong();
			times[i][1] = input.nextLong();
		}
	}

}
