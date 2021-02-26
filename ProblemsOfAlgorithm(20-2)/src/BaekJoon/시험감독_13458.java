package BaekJoon;

import java.io.*;
import java.util.*;

public class 시험감독_13458 {
	
	private static int N, B, C;
	private static long result;
	private static int[] A;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		A = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			A[i] = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		B = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		findMinimumGamdok();
		System.out.println(result);
	}

	private static void findMinimumGamdok() {
		for(int i = 0; i < N; i++) {
			A[i] -= B;
			if(A[i] < 0) A[i] = 0;
			result++;
			
			result += A[i] / C;
			if(A[i] % C != 0) result++;
		}
	}

}
