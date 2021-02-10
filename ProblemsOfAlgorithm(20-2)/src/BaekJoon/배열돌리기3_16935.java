package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class 배열돌리기3_16935 {
	
	private static int N, M, R;
	private static String[][] arr;
	private static int[] moveNum;
	private static int[] dOne = {0, 1, 0, -1};
	private static int[] dTwo = {1, 0, -1, 0};
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		R = Integer.parseInt(st.nextToken());
		arr = new String[N][M];
		moveNum = new int[R];
		
		for(int i = 0; i < N; i++) 
			arr[i] = br.readLine().split(" ");
		
		st = new StringTokenizer(br.readLine());
		for(int i = 0; i < R; i++) 
			moveNum[i] = Integer.parseInt(st.nextToken());
		
		doMove();
		printResult();
	}
	
	private static void print(String[][] s) {
		for(int i = 0; i < s.length; i++) {
			for(int j = 0; j < s[i].length; j++) {
				System.out.print(s[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static void doMove() {
		for(int i = 0; i < R; i++) {
			switch (moveNum[i]) {
			case 1:
				doMove1();
				break;
			case 2:
				doMove2();
				break;
			case 3:
				doMove3();
				break;
			case 4:
				doMove4();
				break;
			case 5:
				doMove5();
				break;
			case 6:
				doMove6();
				break;
			}
		}
	}
	
	private static void doMove6() {
		String[][] q1 = getQuadrant(1);
		String[][] q2 = getQuadrant(2);
		String[][] q3 = getQuadrant(3);
		String[][] q4 = getQuadrant(4);
		
		for(int i = 0; i < N/2; i++)
			for(int j = 0; j < M/2; j++) {
				arr[i][j] = q2[i][j];
				arr[i][j + M/2] = q4[i][j];
				arr[i + N/2][j] = q1[i][j];
				arr[i + N/2][j + M/2] = q3[i][j];
			}
	}
	
	private static void doMove5() {
		String[][] q1 = getQuadrant(1);
		String[][] q2 = getQuadrant(2);
		String[][] q3 = getQuadrant(3);
		String[][] q4 = getQuadrant(4);
		
		for(int i = 0; i < N/2; i++)
			for(int j = 0; j < M/2; j++) {
				arr[i][j] = q3[i][j];
				arr[i][j + M/2] = q1[i][j];
				arr[i + N/2][j] = q4[i][j];
				arr[i + N/2][j + M/2] = q2[i][j];
			}
				
	}

	private static void doMove4() {
		String[][] newArr = new String[M][N];
		for(int h = 0; h < N; h++) 
			for(int i = 0, j = M - 1; i < M; i++, j--) 
				newArr[j][h] = arr[h][i]; 
		
		arr = newArr;
		swapNM();
	}
	
	private static void doMove3() {
		String[][] newArr = new String[M][N];
		for(int h = 0, v = N - 1; h < N; h++, v--) 
			for(int i = 0; i < M; i++) 
				newArr[i][v] = arr[h][i]; 
			
		arr = newArr;
		swapNM();
	}

	private static void doMove2() {
		for(int level = 0; level < N; level++) 
			for(int left = 0, right = M-1; left < M/2; left++, right--) {
				String temp = arr[level][left];
				 arr[level][left] =  arr[level][right];
				 arr[level][right] = temp;
			}
	}

	private static void doMove1() {
		for(int up = 0, down = N-1; up < N/2; up++, down--) {
			String[] temp = arr[up];
			arr[up] = arr[down];
			arr[down] = temp;
		}
	}

	private static void swapNM() {
		int temp = N;
		N = M;
		M = temp;
	}
	
	private static void printResult() {
		StringBuilder sb = new StringBuilder();
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < M; j++) 
				sb.append(arr[i][j]).append(" ");
			sb.append("\n");
		}
			
		System.out.println(sb.toString());
	}
	private static String[][] getQuadrant(int n) {
		String[][] quadArr = new String[N/2][M/2];
		switch (n) {
		case 1:
			for(int i = 0; i < N/2; i++)
				for(int j = 0; j < M/2; j++)
					quadArr[i][j] = arr[i][j];
			break;
		case 2:
			for(int i = 0; i < N/2; i++)
				for(int j = 0; j < M/2; j++)
					quadArr[i][j] = arr[i][j + M/2];
			break;
		case 3:
			for(int i = 0; i < N/2; i++)
				for(int j = 0; j < M/2; j++)
					quadArr[i][j] = arr[i + N/2][j];
			break;
		case 4:
			for(int i = 0; i < N/2; i++)
				for(int j = 0; j < M/2; j++)
					quadArr[i][j] = arr[i + N/2][j + M/2];
			break;
		}
		
		return quadArr;
	}
}
