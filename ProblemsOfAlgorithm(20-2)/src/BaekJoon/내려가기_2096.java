package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 내려가기_2096 {

    private static int N, maxResult, minResult;
    private static int[][] board;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][3];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            board[i][0] = Integer.parseInt(st.nextToken());
            board[i][1] = Integer.parseInt(st.nextToken());
            board[i][2] = Integer.parseInt(st.nextToken());
        }

        findMaximumScore();
        System.out.println(maxResult + " " + minResult);
    }

    private static void findMaximumScore() {
        int[][] maxBoardDp = new int[N][3];
        int[][] minBoardDp = new int[N][3];

        maxBoardDp[0][0] = board[0][0]; maxBoardDp[0][1] = board[0][1]; maxBoardDp[0][2] = board[0][2];
        minBoardDp[0][0] = board[0][0]; minBoardDp[0][1] = board[0][1]; minBoardDp[0][2] = board[0][2];

        for(int i = 1; i < N; i++) {
            maxBoardDp[i][0] = Math.max(maxBoardDp[i - 1][0] + board[i][0], maxBoardDp[i - 1][1] + board[i][0]);
            maxBoardDp[i][2] = Math.max(maxBoardDp[i - 1][2] + board[i][2], maxBoardDp[i - 1][1] + board[i][2]);
            maxBoardDp[i][1] = Math.max(maxBoardDp[i - 1][0] + board[i][1], Math.max(maxBoardDp[i - 1][2] + board[i][1], maxBoardDp[i - 1][1] + board[i][1]));

            minBoardDp[i][0] = Math.min(minBoardDp[i - 1][0] + board[i][0], minBoardDp[i - 1][1] + board[i][0]);
            minBoardDp[i][2] = Math.min(minBoardDp[i - 1][2] + board[i][2], minBoardDp[i - 1][1] + board[i][2]);
            minBoardDp[i][1] = Math.min(minBoardDp[i - 1][0] + board[i][1], Math.min(minBoardDp[i - 1][2] + board[i][1], minBoardDp[i - 1][1] + board[i][1]));
        }

        maxResult = Math.max(maxBoardDp[N - 1][0], Math.max(maxBoardDp[N - 1][1], maxBoardDp[N - 1][2]));
        minResult = Math.min(minBoardDp[N - 1][0], Math.min(minBoardDp[N - 1][1], minBoardDp[N - 1][2]));
    }
}
