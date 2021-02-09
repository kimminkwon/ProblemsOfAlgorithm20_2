package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class 색종이_2563 {
	
	private static int numOfPaper;
	private static boolean[][] map;
	private static int[][] locationOfPaper;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		numOfPaper = Integer.parseInt(br.readLine());
		map = new boolean[100][100];
		locationOfPaper = new int[numOfPaper][2];
		for(int i = 0; i < numOfPaper; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			locationOfPaper[i][0]= Integer.parseInt(st.nextToken());
			locationOfPaper[i][1]= Integer.parseInt(st.nextToken());
		}
		
		pastePapers();
		printResult();
	}

	private static void pastePapers() {
		for(int[] paper : locationOfPaper) 
			pastePaper(paper[0], paper[1]);
	}

	private static void pastePaper(int x, int y) {
		for(int i = x; i < x + 10; i++)
			for(int j = y; j < y + 10; j++)
				if(!isOut(i, j)) map[i][j] = true;
	}

	private static boolean isOut(int x, int y) {
		if(x >= 100 || x < 0 || y >= 100 || y < 0) return true;
		return false;
	}

	private static void printResult() {
		int result = 0;
		for(int i = 0; i < 100; i++)
			for(int j = 0; j < 100; j++)
				if(map[i][j]) result++;
		System.out.println(result);
	}
	
}
