package BaekJoon;

import java.util.Scanner;

public class 가장긴증가하는부분수열_11053 {

	private static int n;
	private static int[] arr;
	private static int result;

	public static void main(String[] args) {
		makeInput();
		makeLIS();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void makeLIS() {
		result = Integer.MIN_VALUE;
		int[] dpLisList = new int[n + 1];
		
		for(int i = 1; i < n + 1; i++) {
			dpLisList[i] = 1;
			for(int j = 1; j < i; j++) {
				if(arr[j] < arr[i] && dpLisList[i] < dpLisList[j] + 1) {
					dpLisList[i] = dpLisList[j] + 1;
				}
			}
			result = result < dpLisList[i] ? dpLisList[i] : result;
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		n = input.nextInt();
		arr = new int[n + 1];
		for(int i = 1; i < n + 1; i++) {
			arr[i] = input.nextInt();
		}
	}

}
