package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

public class Solution_가랏RC카_D2_1940 {
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
        	int N = Integer.parseInt(br.readLine());
        	int[][] command = new int[N][2];
        	for(int i = 0; i < N; i++) {
            	StringTokenizer st = new StringTokenizer(br.readLine());
        		command[i][0] = Integer.parseInt(st.nextToken());
        		if(command[i][0] != 0) command[i][1] = Integer.parseInt(st.nextToken());
        	}
        	
        	System.out.println("#" + tc + " " + doRCCar(N, command));
        }
        	
        br.close();
	}

	private static int doRCCar(int n, int[][] commands) {
		int speed = 0; int length = 0;
		for(int[] command : commands) {
			switch(command[0]) {
			case 1:
				speed += command[1];
				break;
			case 2:
				speed -= command[1];
				break;
			}
			if(speed <= 0) speed = 0;
			length += speed;
		}
		return length;
	}

}
