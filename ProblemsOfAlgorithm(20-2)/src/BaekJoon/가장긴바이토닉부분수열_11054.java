package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class 가장긴바이토닉부분수열_11054 {

	private static int n;
	private static int[] arr;
	private static int result;

	public static void main(String[] args) {
		makeInput();
		makeMaxLengthBitonicArr();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void makeMaxLengthBitonicArr() {
		result = Integer.MIN_VALUE;
		int[] increDp = new int[n+1];
		int[] decreDp = new int[n+1];
		int[] indecreDp = new int[n+1];
		
		// 1. 증가하는 DP
		for(int i = 1; i < n + 1; i++) {
			increDp[i] = 1;
			for(int j = 1; j < i; j++) {
				if(arr[j] < arr[i] && increDp[i] < increDp[j] + 1) {
					increDp[i] = increDp[j] + 1;
				}
			}
			result = result < increDp[i] ? increDp[i] : result;
		}
		
		// 2. 반대방향으로 증가하는 DP
		for(int i = n; i > 0; i--) {
			decreDp[i] = 1;
			for(int j = n; j > i; j--) {
				if(arr[j] < arr[i] && decreDp[i] < decreDp[j] + 1) {
					decreDp[i] = decreDp[j] + 1;
				}
			}
			result = result < decreDp[i] ? decreDp[i] : result;
		}
		
		// 두 DP를 합친다. 자기 자신이 두번 겹치므로 1을 빼줘야 함
		for(int i = 1; i < n + 1; i++) {
			indecreDp[i] = increDp[i] + decreDp[i] - 1;
			result = result < indecreDp[i] ? indecreDp[i] : result;
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
