package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파이프옮기기1_17070 {

    private static int N, result;
    private static int[][] house;
    private static int[][][] move = { {{0, 1, 0}, {1, 1, 2}}, // 가로
                                      {{1, 0, 1}, {1, 1, 2}}, // 세로
                                      {{0, 1, 0}, {1, 0, 1}, {1, 1, 2}} // 대각선
                                    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        house = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++)
                house[i][j] = Integer.parseInt(st.nextToken());
        }
        findCaseOfPipeMove(0, 1, 0);
        System.out.println(result);
    }

    private static void findCaseOfPipeMove(int x, int y, int state) {
        if(x == N - 1 && y == N - 1) {
            result++;
            return;
        }
        for(int i = 0; i < move[state].length; i++) {
            if(move[state][i][0] == 1 && move[state][i][1] == 1) {
                if(isOut(x + 1, y) || isOut(x, y + 1) || isOut(x + 1, y + 1)) continue;
            } else if(isOut(x + move[state][i][0], y + move[state][i][1])) continue;
            findCaseOfPipeMove(x + move[state][i][0], y + move[state][i][1], move[state][i][2]);
        }
    }

    private static boolean isOut(int x, int y) {
        return x >= N || y >= N || x < 0 || y < 0 || house[x][y] == 1;
    }
}
