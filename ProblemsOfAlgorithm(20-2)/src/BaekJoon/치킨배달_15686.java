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

public class 치킨배달_15686 {

	private static class Coor {
		int x, y;
		public Coor(int x, int y) { this.x = x; this.y = y; }
	}
	
	private static int N, M, numOfChk, numOfHome;
	private static List<Coor> homeList = new ArrayList<>();
	private static List<Coor> chkList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 1; j <= N; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 1) {
					numOfHome++;
					homeList.add(new Coor(i, j));
				}
				if(num == 2) {
					numOfChk++;
					chkList.add(new Coor(i, j));
				}
			}
		}
		System.out.println(findMinimumLengthForChkPlace());
	}
	
	private static int findMinimumLengthForChkPlace() {
		int result = Integer.MAX_VALUE;
		int[] comb = new int[numOfChk];
		Coor[] chkComb = new Coor[M];
		for(int i = 0; i < M; i++) comb[numOfChk - i - 1] = 1;
		
		do {
			int cnt = 0;
			for(int i = 0; i < numOfChk; i++)
				if(comb[i] == 1) chkComb[cnt++] = chkList.get(i);
			result = Math.min(getChkLength(chkComb), result);
		} while(nextPermutaiton(comb));
		
		return result;
	}

	private static int getChkLength(Coor[] chkComb) {
		int totalLength = 0;
		for(int i = 0; i < numOfHome; i++) {
			int currLength = Integer.MAX_VALUE;
			for(int j = 0; j < M; j++) 
				currLength = Math.min(getLength(homeList.get(i), chkComb[j]), currLength);
			totalLength += currLength;
		}
		return totalLength;
	}
	
	private static int getLength(Coor c1, Coor c2) {
		return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
	}

	private static boolean nextPermutaiton(int[] comb) {
		int i = numOfChk - 1;
		while (i > 0 && comb[i - 1] >= comb[i]) i--;
		if (i <= 0) return false;

		int j = numOfChk - 1;
		while (comb[i - 1] >= comb[j]) j--;
		swap(comb, i - 1, j);

		int k = numOfChk - 1;
		while (i < k) swap(comb, i++, k--);

		return true;
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
}
