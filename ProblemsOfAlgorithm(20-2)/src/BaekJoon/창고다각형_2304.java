package BaekJoon;

import java.io.*;
import java.util.*;

public class 창고다각형_2304 {
	
	private static class Pillar {
		int loc, height;

		public Pillar(int loc, int height) {
			this.loc = loc;
			this.height = height;
		}
	}
	
	private static int N, result;
	private static Pillar[] pillars;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		pillars = new Pillar[N];
		
		for(int i = 0; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			pillars[i] = new Pillar(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
		}
		
		findMinimumArea();
		System.out.println(result);
	}
	
	private static void findMinimumArea() {
		Arrays.sort(pillars, (o1, o2) -> o1.loc - o2.loc);
		int maxIndex = 0, max = 0;
		for(int i = 0; i < N; i++)
			if(max < pillars[i].height) {
				maxIndex = i;
				max = pillars[i].height;
			}
		int area = pillars[maxIndex].height;
		
		int currH = pillars[0].height; int currL = pillars[0].loc;
		for(int i = 1; i <= maxIndex; i++) {
			if(pillars[i].height >= currH) {
				area += (currH * (pillars[i].loc - currL));
				currH = pillars[i].height;
				currL = pillars[i].loc;
			}
		}
		
		currH = pillars[N - 1].height; currL = pillars[N - 1].loc;
		for(int i = N - 1; i >= maxIndex; i--) {
			if(pillars[i].height >= currH) {
				area += (currH * Math.abs(pillars[i].loc - currL));
				currH = pillars[i].height;
				currL = pillars[i].loc;
			}
		}
		result = area;
	}
}
