package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 그대그머가되어_14496 {

    private static final int INF = 100000000;
    private static int a, b, N, M;
    private static List<List<Integer>> adjList = new ArrayList<>();;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        a = Integer.parseInt(st.nextToken());
        b = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for(int i = 0; i <= N; i++) adjList.add(new ArrayList<>());
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        int[] minPath = findMinimumReplacement();
        System.out.println(minPath[b] == INF ? -1 : minPath[b]);
    }

    private static int[] findMinimumReplacement() {
        int[] minPath = new int[N + 1];
        Arrays.fill(minPath, INF);
        minPath[a] = 0;
        boolean[] visited = new boolean[N + 1];

        for(int V = 0; V < N; V++) {
            // 1. 가장 가까운 거리인 V를 찾기
            int min = INF, minIndex = -1;
            for(int i = 1; i <= N; i++) {
                if(!visited[i] && min > minPath[i]) {
                    min = minPath[i];
                    minIndex = i;
                }
            }
            if(minIndex == -1) break;
            visited[minIndex] = true;

            // 2. 선택된 V를 기준으로 값 업데이트
            for(int v : adjList.get(minIndex)) {
                if(!visited[v] && minPath[v] > min + 1)
                    minPath[v] = min + 1;
            }
        }
        return minPath;
    }
}
