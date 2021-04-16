package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 운동_1956 {


    private static class Edge {
        int to, weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    private static final int INF = 200000000;
    private static int V, E;
    private static int[][] adjArr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        adjArr = new int[V][V];
        for(int i = 0; i < V; i++) Arrays.fill(adjArr[i], INF);
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            adjArr[from][to] = weight;
        }

        for(int k = 0; k < V; k++)
            for(int i = 0; i < V; i++)
                for(int j = 0; j < V; j++)
                    if(i != j && k != i && k != j) adjArr[i][j] = Math.min(adjArr[i][j], adjArr[i][k] + adjArr[k][j]);

        int result = findCycle();
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static int findCycle() {
        int cycleResult = Integer.MAX_VALUE;
        for(int i = 0; i < V; i++) {
            for(int j = 0; j < V; j++) {
                if(i != j && adjArr[i][j] != INF && adjArr[j][i] != INF) {
                    cycleResult = Math.min(cycleResult, adjArr[i][j] + adjArr[j][i]);
                }
            }
        }
        return cycleResult;
    }

    private static void printMap(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] == INF ? "X " : arr[i][j] + " ") ;
            }
            System.out.println();
        }
    }
}
