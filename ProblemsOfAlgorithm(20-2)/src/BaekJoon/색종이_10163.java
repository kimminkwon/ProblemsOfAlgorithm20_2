package BaekJoon;

import java.io.*;
import java.util.*;

public class 색종이_10163 {

	private static int N;
	private static int[][] paperMap = new int[101][101];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		for(int i = 1; i <= N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int width = Integer.parseInt(st.nextToken());
			int height = Integer.parseInt(st.nextToken());
			
			fillPaperMap(i, x, y, height, width);
		}
		
		System.out.println(paperMapToResult());
	}

	private static String paperMapToResult() {
		int[] results = new int[N + 1];
		for(int i = 0; i < 101; i++) 
			for(int j = 0; j < 101; j++) 
				if(paperMap[i][j] != 0) results[paperMap[i][j]]++;

		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++)
			sb.append(results[i]).append("\n");
			
		return sb.toString();
	}

	private static void fillPaperMap(int num, int x, int y, int width, int height) {
		for(int i = 0; i < height; i++)
			for(int j = 0; j < width; j++)
				paperMap[x + i][y + j] = num;
	}
	
}
