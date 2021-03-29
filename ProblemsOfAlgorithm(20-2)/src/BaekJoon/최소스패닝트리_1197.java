package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 최소스패닝트리_1197 {

    private static class Node {
        int v, weight;
        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
    private static final int INF = 1000000000;
    private static int V, E;
    private static List<List<Node>> adjList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for(int i = 0; i < V; i++) adjList.add(new ArrayList<>());
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            int w = Integer.parseInt(st.nextToken());
            adjList.get(from).add(new Node(to, w));
            adjList.get(to).add(new Node(from, w));
        }
        System.out.println(makeMST());
    }

    private static long makeMST() {
        long result = 0;
        int[] minDist = new int[V];
        Arrays.fill(minDist, INF);
        boolean[] visited = new boolean[V];
        minDist[0] = 0;

        for(int v = 0; v < V; v++) {
            int min = INF, minIndex = -1;
            for(int i = 0; i < V; i++) {
                if(!visited[i] && min > minDist[i]) {
                    minIndex = i;
                    min = minDist[i];
                }
            }
            result += min;
            visited[minIndex] = true;

            for(Node n : adjList.get(minIndex)) {
                if(!visited[n.v] && minDist[n.v] > n.weight) {
                    minDist[n.v] = n.weight;
                }
            }
        }
        return result;
    }
}
