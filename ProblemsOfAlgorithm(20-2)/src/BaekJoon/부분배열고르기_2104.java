package BaekJoon;

import java.io.*;
import java.util.*;

public class 부분배열고르기_2104 {
	
	private static int N;
	private static long[] arr;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		arr = new long[N];
		for(int i = 0; i < N; i++) 
			arr[i] = Long.parseLong(st.nextToken());
			
		System.out.println(findMaximumSubArray(0, N));
	}

	private static long findMaximumSubArray(int left, int right) {
		if(left >= right - 1) return arr[left] * arr[left];
		
		int mid = (left + right) / 2;
		long minValue = arr[mid]; 
		long sum = arr[mid]; 
		long mulSum = arr[mid] * arr[mid];
		
		int midLeft = mid; int midRight = mid;
		while(midLeft - 1 >= left || midRight + 1 < right) {
			long leftValue = midLeft - 1 >= left ? sum + arr[midLeft - 1] : Integer.MIN_VALUE;
			long rightValue = midRight + 1 < right ? sum + arr[midRight + 1] : Integer.MIN_VALUE;
			
			if(leftValue > rightValue) {
				sum = leftValue;
				minValue = Math.min(minValue, arr[--midLeft]);
			} else {
				sum = rightValue;
				minValue = Math.min(minValue, arr[++midRight]);
			}
			mulSum = Math.max(mulSum, sum * minValue);
		}
		
		return Math.max(mulSum, 
			   Math.max(findMaximumSubArray(left, mid), 
					    findMaximumSubArray(mid, right)));
	}
}
