package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 줄서기_2273 {

    private static final int INF = 2000000000;
    private static int N, M;
    private static int[][] backArr, frontArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        backArr = new int[N][N]; frontArr = new int[N][N];

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            backArr[from][to] = 1; // [a][?]는 a보다 뒤에 설 사람이 연결된다.
            frontArr[to][from] = 1; // [a][?]는 a보다 앞에 설 사람이 연결된다.
        }

        findAllPossiblePath(backArr);
        findAllPossiblePath(frontArr);

        System.out.print(printResult());
    }

    private static String printResult() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            String result = findMyPositionRange(i);
            if(result.equals("-1")) return "-1\n";
            else sb.append(result).append("\n");
        }
        return sb.toString();
    }

    private static String findMyPositionRange(int sNum) {
        int frontNum = 0, backNum = 0;
        for(int i = 0; i < N; i++) {
            if(i != sNum && backArr[sNum][i] == 1) backNum++; // 나보다 뒤에 설 사람 체크
            if(i != sNum && frontArr[sNum][i] == 1) frontNum++; // 나보다 앞에 설 사람 체크
        }
        int front = 1 + frontNum, back = N - backNum;
        if(front > back) return "-1";
        else return String.valueOf(front) + " " + String.valueOf(back);
    }

    private static void findAllPossiblePath(int[][] minPath) {
        for(int k = 0; k < N; k++)
            for(int i = 0; i < N; i++)
                for(int j = 0; j < N; j++)
                    if(i != j && i != k && j != k)
                        if(minPath[i][k] == 1 && minPath[k][j] == 1) minPath[i][j] = 1;
    }
}
