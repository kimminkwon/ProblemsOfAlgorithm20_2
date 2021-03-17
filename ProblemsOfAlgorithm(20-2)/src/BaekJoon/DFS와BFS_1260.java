package BaekJoon;

import java.io.*;
import java.util.*;

public class DFS와BFS_1260 {
	
	private static int N, M, V;
	private static List<List<Integer>> adjList = new LinkedList<>();
	private static StringBuilder sb = new StringBuilder();
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		for(int i = 0; i <= N; i++) adjList.add(new LinkedList<>());
		
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			int from = Integer.parseInt(st.nextToken());
			int to = Integer.parseInt(st.nextToken());
			adjList.get(from).add(to);
			adjList.get(to).add(from);
		}
		
		for(int i = 1; i <= N; i++) Collections.sort(adjList.get(i));
	
		doDFS(V, new boolean[N + 1]);
		sb.append("\n");
		doBFS();
		sb.append("\n");
		System.out.print(sb.toString());
	}

	private static void doDFS(int v, boolean[] visited) {
		visited[v] = true;
		sb.append(v).append(" ");
		
		for(int i : adjList.get(v))
			if(!visited[i]) doDFS(i, visited);
	}
	
	private static void doBFS() {
		Deque<Integer> q = new ArrayDeque<>();
		boolean[] visited = new boolean[N + 1];
		q.offer(V); visited[V] = true;
		
		while(!q.isEmpty()) {
			int currNum = q.poll();
			sb.append(currNum).append(" ");
			for(int i : adjList.get(currNum)) {
				if(!visited[i]) {
					visited[i] = true;
					q.offer(i);
				}
			}
		}
	}
}
	
