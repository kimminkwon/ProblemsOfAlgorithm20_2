package SWEA;

import java.io.*;
import java.util.*;

public class Solution_요리사_모의SW역량테스트_4012 {
	
	private static int N, result;
	private static int[][] synergy;

	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_요리사.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	synergy = new int[N][N];
        	result = Integer.MAX_VALUE;
        	
        	for(int i = 0; i < N; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
        		for(int j = 0; j < N; j++) {
        			synergy[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	findMinimumDiffBetweenTwoFood();
        	sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
	}


	private static void findMinimumDiffBetweenTwoFood() {
		int[] perm = new int[N];
		int[] foodsOne = new int[N/2], foodsTwo = new int[N/2];
		int cnt = 0;
		while (++cnt <= N/2) perm[N - cnt] = 1;
		do {
			int cntOne = 0, cntTwo = 0;
			for(int i = 0; i < N; i++) 
				if(perm[i] == 1) foodsOne[cntOne++] = i;
				else foodsTwo[cntTwo++] = i;
			result = Math.min(getDiffBetweenTwoFood(foodsOne, foodsTwo), result);
		} while(nextPermutation(perm, N));
	}

	private static int synergyOne, synergyTwo; 
	private static int getDiffBetweenTwoFood(int[] foodsOne, int[] foodsTwo) {
		synergyOne = 0; synergyTwo = 0;
		getSynergy(true, foodsOne, 0, 0, new int[2]);
		getSynergy(false, foodsTwo, 0, 0, new int[2]);
		
		return Math.abs(synergyOne - synergyTwo);
	}


	private static void getSynergy(boolean isOne, int[] foodsOne, int flag, int cnt, int[] select) {
		if(cnt == 2) {
			if(isOne) synergyOne += synergy[select[0]][select[1]];
			else synergyTwo += synergy[select[0]][select[1]];
			return;
		} else {
			for(int i = 0; i < N / 2; i++) 
				if((flag & 1 << i) == 0) {
					select[cnt] = foodsOne[i];
					getSynergy(isOne, foodsOne, flag | 1 << i, cnt + 1, select);
				}
		}	
	}


	private static boolean nextPermutation(int[] arr, int N) {
		int i = N - 1;
		while (i > 0 && arr[i - 1] >= arr[i]) --i;

		if (i == 0)
			return false;

		int j = N - 1;
		while (arr[i - 1] >= arr[j]) --j;
		swap(arr, i - 1, j);

		int k = N - 1;
		while (i < k) swap(arr, i++, k--);
		
		return true;
	}

	private static void swap(int numbers[], int i, int j) {
		int temp = numbers[i];
		numbers[i] = numbers[j];
		numbers[j] = temp;
	}
	
}
