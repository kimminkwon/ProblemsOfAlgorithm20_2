package BaekJoon;

import java.io.*;
import java.util.*;

public class 서강그라운드_14938 {

    private static final int INF = 100000000;
    private static int n, m, r;
    private static int[] vertex;
    private static int[][] adjArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        vertex = new int[n];
        adjArr = new int[n][n];

        for(int i = 0; i < n; i++) Arrays.fill(adjArr[i], INF);
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < n; i++) vertex[i] = Integer.parseInt(st.nextToken());

        for(int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            adjArr[from][to] = weight;
            adjArr[to][from] = weight;
        }
        // int[][] minDist = findShortestPathForAllPair();
        int[][] minDist = findShortestPathForAllPairUsingD();
        System.out.println(calculateMaxProfit(minDist));
    }

    private static int calculateMaxProfit(int[][] minDist) {
        int maxProfit = 0;
        for(int i = 0; i < n; i++) {
            int currMaxProfit = vertex[i];
            for(int j = 0; j < n; j++)
                if(i != j && minDist[i][j] <= m) currMaxProfit += vertex[j];
            maxProfit = Math.max(maxProfit, currMaxProfit);
        }
        return maxProfit;
    }

    private static int[][] findShortestPathForAllPairUsingD() {
        int[][] minDist = new int[n][n];
        for(int i = 0; i < n; i++) Arrays.fill(minDist[i], INF);
        for(int k = 0; k < n; k++) {
            minDist[k][k] = 0;
            boolean[] visited = new boolean[n];
            for(int V = 0; V < n; V++) {
                int min = INF, minIndex = -1;
                for(int i = 0; i < n; i++) {
                    if(min > minDist[k][i] && !visited[i]) {
                        min = minDist[k][i];
                        minIndex = i;
                    }
                }
                if(minIndex == -1) break;
                visited[minIndex] = true;
                for(int i = 0; i < n; i++) {
                    if(!visited[i] && minDist[k][i] > min + adjArr[minIndex][i])
                        minDist[k][i] = min + adjArr[minIndex][i];
                }
            }
        }
        return minDist;
    }

    private static int[][] findShortestPathForAllPair() {
        int[][] minDist = makeInitDist();
        for(int k = 0; k < n; k++)
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    minDist[i][j] = Math.min(minDist[i][j], minDist[i][k] + minDist[k][j]);
        return minDist;
    }

    private static int[][] makeInitDist() {
        int[][] minDist = new int[n][n];
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                minDist[i][j] = adjArr[i][j];
        return minDist;
    }
}

