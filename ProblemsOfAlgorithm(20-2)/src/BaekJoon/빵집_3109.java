package BaekJoon;

import java.io.*;
import java.util.*;

public class 빵집_3109 {

	private static int R, C, result;
	private static int[][] map;
	private static boolean[][] visited;
	
	private static int[] dX = {-1, 0, 1};
	private static int[] dY = {1, 1, 1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		
		map = new int[R][C];
		visited = new boolean[R][C];
		
		for(int i = 0; i < R; i++) {
			String box = br.readLine();
			for(int j = 0; j < C; j++) 
				if(box.charAt(j) == 'x') map[i][j] = 1;
		}
		
		boolean[] check = new boolean[R];
		Arrays.fill(check, true);
		findMaximumPathOfPipe();
		System.out.println(result);
	}
	private static void findMaximumPathOfPipe() {
		for(int i = 0; i < R; i++) 
			makePipe(i, 0);
	}

	private static boolean makePipe(int x, int y) {
		if(y == C - 1) {
			result++;
			return true;
		}
		
		for(int d = 0; d < 3; d++) {
			int nX = x + dX[d], nY = y + dY[d];
			if(isOut(nX, nY) || visited[nX][nY] || map[nX][nY] == 1) continue;
			visited[nX][nY] = true; // 만약 이후 연결이 불가능하면 다른 출발점에서도 어차피 가봐야 안되는길! false로 다시 바꿔줄 필요가 없다! 
			if(makePipe(nX, nY)) return true;
		}
		return false;
	}

	private static boolean isOut(int x, int y) {
		return x >= R || y >= C || x < 0 | y < 0;
	}
}
