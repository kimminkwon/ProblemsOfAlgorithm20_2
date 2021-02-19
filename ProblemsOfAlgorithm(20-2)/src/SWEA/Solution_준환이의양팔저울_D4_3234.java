package SWEA;

import java.io.*;
import java.util.*;

public class Solution_준환이의양팔저울_D4_3234 {
	
	private static int N, numOfCase;
	private static int[] weight;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_준환이의양팔저울.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	weight = new int[N];
        	numOfCase = 0;
        	
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < N; i++) 
        		weight[i] = Integer.parseInt(st.nextToken());
        
        	findCaseOfBalance(weight.clone());
        	sb.append("#").append(tc).append(" ").append(numOfCase).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
	}
	
	private static void findCaseOfBalance(int[] weightPerm) {
		Arrays.sort(weightPerm);
		do findCaseOfBalanceForPerm(weightPerm[0], 0, 1, weightPerm);
		while(nextPermutation(weightPerm, N));
	}

	private static void findCaseOfBalanceForPerm(int lWeight, int rWeight, int index, int[] weightPerm) {
		if(lWeight < rWeight) return;
		if(index >= N) {
			numOfCase++;
			return;
		}
		
		findCaseOfBalanceForPerm(lWeight + weightPerm[index], rWeight, index + 1, weightPerm);
		findCaseOfBalanceForPerm(lWeight, rWeight + weightPerm[index], index + 1, weightPerm);
	}
	
	private static boolean nextPermutation(int[] arr, int N) {
		int i = N - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) --i;

		if (i == 0) return false;

		int j = N - 1;
		while (arr[i - 1] >= arr[j]) --j;
		swap(arr, i - 1, j);

		int k = N - 1;
		while (i < k) swap(arr, i++, k--);
		
		return true;
	}

	private static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
}
