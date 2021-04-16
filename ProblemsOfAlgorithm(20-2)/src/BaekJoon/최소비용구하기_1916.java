package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 최소비용구하기_1916 {

    private static class Edge {
        int to, weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    private static final int INF = 200000000;
    private static int N, M;
    private static List<List<Edge>> adjList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        for(int i = 0; i < N; i++) adjList.add(new ArrayList<>());
        for(int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            adjList.get(from).add(new Edge(to, weight));
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;
        int[] minPath = findMinimumPath(start);

        System.out.println(minPath[end]);
    }

    private static int[] findMinimumPath(int start) {
        int[] minPath = new int[N];
        Arrays.fill(minPath, INF);
        minPath[start] = 0;
        boolean[] visited = new boolean[N];

        for(int v = 0; v < N; v++) {
            // 1. 가장 가까운 정점 선택
            int min = INF, minIndex = -1;
            for(int i = 0; i < N; i++) {
                if(!visited[i] && minPath[i] < min) {
                    min = minPath[i];
                    minIndex = i;
                }
            }

            // 2. 방문 처리
            if(minIndex == -1) break;
            visited[minIndex] = true;

            // 3. 해당 정점을 경유하는 것으로 업데이트
            for(Edge e : adjList.get(minIndex)) {
                if(!visited[e.to] && e.weight + min < minPath[e.to]) {
                    minPath[e.to] = e.weight + min;
                }
            }
        }
        return minPath;
    }
}
