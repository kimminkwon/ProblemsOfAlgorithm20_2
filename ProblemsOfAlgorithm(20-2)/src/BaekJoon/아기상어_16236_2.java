package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class 아기상어_16236_2 {
	
	private static class Coor {
		int x, y;

		public Coor(int x, int y) {
			this.x = x;
			this.y = y;
		}

		@Override
		public String toString() {
			return "(" + x + ", " + y + ")";
		}
	}
	
	private static int N, result;
	private static int[][] map;
	private static Coor shark;
	private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 9) shark = new Coor(i, j);
			}
		}
		doShark();
		System.out.println(result);
	}
	
	private static void doShark() {
		int eatNum = 0;
		int sharkSize = 2;
		while(true) {
			// 1. 남은 물고기가 있는가?
			if(!isRemainFish()) break;
			
			// 2. 먹을 수 있는 물고기 찾기
			Deque<Coor> q = new ArrayDeque<>();
			boolean[][] visited = new boolean[N][N];
			q.offer(shark); visited[shark.x][shark.y] = true;
			List<Coor> eatList = new ArrayList<>();
			int pathLength = 0;
			while(!q.isEmpty()) {
				int size = q.size();
				while(--size >= 0) {
					Coor c = q.poll();
					for(int d = 0; d < 4; d++) {
						int nx = c.x + dx[d], ny = c.y + dy[d];
						if(!isOut(nx, ny) && !visited[nx][ny] && map[nx][ny] <= sharkSize) {
							visited[nx][ny] = true;
							q.offer(new Coor(nx, ny));
							if(map[nx][ny] != 0 && map[nx][ny] < sharkSize) eatList.add(new Coor(nx, ny));
						}
					}
				}
				pathLength++;
				// 먹을 수 있는 물고기가 존재한다면 break
				if(!eatList.isEmpty()) break;
			}
			
			
			// 3. 먹을 수 있는 물고기중 최적의 물고기를 골라낸다.
			Coor eatFish = null;
			if(!eatList.isEmpty()) {
				if(eatList.size() == 1) eatFish = eatList.get(0);
				else { // 3-1. 가장 위에 있는 물고기를 Pick
					List<Coor> optimalEat = new ArrayList<>();
					int x = Integer.MAX_VALUE;
					for(Coor c : eatList) {
						if(c.x <= x) x = c.x;
					}
					for(Coor c : eatList) {
						if(c.x == x) optimalEat.add(c);
					}
					if(optimalEat.size() == 1) eatFish = optimalEat.get(0);
					else { // 3-2. 가장 왼쪽에 있는 물고기를 Pick
						eatList = optimalEat;
						int y = Integer.MAX_VALUE;
						optimalEat = new ArrayList<>();
						for(Coor c : eatList) {
							if(c.y < y) y = c.y;
						}
						for(Coor c : eatList) {
							if(c.y == y) eatFish = c;
						}
					}
				}
			} else break; // 물고기가 없다면 종료!
			
			result += pathLength;
			eatNum++;
			if(eatNum == sharkSize) {
				eatNum = 0;
				sharkSize++;
			}
			map[shark.x][shark.y] = 0;
			map[eatFish.x][eatFish.y] = 9;
			shark = eatFish;
		}
	}

	private static boolean isRemainFish() {
		for(int i = 0; i < N; i++) 
			for(int j = 0; j < N; j++) 
				if(map[i][j] != 0) return true;
		return false;
	}
	
	private static int getDist(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	
	private static boolean isOut(int x, int y) {
		return x >= N || y >= N || x < 0 || y < 0;
	}
	
	private static void printMap() {
		for(int i = 0; i < N; i++) {
			for(int j = 0; j < N; j++) {
				System.out.print(map[i][j] == 9 ? "* " : map[i][j] + " ");
			}
			System.out.println();
		}
	}

}
	
