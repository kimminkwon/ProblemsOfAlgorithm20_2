package BaekJoon;

import java.io.*;
import java.util.*;

public class 슈퍼마리오_2851 {
	
	private static int[] mush = new int[10];
	private static int score, diff = Integer.MAX_VALUE;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		for(int i = 0; i < 10; i++) mush[i] = Integer.parseInt(br.readLine());
		
		findMaximumScore();
		System.out.println(score);
	}

	private static void findMaximumScore() {
		for(int i = 0; i < 10; i++) {
			int currScore = 0;
			for(int j = 0; j <= i; j++) 
				currScore += mush[j];
			int currDiff = Math.abs(100 - currScore);
			if(diff > currDiff) {
				diff = currDiff;
				score = currScore;
			} else if(diff == currDiff) score = Math.max(currScore, score);
		}
	}
}
