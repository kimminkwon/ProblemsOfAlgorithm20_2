package SWEA;

import java.io.*;
import java.util.*;

public class Solution_Contact_D4_SW문제해결기본 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_Contact.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int tc = 1;
        
        while(true) {
            int[][] adjArr = initArr();
            String box = br.readLine();
            if(box == null) break;
            StringTokenizer st = new StringTokenizer(box);
            int N = Integer.parseInt(st.nextToken()) / 2;
            int start = Integer.parseInt(st.nextToken());
            
            st = new StringTokenizer(br.readLine());
            for(int i = 0; i < N; i++) 
            	adjArr[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 1;
            
            sb.append("#").append(tc++).append(" ").append(findLastCall(N, start, adjArr)).append("\n");
        }
        
        
        System.out.println(sb.toString());
        br.close();
	}

	private static int findLastCall(int n, int start, int[][] adjArr) {
		Deque<Integer> q = new ArrayDeque<>();
		int[] depthArr = new int[101];
		Arrays.fill(depthArr, -1);
		boolean[] visited = new boolean[101];
		q.offer(start); visited[start] = true; depthArr[start] = 0;
		int depth = 1;
		
		while(!q.isEmpty()) {
            int size = q.size();
            while(--size >= 0) {
    			int currNum = q.poll();
    			for(int i = 0; i < 101; i++) {
    				if(adjArr[currNum][i] == 1 && !visited[i]) {
    					visited[i] = true;
    					q.offer(i);
    					depthArr[i] = depth;
    				}
    			}
            }
            depth++;
		}
		
		int maxIndex = -1, maxDepth = -1;
		for(int i = 0; i < 101; i++) {
			if(depthArr[i] >= maxDepth) {
				maxDepth = depthArr[i];
				maxIndex = i;
			}
		}
		return maxIndex;
	}

	private static int[][] initArr() {
		int[][] adjArr = new int[101][101];
        for(int i = 0; i < 101; i++) 
        	for(int j = 0; j < 101; j++)
        		adjArr[i][j] = -1;
        return adjArr;
	}

}
