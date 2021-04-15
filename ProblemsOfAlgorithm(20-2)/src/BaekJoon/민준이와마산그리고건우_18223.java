package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 민준이와마산그리고건우_18223 {

    private static class Edge {
        int to, weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    private static int V, E, P;
    private static List<List<Edge>> adjList = new ArrayList<>();
    private static final int INF = 2000000000;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken()) - 1;

        for(int i = 0; i < V; i++) adjList.add(new ArrayList<>());
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            adjList.get(from).add(new Edge(to, weight));
            adjList.get(to).add(new Edge(from, weight));
        }

        int[] minPathStartedZero = getMinPath(0);
        int[] minPathStartedP = getMinPath(P);

        System.out.println(Arrays.toString(minPathStartedZero));
        System.out.println(Arrays.toString(minPathStartedP));

        System.out.println("바로 V까지 가는 경로: " + minPathStartedZero[V - 1]);
        System.out.println("0 to P: " + minPathStartedZero[P]);
        System.out.println("P to V: " + minPathStartedP[V - 1]);
        if(minPathStartedP[V - 1] == INF) {
            System.out.println("GOOD BYE");
        } else if(minPathStartedZero[V - 1] == (minPathStartedZero[P] + minPathStartedP[V - 1])) {
            System.out.println("SAVE HIM");
        } else {
            System.out.println("GOOD BYE");
        }
    }

    private static int[] getMinPath(int start) {
        int[] minPath = new int[V];
        Arrays.fill(minPath, INF);
        minPath[start] = 0;
        boolean[] visited = new boolean[V];

        for(int v = 0; v < V; v++) {
            // 1. 가장 인접한 정점 찾기
            int min = INF, minIndex = -1;
            for(int i = 0; i < V; i++) {
                if(min > minPath[i] && !visited[i]) {
                    min = minPath[i];
                    minIndex = i;
                }
            }

            // 2. 방문 처리
            if(minIndex == -1) break;
            visited[minIndex] = true;

            // 3. minPath 업데이트
            for(Edge edge : adjList.get(minIndex))
                if(!visited[edge.to] && min + edge.weight < minPath[edge.to])
                    minPath[edge.to] = min + edge.weight;
        }
        return minPath;
    }
}
