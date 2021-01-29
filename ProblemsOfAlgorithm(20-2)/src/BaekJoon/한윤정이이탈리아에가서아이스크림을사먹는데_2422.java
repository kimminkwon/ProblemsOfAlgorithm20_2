package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 한윤정이이탈리아에가서아이스크림을사먹는데_2422 {
    private static int N, M, result = 0;
    private static boolean[][] isInsipid;

    public static void main(String[] args) throws IOException {
        makeInput();
        for(int i = 1; i < N; i++) findCombinationOfIceCream(i, 1, isInsipid[i]);
        printResult();
    }

    private static void findCombinationOfIceCream(int start, int depth, boolean[] isInsipidIn) {
        if(depth >= 3) { result++; return; }

        for(int i = start + 1; i <= N; i++)
            if(!isInsipidIn[i])
                findCombinationOfIceCream(i, depth + 1, makeInsipid(isInsipidIn, isInsipid[i]));
    }

    private static boolean[] makeInsipid(boolean[] isInsipidIn, boolean[] isInsipidNext) {
        boolean[] isInsipidTotal = new boolean[N+1];
        for(int i = 0; i <= N; i++) if(isInsipidIn[i] || isInsipidNext[i]) isInsipidTotal[i] = true;
        return isInsipidTotal;
    }

    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        isInsipid = new boolean[N+1][N+1];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int t1 = Integer.parseInt(st.nextToken()); int t2 = Integer.parseInt(st.nextToken());
            isInsipid[t1][t2] = true; isInsipid[t2][t1] = true;
        }
    }

    private static void printResult() { System.out.println(result); }
}
