package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 네트워크연결_1922 {

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
            int v1 = Integer.parseInt(st.nextToken()) - 1;
            int v2 = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            adjList.get(v1).add(new Edge(v2, weight));
            adjList.get(v2).add(new Edge(v1, weight));
        }

        System.out.println(makeMST());
    }

    private static int makeMST() {
        int[] minPath = new int[N];
        boolean[] visited = new boolean[N];
        Arrays.fill(minPath, INF);
        minPath[0] = 0;
        int result = 0;

        for(int v = 0; v < N; v++) {
            // 1. 최소 거리 정점 선택
            int minIndex = -1, min = INF;
            for(int i = 0; i < N; i++) {
                if(!visited[i] && minPath[i] < min) {
                    minIndex = i;
                    min = minPath[i];
                }
            }
            // 2. 선택된 정점 값 저장
            result += min;
            visited[minIndex] = true;

            // 3. 최소 거리 업데이트
            for(Edge e : adjList.get(minIndex)) {
                if(!visited[e.to] && e.weight < minPath[e.to]) minPath[e.to] = e.weight;
            }
        }
        return result;
    }
}

