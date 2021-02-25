package BaekJoon;

import java.io.*;
import java.util.*;

public class 롤케이크_3985 {
	
	private static int L, N, firstIndex, lastIndex;
	private static int[] cake;
	private static int[][] nums;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		L = Integer.parseInt(br.readLine());
		N = Integer.parseInt(br.readLine());
		
		cake = new int[L + 1];
		nums = new int[N + 1][2];
		int max = 0;
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			nums[i][0] = Integer.parseInt(st.nextToken());
			nums[i][1] = Integer.parseInt(st.nextToken());
			if(max == 0 || max < (nums[i][1] - nums[i][0])) {
				max = (nums[i][1] - nums[i][0]);
				firstIndex = i;
			}
		}
		
		findMaximumPiece();
		System.out.println(firstIndex);
		System.out.println(lastIndex);
	}
	
	private static void findMaximumPiece() {
		for(int i = 1; i <= N; i++) 
			for(int j = nums[i][0]; j <= nums[i][1]; j++)
				if(cake[j] == 0) cake[j] = i;
		
		int[] hash = new int[N + 1];
		
		for(int i = 1; i <= L; i++)
			hash[cake[i]]++;
		
		int max = 0;
		for(int i = 1; i <= N; i++) {
			if(max < hash[i]) {
				max = hash[i];
				lastIndex = i;
			}
		}
	}
}
