package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 택배배송_5972 {

    private static class Edge {
        int to, weight;
        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }
    private static final int INF = 2000000000;
    private static int N, M;
    private static List<List<Edge>> adjList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        for(int i = 0; i < N; i++) adjList.add(new ArrayList<>());

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            adjList.get(from).add(new Edge(to, weight));
            adjList.get(to).add(new Edge(from, weight));
        }
        int[] minPath = findMinimumPath();
        System.out.println(minPath[N - 1]);
    }

    private static int[] findMinimumPath() {
        int[] minPath = new int[N];
        Arrays.fill(minPath, INF);
        minPath[0] = 0;
        boolean[] visited = new boolean[N];
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o1.weight - o2.weight);
        pq.offer(new Edge(0, 0));
        while (!pq.isEmpty()) {
            // 1. 가장 가까운 거리인 정점 선택
            Edge currE = pq.poll();

            // 2. 방문 처리
            visited[currE.to] = true;

            // 3. 이번에 선택한 정점을 경유해서 가도록 설정
            for(Edge edge : adjList.get(currE.to)) {
                if(!visited[edge.to] && currE.weight + edge.weight < minPath[edge.to]) {
                    pq.offer(new Edge(edge.to, currE.weight + edge.weight));
                    minPath[edge.to] = currE.weight + edge.weight;
                }
            }
        }

        return minPath;
    }
}
