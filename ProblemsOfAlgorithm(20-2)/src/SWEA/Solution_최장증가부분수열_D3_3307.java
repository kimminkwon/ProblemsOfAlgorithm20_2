package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_최장증가부분수열_D3_3307 {
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
        	int N = Integer.parseInt(br.readLine());
        	int[] nums = new int[N];
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < N; i++) nums[i] = Integer.parseInt(st.nextToken());
        	
        	System.out.println("#" + tc + " " + getLCS(N, nums));
        }
        	
        br.close();
	}

	private static int getLCS(int N, int[] nums) {
		int result = Integer.MIN_VALUE;
		int[] dpLCS = new int[N];
		
		for(int i = 0; i < N; i++) {
			dpLCS[i] = 1;
			for(int j = 0; j < i; j++) 
				if(nums[j] < nums[i] && dpLCS[i] < dpLCS[j] + 1) dpLCS[i] = dpLCS[j] + 1;
			result = Math.max(result, dpLCS[i]);
		}
		
		return result;
	}
}
