package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_햄버거다이어트_D3_5215 {
	private static int N, L, result;
	private static boolean[] isSelected;
	private static Hambuger[] hamArr;
	
	private static class Hambuger {
		int cal; int score;
		public Hambuger(int score, int cal) { this.cal = cal; this.score = score; }
	}
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	
        	N = Integer.parseInt(st.nextToken());
        	L = Integer.parseInt(st.nextToken());
        	hamArr = new Hambuger[N];
        	isSelected = new boolean[N];
        	result = 0;
        	
        	for(int i = 0; i < N; i++) {
        		st = new StringTokenizer(br.readLine());
        		Hambuger h = new Hambuger(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        		hamArr[i] = h;
        	}
        	getSubSetOfHam(0, 0);
        	System.out.println("#" + tc + " " + result);
        }
        	
        br.close();
	}
	
	private static void getSubSetOfHam(int cnt, int currCal) {
		if(currCal > L) return;
		else if(cnt == N) result = Math.max(result, calCalro());
		else {
			isSelected[cnt] = true;
			getSubSetOfHam(cnt + 1, currCal + hamArr[cnt].cal);
			isSelected[cnt] = false;
			getSubSetOfHam(cnt + 1, currCal);
		}
	}

	private static int calCalro() {
		int totalScore = 0;
		for(int i = 0; i < N; i++) 
			if(isSelected[i]) totalScore += hamArr[i].score;
		
		return totalScore;
	}
}
