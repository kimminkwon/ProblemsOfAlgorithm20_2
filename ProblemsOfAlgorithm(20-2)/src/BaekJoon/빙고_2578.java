package BaekJoon;

import java.io.*;
import java.util.*;

public class 빙고_2578 {
	
	private static class Coor {
		int x, y;
		public Coor(int x, int y) { this.x = x; this.y = y; }
	}
	
	private static boolean[][] check = new boolean[5][5];
	private static int[] callNums = new int[25];
	private static Map<Integer, Coor> hashMap = new HashMap<>();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++) 
				hashMap.put(Integer.parseInt(st.nextToken()), new Coor(i, j));
		}
		
		int flag = 0;
		for(int i = 0; i < 5; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j = 0; j < 5; j++)
				callNums[flag++] = Integer.parseInt(st.nextToken());
		}
		System.out.println(findBingo());
	}
	
	private static int findBingo() {
		for(int i = 0; i < 25; i++) {
			Coor c = hashMap.get(callNums[i]);
			check[c.x][c.y] = true;
			if(isBingo()) return i + 1;
		}
		return 0;
	}
	
	private static boolean isBingo() {
		int bingoNum = 0;
		// 1. 가로
		for(int i = 0; i < 5; i++) {
			int cnt = 0;
			for(int j = 0; j < 5; j++)
				if(check[i][j]) cnt++; 
			if(cnt == 5) bingoNum++;
		}
		// 2. 세로
		for(int i = 0; i < 5; i++) {
			int cnt = 0;
			for(int j = 0; j < 5; j++)
				if(check[j][i]) cnt++; 
			if(cnt == 5) bingoNum++;
		}
		// 3. 대각선
		int cnt = 0;
		for(int i = 0, j = 0; i < 5; i++, j++) 
			if(check[i][j]) cnt++; 
		if(cnt == 5) bingoNum++;
		
		cnt = 0;
		for(int i = 0, j = 4; i < 5; i++, j--) 
			if(check[i][j]) cnt++; 
		if(cnt == 5) bingoNum++;
			
		return bingoNum >= 3;
	}
}
