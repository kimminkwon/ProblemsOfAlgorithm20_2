package BaekJoon;

import java.io.*;
import java.util.*;

public class 특정거리의도시찾기_18352 {

    private static class Node {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    private static final int INF = 100000000;
    private static int N, M, K, X;
    private static List<List<Integer>> adjList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) adjList.add(new ArrayList<>());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList.get(from).add(to);
        }

        int[] minPath = findMinimumPath();
        for(int i = 1; i <= N; i++)
            if(minPath[i] == K) sb.append(i).append("\n");
        System.out.print(sb.length() == 0 ? -1 + "\n" : sb.toString());
    }

    private static int[] findMinimumPath() {
        int[] minPath = new int[N + 1];
        Arrays.fill(minPath, INF);
        minPath[X] = 0;
        boolean[] visited = new boolean[N + 1];

        PriorityQueue<Node> minQ = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        minQ.offer(new Node(X, 0));

        while(!minQ.isEmpty()) {
            // 1. 가장 가까운 정점 선택
            Node n = minQ.poll();
            if(visited[n.v]) continue;
            visited[n.v] = true;
            
            // 2. 가까운 정점을 경유지로 거리 업데이트
            for(int v : adjList.get(n.v)) {
                if(!visited[v] && minPath[v] > n.w + 1) {
                    minPath[v] = n.w + 1;
                    minQ.offer(new Node(v, n.w + 1));
                }
            }
        }
       
        return minPath;
    }
}
