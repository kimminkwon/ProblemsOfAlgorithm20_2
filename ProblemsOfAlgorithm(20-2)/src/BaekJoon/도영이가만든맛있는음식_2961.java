package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class 도영이가만든맛있는음식_2961 {
	
	private static int N;
	private static int[][] flavor;
	private static int result = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());

		flavor = new int[N][2];
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			flavor[i][0] = Integer.parseInt(st.nextToken());
			flavor[i][1] = Integer.parseInt(st.nextToken());
		}
		
		findMinimumDiffOfTwoFlavor(new boolean[N], 0);
		System.out.println(result);
	}

	private static void findMinimumDiffOfTwoFlavor(boolean[] selected, int cnt) {
		if(cnt == N) {
			if(isAllFalse(selected)) return;
			result = Math.min(result, getDiff(selected));
			return;
		} else {
			selected[cnt] = true;
			findMinimumDiffOfTwoFlavor(selected, cnt + 1);
			selected[cnt] = false;
			findMinimumDiffOfTwoFlavor(selected, cnt + 1);
		}
	}

	private static boolean isAllFalse(boolean[] selected) {
		for(int i = 0; i < N; i++)
			if(selected[i]) return false;
		return true;
	}

	private static int getDiff(boolean[] selected) {
		int bitter = 0; int sour = 1;
		
		for(int i = 0; i < N; i++)
			if(selected[i]) {
				sour *= flavor[i][0];
				bitter += flavor[i][1];
			}
		
		return Math.abs(sour - bitter);
	}
	

	
}
