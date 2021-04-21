package BaekJoon;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파일합치기_11066 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {
            int K = Integer.parseInt(br.readLine());
            int[] pages = new int[K];
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int i = 0; i < K; i++)
                pages[i] = Integer.parseInt(st.nextToken());
            sb.append(calculateMinimumMargePages(K, pages)).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
    }

    private static int calculateMinimumMargePages(int K, int[] pages) {
        int[] prefixSum = new int[K];
        prefixSum[0] = pages[0];
        for(int i = 1; i < K; i++)
            prefixSum[i] = prefixSum[i - 1] + pages[i];

        int[][] prefixDp = new int[K][K];
        minimumMargePages(prefixSum, prefixDp, pages, 0, K - 1);

        return prefixDp[0][K - 1];
    }

    private static int minimumMargePages(int[] prefixSum, int[][] prefixDp, int[] pages, int start, int end) {
        if(prefixDp[start][end] > 0) return prefixDp[start][end];
        if(start == end) return prefixDp[start][start] = 0;
        if(start + 1 == end) return prefixDp[start][end] = pages[start] + pages[end];

        prefixDp[start][end] = Integer.MAX_VALUE;
        for(int k = start; k < end; k++) {
            prefixDp[start][end] = Math.min(prefixDp[start][end],
                    minimumMargePages(prefixSum, prefixDp, pages, start, k)
                            + minimumMargePages(prefixSum, prefixDp, pages, k + 1, end));
        }
        if(start == 0) return prefixDp[start][end] = prefixDp[start][end] + prefixSum[end];
        else return prefixDp[start][end] = prefixDp[start][end] + prefixSum[end] - prefixSum[start - 1];
    }
}
