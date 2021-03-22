package Jungol;

import java.io.*;
import java.util.*;

public class Main_Jongol_1681_해밀턴순환회로 {
	
	private static int N, result = Integer.MAX_VALUE;
	private static int[][] adjArr;
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        adjArr = new int[N][N];
        
        for(int i = 0; i < N; i++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < N; j++)
        		adjArr[i][j] = Integer.parseInt(st.nextToken());
        }
        
        findMinimumDeliveryPath(0, 0, 0, new int[N - 1], new boolean[N]);
        System.out.println(result);
    }

	private static void findMinimumDeliveryPath(int length, int preIndex, int cost, int[] perm, boolean[] visited) {
		if(length == N - 1) {
			if(adjArr[perm[N - 2]][0] != 0) result = Math.min(result, cost + adjArr[perm[N - 2]][0]);
			return;
		}
		
		if(cost >= result) return;
		
		for(int i = 1; i < N; i++) {
			if(visited[i] || adjArr[preIndex][i] == 0) continue;
			perm[length] = i;
			visited[i] = true;
			findMinimumDeliveryPath(length + 1, i, cost + adjArr[preIndex][i], perm, visited);
			visited[i] = false;
		}
	}
}
