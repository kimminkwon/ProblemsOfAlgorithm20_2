package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 세부_13905 {
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
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int s = Integer.parseInt(st.nextToken()) - 1;
        int e = Integer.parseInt(st.nextToken()) - 1;

        for(int i = 0; i < N; i++) adjList.add(new ArrayList<>());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int weight = Integer.parseInt(st.nextToken());
            adjList.get(from).add(new Edge(to, weight));
            adjList.get(to).add(new Edge(from, weight));
        }

        int result = findMaxPepero(s, e);
        System.out.println(result);
    }

    private static int findMaxPepero(int s, int e) {
        PriorityQueue<Edge> pq = new PriorityQueue<>((o1, o2) -> o2.weight - o1.weight);
        boolean[] visited = new boolean[N];
        int[] maxDist = new int[N];
        pq.add(new Edge(s, 0));
        maxDist[s] = INF;

        while(!pq.isEmpty()) {
            // 1. 가까운 정점 찾기
            Edge currE = pq.poll();
            // 2. 방문처리
            if(visited[currE.to]) continue;
            visited[currE.to] = true;
            // 3. 거리 업데이트
            for(Edge edge : adjList.get(currE.to)) {
                // Math.min(edge.weight, maxDist[currE.to]) ==> currE.to로 가는 최소 가중치
                // currE.to로 가는 최소 가중치를 가장 큰 값으로 계속 갱신한다.
                if(maxDist[edge.to] < Math.min(edge.weight, maxDist[currE.to])) {
                    pq.offer(new Edge(edge.to, Math.min(edge.weight, maxDist[currE.to])));
                    maxDist[edge.to] = Math.min(edge.weight, maxDist[currE.to]);
                }
            }
        }
        return maxDist[e];
    }
}
