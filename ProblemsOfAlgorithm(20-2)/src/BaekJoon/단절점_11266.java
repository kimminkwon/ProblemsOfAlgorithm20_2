package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class 단절점_11266 {

    private static class Node {
        int v, weight;
        public Node(int v, int weight) {
            this.v = v;
            this.weight = weight;
        }
    }
    private static final int INF = 1000000000;
    private static int V, E;
    private static List<List<Integer>> adjList = new ArrayList<>();
    private static int[] color; // 0 = white, 1 = grey, 2 = black;
    private static int[] discoverTime, finishTime;
    private static boolean[] isCutV;
    private static int time;

    private static int doDFS(int v, boolean isRoot) {
        color[v] = 1;
        discoverTime[v] = ++time;

        int minDT = discoverTime[v], childNum = 0;
        for(Integer i : adjList.get(v)) {
            if(color[i] == 0) {
                childNum++;
                int childDT = doDFS(i, false);
                minDT = Math.min(minDT, childDT);
                if(!isRoot && childDT >= discoverTime[v]) isCutV[v] = true;
            } else if(color[i] == 1) minDT = Math.min(discoverTime[i], minDT);
        }
        if(isRoot && childNum >= 2) isCutV[v] = true;
        color[v] = 2;
        finishTime[v] = ++time;

        return minDT;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        color = new int[V]; discoverTime = new int[V]; finishTime = new int[V];
        isCutV = new boolean[V];
        time = 0;

        for(int i = 0; i < V; i++) adjList.add(new ArrayList<>());
        for(int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            adjList.get(from).add(to);
            adjList.get(to).add(from);
        }

        for(int i = 0; i < V; i++) if(color[i] == 0) doDFS(i, true);

        int numOfCutV = 0;
        for(int i = 0; i < V; i++)
            if(isCutV[i]) {
                numOfCutV++;
                sb.append(i + 1).append(" ");
            }

        System.out.println(numOfCutV);
        System.out.println(sb.toString());
    }
}
