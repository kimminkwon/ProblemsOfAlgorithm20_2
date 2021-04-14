package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 게임개발_1516 {

    private static int N;
    private static List<List<Integer>> adjList = new ArrayList<>();
    private static int[] vertex, numOfEdge;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        vertex = new int[N];
        numOfEdge = new int[N];

        for(int i = 0; i < N; i++) adjList.add(new ArrayList<>());

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            vertex[i] = Integer.parseInt(st.nextToken());
            while(true) {
                int box = Integer.parseInt(st.nextToken());
                if(box == -1) break;
                adjList.get(box - 1).add(i); // 역순으로 저장
                numOfEdge[i]++;
            }
        }
        StringBuilder sb = new StringBuilder();
        int[] topological = topologicalSorting();
        for(int i = 0; i < N; i++)
            sb.append(topological[i] + vertex[i]).append("\n");
        System.out.print(sb.toString());
    }

    private static int[] topologicalSorting() {
        int[] topological = new int[N];
        Deque<Integer> q = new ArrayDeque<>();

        // 1. 초기 세팅
        for(int i = 0; i < N; i++)
            if(numOfEdge[i] == 0) q.offer(i);

        // 2. 위상 정렬
        while(!q.isEmpty()) {
            int currV = q.poll();
            for(int adjV : adjList.get(currV)) {
                topological[adjV] = Math.max(topological[adjV], topological[currV] + vertex[currV]);
                numOfEdge[adjV]--;
                if(numOfEdge[adjV] == 0) q.offer(adjV);
            }
        }
        return topological;
    }

    private static void print() {
        System.out.println(Arrays.toString(vertex));
        System.out.println(Arrays.toString(numOfEdge));

        for(int i = 0; i < N; i++)
            System.out.println(adjList.get(i));
    }
}
