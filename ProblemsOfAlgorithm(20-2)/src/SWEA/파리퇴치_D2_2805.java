package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 파리퇴치_D2_2805 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int[][] filesMap = new int[N][N];

            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++)
                    filesMap[i][j] = Integer.parseInt(st.nextToken());
            }
            System.out.println("#" + tc + " " + findMaximumFilesKilled(M, N, filesMap));
        }

    }

    private static int findMaximumFilesKilled(int sizeOfSwatter, int N, int[][] filesMap) {
        int maxFiles = Integer.MIN_VALUE;
        for(int i = 0; i <= N - sizeOfSwatter; i++)
            for(int j = 0; j <= N - sizeOfSwatter; j++)
                maxFiles = Math.max(maxFiles, killFiles(sizeOfSwatter, i, j, filesMap));
        return maxFiles;
    }

    private static int killFiles(int sizeOfSwatter, int one, int two, int[][] filesMap) {
        int numOfFiles = 0;
        for(int i = 0; i < sizeOfSwatter; i++)
            for(int j = 0; j < sizeOfSwatter; j++)
                numOfFiles += filesMap[one + i][two + j];
        return numOfFiles;
    }
}
