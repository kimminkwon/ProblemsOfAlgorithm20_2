package SWEA;

import java.io.*;
import java.util.*;

public class Solution_최적경로_D5_1247 {

    private static class Coor {
        int x, y;
        public Coor(int x, int y) { this.x = x; this.y = y; }
    }
	
	private static int N, result;
	private static Coor[] clients;
	private static Coor home, company;
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_최적경로.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
        	N = Integer.parseInt(br.readLine());
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	company = new Coor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        	home = new Coor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        	clients = new Coor[N];
        	result = Integer.MAX_VALUE;
        	for(int i = 0; i < N; i++) 
        		clients[i] = new Coor(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        	permOfClient(company, 0, 0, 0);
        	sb.append("#").append(tc).append(" ").append(result).append("\n");
        }
        System.out.println(sb.toString());
        br.close();
	}
	
	private static void permOfClient(Coor preCoor, int count, int flag, int length) {
		if(result < length) return;
		if(count == N) {
			int nextlength = length + getDist(preCoor, home);
			result = Math.min(result, nextlength);
			return;
		}
		for(int i = 0; i < N; i++) {
			if((flag & 1 << i) == 0) {
				int nextlength = length + getDist(clients[i], preCoor);
				permOfClient(clients[i], count + 1, flag | 1 << i, nextlength);
			}
		}
	}
	
	private static int getDist(Coor c1, Coor c2) {
		return Math.abs(c1.x - c2.x) + Math.abs(c1.y - c2.y);
	}
}
