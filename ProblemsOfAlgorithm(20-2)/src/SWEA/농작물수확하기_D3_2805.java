package SWEA;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 농작물수확하기_D3_2805 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine());
            int[][] farm = new int[N][N];
            for(int i = 0; i < N; i++) {
            	String s = br.readLine();
            	for(int j = 0; j < N; j++)
            		farm[i][j] = Character.getNumericValue(s.charAt(j));
            }
            
            System.out.println("#" + tc + " " + findMaximumProfit(N, farm));
        }
    }

	private static int findMaximumProfit(int N, int[][] farm) {
		int midIndex = N / 2; int startIndex = N / 2; int endIndex = N / 2;
		int profit = 0;
		for(int i = 0; i < N; i++) {
			for(int j = startIndex; j <= endIndex; j++) {
				profit += farm[i][j];
			}
			if(i < midIndex) {
				startIndex--;
				endIndex++;
			} else {
				startIndex++;
				endIndex--;
			}
		}
		
		return profit;
	}

}
