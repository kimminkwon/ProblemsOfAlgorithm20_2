package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class Z_1074 {

	private static int r, c;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		r = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		findVisitedNumber(0, (int) Math.pow(2, N) * (int) Math.pow(2, N), 0, 0, N);
	}


	private static void findVisitedNumber(int range1, int range2, int x1, int y1, int N) {
		if(range1 >= range2) {
			System.out.println(range1);
			return;
		}
		
		int subSize = (int) Math.pow(2, N - 1);
		int rLoc = 0; int cLoc = 0;
		int clusterSize = (int) Math.pow(subSize, 2);
		
		if(!(r < x1 + subSize)) rLoc++;
		if(!(c < y1 + subSize)) cLoc++;

		if(rLoc == 0 && cLoc == 0) findVisitedNumber(range1, range1 + clusterSize, x1, y1, N - 1);
		else if(rLoc == 1 && cLoc == 1) findVisitedNumber(range1 + (clusterSize * 3), range1 + (clusterSize * 4), x1 + subSize, y1 + subSize, N - 1);
		else if(rLoc == 0 && cLoc == 1) findVisitedNumber(range1 + clusterSize, range1 + (clusterSize * 2), x1, y1 + subSize, N - 1);
		else if(rLoc == 1 && cLoc == 0) findVisitedNumber(range1 + (clusterSize * 2), range1 + (clusterSize * 3), x1 + subSize, y1, N - 1);;
	}
}
