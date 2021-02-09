import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_스도쿠검증_D2_1974 {
	
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_스도쿠검증.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
        	int[][] sdoku = new int[9][9];
        	
        	for(int i = 0; i < 9; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
            	for(int j = 0; j < 9; j++) {
            		sdoku[i][j] = Integer.parseInt(st.nextToken());
            	}
        	}
        	
        	System.out.println("#" + tc + " " + isCorrectSdoku(sdoku));
        }
        br.close();
	}

	private static int isCorrectSdoku(int[][] sdoku) {
		if(!verticalCheck(sdoku)) return 0;
	
		if(!horizentalCheck(sdoku)) return 0;
		
		for(int i = 0; i < 9; i = i + 3)
			for(int j = 0; j < 9; j = j + 3)
				if(!subBoxCheck(sdoku, i, j)) return 0;
			
		return 1;
	}
	
	private static boolean subBoxCheck(int[][] sdoku, int x, int y) {
		boolean[] numCheck = new boolean[10];
		for(int i = x; i < x + 3; i++) {
			for(int j = y; j < y + 3; j++) {
				if(numCheck[sdoku[i][j]]) return false;
				numCheck[sdoku[i][j]] = true;
			}
		}
		return true;
	}

	private static boolean horizentalCheck(int[][] sdoku) {
		boolean[] numCheck = new boolean[10];
		for(int i = 0; i < 9; i++) {
			Arrays.fill(numCheck, false);
			for(int j = 0; j < 9; j++) {
				if(numCheck[sdoku[j][i]]) return false;
				numCheck[sdoku[j][i]] = true;
			}
		}
		return true;
	}

	private static boolean verticalCheck(int[][] sdoku) {
		boolean[] numCheck = new boolean[10];
		for(int i = 0; i < 9; i++) {
			Arrays.fill(numCheck, false);
			for(int j = 0; j < 9; j++) {
				if(numCheck[sdoku[i][j]]) return false;
				numCheck[sdoku[i][j]] = true;
			}
		}
		return true;
	}
}
