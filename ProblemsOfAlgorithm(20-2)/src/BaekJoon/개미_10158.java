package BaekJoon;

import java.io.*;
import java.util.*;

public class 개미_10158 {

	private static int w, h, p, q, t;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		w = Integer.parseInt(st.nextToken());
		h = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		p = Integer.parseInt(st.nextToken());
		q = Integer.parseInt(st.nextToken());
		t = Integer.parseInt(br.readLine());
		
		System.out.println(findLocationOfAnt());
	}

	private static String findLocationOfAnt() {
		int wRemain = (p + t) / w, hRemain = (q + t) / h;
		int xRes = (wRemain % 2 == 0) ? (p + t) % w : w - (p + t) % w;
		int yRes = (hRemain % 2 == 0) ? (q + t) % h : h - (q + t) % h;
		
		return String.format("%d %d", xRes, yRes);
	}
	
}
