package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 녹색옷입은애가젤다지_4485 {

    private static class Node {
        int v, w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
    private static final int INF = 100000000;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int problemNum = 1;
        while(true) {
            int N = Integer.parseInt(br.readLine());
            if(N == 0) break;
            int[][] map = new int[N][N];
            for(int i = 0; i < N; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j = 0; j < N; j++)
                    map[i][j] = Integer.parseInt(st.nextToken());
            }
            sb.append("Problem ").append(problemNum++).append(": ").append(findMinimumLoseRupee(N, map)).append("\n");
        }
        System.out.print(sb.toString());

    }

    private static int findMinimumLoseRupee(int N, int[][] map) {
        List<List<Node>> adjList = getLocationVertex(N, map);
        int[] minPath = new int[N * N];
        Arrays.fill(minPath, INF);
        minPath[0] = map[0][0];
        boolean[] visited = new boolean[N * N];

        for(int V = 0; V < N * N; V++) {
            int min = Integer.MAX_VALUE, minIndex = -1;
            for(int i = 0; i < N * N; i++) {
                if(!visited[i] && minPath[i] < min) {
                    min = minPath[i];
                    minIndex = i;
                }
            }
            visited[minIndex] = true;
            for(Node n : adjList.get(minIndex)) {
                if(!visited[n.v] && minPath[n.v] > min + n.w)
                    minPath[n.v] = min + n.w;
            }
        }

        return minPath[N * N - 1];
    }

    private static void printArr(int[][] adjArr) {
        System.out.println("인접행렬====================");
        for(int i = 0; i < adjArr.length; i++) {
            for(int j = 0; j < adjArr[i].length; j++) {
                System.out.print(adjArr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static List<List<Node>> getLocationVertex(int N, int[][] map) {
        Map<Integer, String> hm1 = new HashMap<>();
        Map<String, Integer> hm2 = new HashMap<>();
        List<List<Node>> adjList = new ArrayList<>();
        for(int i = 0; i < N * N; i++) adjList.add(new ArrayList<>());

        int cnt = 0;
        for(int i = 0; i < N; i++)
            for(int j = 0; j < N; j++) {
                hm1.put(cnt, String.valueOf(i) + "/" + String.valueOf(j));
                hm2.put(String.valueOf(i) + "/" + String.valueOf(j), cnt++);
            }

        for(int i = 0; i < N * N; i++) {
            for(int d = 0; d < 4; d++) {
                String[] box1 = hm1.get(i).split("/");
                int nx = Integer.parseInt(box1[0]) + dx[d], ny = Integer.parseInt(box1[1]) + dy[d];
                if(isOut(nx, ny, N)) continue;
                int adjVNum = hm2.get(String.valueOf(nx) + "/" + String.valueOf(ny));
                adjList.get(i).add(new Node(adjVNum, map[nx][ny]));
            }
        }
        return adjList;
    }

    private static boolean isOut(int x, int y, int N) {
        return x >= N || y >= N || x < 0 || y < 0;
    }
}
