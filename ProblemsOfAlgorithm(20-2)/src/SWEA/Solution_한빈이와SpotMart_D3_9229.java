package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_한빈이와SpotMart_D3_9229 {
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int N = Integer.parseInt(st.nextToken());
        	int M = Integer.parseInt(st.nextToken());
        	List<Integer> snack = new ArrayList<>();
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < N; i++) 
        		snack.add(Integer.parseInt(st.nextToken()));
        	
        	System.out.println("#" + tc + " " + findMaximumWeightOfSnacks(N, M, snack));
        }
        br.close();
	}

	private static int findMaximumWeightOfSnacks(int N, int M, List<Integer> snack) {
		int result = -1;
		Collections.sort(snack);
	
		for(int i = 0; i < N; i++) 
			for(int j = 0; j < N; j++) 
				if(i != j) 
					if(snack.get(i) + snack.get(j) > M) break;
					else result = Math.max(result, snack.get(i) + snack.get(j));
		
		return result;
	}

}
