package Jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main_Jongol_1037_오류교정 {

	private static int N;
    private static int[][] parityBits;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		parityBits = new int[N][N];

		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++)
				parityBits[i][j] = Integer.parseInt(st.nextToken());
		}

		System.out.println(isParity());
    }

	private static String isParity() {
    	int[] rowSum = new int[N], colSum = new int[N];

    	for(int i = 0; i < N; i++) {
    		int currRowSum = 0, currColSum = 0;
			for(int j = 0; j < N; j++) {
				currRowSum += parityBits[i][j];
				currColSum += parityBits[j][i];
			}
			rowSum[i] = currRowSum;
			colSum[i] = currColSum;
		}
    	if(isEven(rowSum, colSum)) return "OK";
		int rowOddNum = findOdd(rowSum), colOddNum = findOdd(colSum);
		if(rowOddNum == -2 || colOddNum == -2) return "Corrupt";

		return String.format("Change bit (%d,%d)", rowOddNum + 1, colOddNum + 1);
	}

	private static int findOdd(int[] sums) {
    	int num = -1;
		for(int i = 0; i < N; i++)
			if(sums[i] % 2 != 0) num = num == -1 ? i : -2;
    	return num;
	}

	private static boolean isEven(int[] rowSum, int[] colSum) {
    	for(int i = 0; i < N; i++)
    		if(rowSum[i] % 2 != 0) return false;
    		else if(colSum[i] % 2 != 0) return false;
    	return true;
	}
}
