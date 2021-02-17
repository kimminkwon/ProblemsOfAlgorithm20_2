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

public class 캐슬디펜스_17135 {

	private static class Coor {
		int x, y;
		public Coor(int x, int y) { this.x = x; this.y = y; }
		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
		
		
	}
	
	private static int N, M, D, numOfMon, result;
	private static List<Coor> bowList = new ArrayList<>();
	private static List<Coor> monList = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		D = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < M; j++) {
				int num = Integer.parseInt(st.nextToken());
				if(num == 1) {
					numOfMon++;
					monList.add(new Coor(i, j));
				}
			}
		}
		int[] comb = new int[M];
		Coor[] bowArr = new Coor[3];
		Coor[] monArr = new Coor[numOfMon];
		for(int i = 0; i < 3; i++) comb[M - i - 1] = 1;
		
		do {
			int cnt = 0;
			for(int i = 0; i < M; i++) 
				if(comb[i] == 1) bowArr[cnt++] = new Coor(N, i);
			for(int i = 0; i < numOfMon; i++)
				monArr[i] = new Coor(monList.get(i).x, monList.get(i).y);
			result = Math.max(calculateNumOfKill(bowArr, monArr), result);
		} while(nextPermutaiton(comb));
		
		System.out.println(result);
	}
	
	private static int calculateNumOfKill(Coor[] bowArr, Coor[] monArr) {
		int killNum = 0;
		int escapeNum = 0;
		Arrays.sort(monArr, (o1, o2) -> o1.y - o2.y);
		while(true) {
			if(killNum + escapeNum >= numOfMon) break;
			int[] killIndex = new int[3];
			
			// 1. Kill
			for(int i = 0; i < 3; i++) {
				int index = -1; 
				int minLength = Integer.MAX_VALUE;
				for(int j = 0; j < numOfMon; j++) {
					int currLength = getLength(bowArr[i], monArr[j]);
					if(currLength > D) continue;
					else if(currLength < minLength && monArr[j].x != -1) {
						index = j;
						minLength = currLength;
					}
				}
				killIndex[i] = index;
			}
			for(int i = 0; i < 3; i++) {
				if(killIndex[i] != -1 && monArr[killIndex[i]].x != -1) {
					monArr[killIndex[i]].x = -1;
					killNum++;
				}
			}
			
			// 2. Move 
			for(int i = 0; i < numOfMon; i++) {
				if(monArr[i].x != -1) {
					monArr[i].x += 1;
				}
				if(isOut(monArr[i].x)) {
					monArr[i].x = -1;
					escapeNum++;
				}
			}
		}
		return killNum;
	}
	
	private static int getLength(Coor c1, Coor c2) {
		return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
	}

	private static boolean nextPermutaiton(int[] comb) {
		int i = M - 1;
		while (i > 0 && comb[i - 1] >= comb[i]) i--;
		if (i <= 0) return false;

		int j = M - 1;
		while (comb[i - 1] >= comb[j]) j--;
		swap(comb, i - 1, j);

		int k = M - 1;
		while (i < k) swap(comb, i++, k--);

		return true;
	}

	private static void swap(int[] nums, int i, int j) {
		int temp = nums[i];
		nums[i] = nums[j];
		nums[j] = temp;
	}
	
	private static boolean isOut(int x) {
		return x >= N;
	}
}
