package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 카드구매하기_11052 {

    private static int N;
    private static int[] P;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        P = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++)
            P[i] = Integer.parseInt(st.nextToken());

        System.out.println(findMaximumPriceForCards());
    }

    private static int findMaximumPriceForCards() {
        int[] cardDP = new int[N + 1]; // 카드 N개를 구매하는 최대 값
        // DP[N] = 카드 i개짜리 팩 + DP[N - i]
        for(int n = 1; n <= N; n++) {
            for(int i = 1; i <= n; i++) {
                cardDP[n] = Math.max(cardDP[n], P[i] + cardDP[n - i]);
            }
        }
        return cardDP[N];
    }

    private static void print(int[][] arr) {
        for(int i = 1; i < arr.length; i++) {
            for(int j = 1; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
