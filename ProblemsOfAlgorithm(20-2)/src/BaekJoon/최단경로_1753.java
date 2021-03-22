package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

public class 최단경로_1753 {
	
	private static class Node {
		int v, w;

		public Node(int v, int w) {
			this.v = v;
			this.w = w;
		}
	}
	private static int V, E, K;
	private static List<List<Node>> adjList;
	private static final int INF = 1000000;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		V = Integer.parseInt(st.nextToken());		
		E = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(br.readLine());
		adjList = new ArrayList<>();
		for(int i = 0; i <= V; i++) adjList.add(new ArrayList<>());
		for(int i = 0; i < E; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			int w = Integer.parseInt(st.nextToken());
			adjList.get(u).add(new Node(v, w));
		}
		
		int[] minPath = makeMinimumPathArr();
		for(int i = 1; i <= V; i++)
			sb.append(minPath[i] == INF ? "INF" : minPath[i]).append("\n");
		
		System.out.print(sb.toString());	
	}

	private static int[] makeMinimumPathArr() {
		int[] minPath = new int[V + 1];
		Arrays.fill(minPath, INF);
		minPath[K] = 0;
		boolean[] visited = new boolean[V + 1];
		
		for(int v = 0; v < V; v++) {
			int min = INF;
			int minIndex = -1;

			// 1. 최소인 값을 고른다.
			for(int i = 1; i < V + 1; i++) {
				if(!visited[i] && min > minPath[i]) {
					min = minPath[i];
					minIndex = i;
				}
			}
			if(minIndex == -1) break;
			visited[minIndex] = true;
			
			// 2. 해당 경로를 거쳐서 간 경로가 더 짧은가 확인 후 minDist 업데이트
			for(Node n : adjList.get(minIndex)) {
				if(!visited[n.v] && minPath[n.v] > min + n.w)
					minPath[n.v] = min + n.w;	
			}
		}
		return minPath;
	}
}
	
