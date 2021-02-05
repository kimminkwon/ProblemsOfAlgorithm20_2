package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 정사각형방_D4_1861 {
    // 상-하-좌-우
    private static int[] dOne = {-1, 1, 0, 0};
    private static int[] dTwo = {0, 0, -1, 1};
    
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_정사각형방.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			int[][] room = new int[N][N];
			
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				for(int j = 0; j < N; j++) 
					room[i][j] = Integer.parseInt(st.nextToken());
			}
			
			findMaximumMoveRoom(tc, N, room);
		}
		br.close();
	}

	private static void findMaximumMoveRoom(int tc, int N, int[][] room) {
		int roomNum = Integer.MAX_VALUE; int maxMove = Integer.MIN_VALUE;
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				int moveNum = maximumMoveFromSomeRoom(i, j, N, room);
				if(maxMove < moveNum || (maxMove == moveNum && room[i][j] < roomNum)) {
					maxMove = moveNum;
					roomNum = room[i][j];
				}
			}
		}
		System.out.println("#" + tc + " " + roomNum + " " + maxMove);
	}

	private static int maximumMoveFromSomeRoom(int one, int two, int N, int[][] room) {
		int moveNum = 1; int moveDir = 0;
		while(true) {
			moveDir = isPass(N, one, two, room);
			if(moveDir == -1) break;
			one += dOne[moveDir]; two += dTwo[moveDir];
			moveNum++;
		}
		return moveNum;
	}
	
	private static int isPass(int N, int one, int two, int[][] room) {
		if(one >= N || one < 0 || two >= N || two < 0) return -1;
		for(int d = 0; d < 4; d++) {
			int nextOne = one + dOne[d]; int nextTwo = two + dTwo[d];
			if(nextOne >= N || nextOne < 0 || nextTwo >= N || nextTwo < 0) continue;
			if(room[nextOne][nextTwo] == room[one][two] + 1) return d;
		}	
		return -1;
	}
}
