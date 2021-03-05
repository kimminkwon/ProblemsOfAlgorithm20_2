package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class 돌게임_9658 {

    private static int N;
    private static boolean[] isWin;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        isWin = new boolean[N];

        isWin[0] = false;
        if(N > 1) isWin[1] = true;
        if(N > 2) isWin[2] = false;
        if(N > 3) isWin[3] = true;

        for(int i = 4; i < N; i++)
            isWin[i] = !(isWin[i - 1] && isWin[i - 3] && isWin[i - 4]);

        System.out.println(isWin[N - 1] ? "SK" : "CY");
    }
}
