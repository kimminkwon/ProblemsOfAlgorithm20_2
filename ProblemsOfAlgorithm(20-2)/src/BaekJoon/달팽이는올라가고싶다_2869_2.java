package BaekJoon;

import java.io.*;
import java.util.*;

public class 달팽이는올라가고싶다_2869_2 {
	
	private static int A, B, V;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		A = Integer.parseInt(st.nextToken());
		B = Integer.parseInt(st.nextToken());
		V = Integer.parseInt(st.nextToken());
		
		int dest = V - A;
		int remain = dest % (A - B);
		int res = dest / (A - B) + 1;
		
		System.out.println(remain == 0 ? res : res + 1);
	}

}
