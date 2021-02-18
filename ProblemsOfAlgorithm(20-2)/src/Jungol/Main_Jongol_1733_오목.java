package Jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main_Jongol_1733_오목 {

    private static class Coor {
        int x, y;
        public Coor(int x, int y) { this.x = x; this.y = y; }
    }

    private static List<Coor> white = new ArrayList<>(), black = new ArrayList<>();
    private static int whiteNum, blackNum;
    private static final int SIZE = 20;
    private static int[][] board = new int[SIZE][SIZE];
    private static int[] dX = {-1, 0, 1, 1};
    private static int[] dY = {1, 1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 1; i < SIZE; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int j = 1; j < SIZE; j++) {
        		board[i][j] = Integer.parseInt(st.nextToken());
        		if(board[i][j] == 1) {
        			black.add(new Coor(i, j));
        			blackNum++;
        		} else if(board[i][j] == 2) {
        			white.add(new Coor(i, j));
        			whiteNum++;
        		}
        	}	
        }
        System.out.println(whoWin());
    }

	private static String whoWin() {
		StringBuilder sb = new StringBuilder();
		Collections.sort(black, (o1, o2) -> o1.y == o2.y ? o1.x - o2.x : o1.y - o2.y);
		Collections.sort(white, (o1, o2) -> o1.y == o2.y ? o1.x - o2.x : o1.y - o2.y);
		
		for(int i = 0; i < blackNum; i++) {
			Coor b = black.get(i);
			for(int d = 0; d < 4; d++) {
				if(!isOut(b.x - dX[d], b.y - dY[d]) && board[b.x - dX[d]][b.y - dY[d]] == 1) continue;
				else if(isWin(b.x, b.y, 1, 1, d)) {
					sb.append("1").append("\n").append(b.x).append(" ").append(b.y).append("\n");
					return sb.toString();
				}
			}	
		}
		
		for(int i = 0; i < whiteNum; i++) {
			Coor w = white.get(i);
			for(int d = 0; d < 4; d++) {
				if(!isOut(w.x - dX[d], w.y - dY[d]) && board[w.x - dX[d]][w.y - dY[d]] == 2) continue;
				else if(isWin(w.x, w.y, 2, 1, d)){
					sb.append("2").append("\n").append(w.x).append(" ").append(w.y).append("\n");
					return sb.toString();
				}
			}		
		}
		return "0";
	}
	
	private static boolean isWin(int x, int y, int num, int count, int dir) {
		if(count == 5) {
			int nX = x + dX[dir]; int nY = y + dY[dir];
			if(isOut(nX, nY)) return true;
			else if(board[nX][nY] == num) return false;
			else return true;
		}
		
		int nX = x + dX[dir]; int nY = y + dY[dir];
		if(!isOut(nX, nY) && board[nX][nY] == num) 
			return isWin(nX, nY, num, count + 1, dir);
		else return false;
	}
	
	private static boolean isOut(int x, int y) {
		return x >= SIZE || y >= SIZE || x < 1 || y < 1;
	}

}
