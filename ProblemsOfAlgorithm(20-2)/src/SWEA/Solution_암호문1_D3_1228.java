package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Solution_암호문1_D3_1228 {
	
	public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        
        for(int tc = 1; tc <= T; tc++) {
        	int N = Integer.parseInt(br.readLine());
        	LinkedList<Integer> pw = new LinkedList<>();
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < N; i++) pw.add(Integer.parseInt(st.nextToken()));

        	int C = Integer.parseInt(br.readLine());
        	st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < C; i++) {
        		st.nextToken();
        		int x = Integer.parseInt(st.nextToken());
        		int y = Integer.parseInt(st.nextToken());
        		for(int j = 0; j < y; j++)
        			pw.add(x + j, Integer.parseInt(st.nextToken()));
        	}
        	
        	System.out.println("#" + tc + " " + makeFinalPassword(pw));
        }
        	
        br.close();
	}

	private static String makeFinalPassword(LinkedList<Integer> pw) {
		StringBuilder sb = new StringBuilder();
		Iterator<Integer> iter = pw.iterator();
		for(int i = 0; i < 10; i++) sb.append(iter.next() + " ");
		return sb.toString();
	}

}
