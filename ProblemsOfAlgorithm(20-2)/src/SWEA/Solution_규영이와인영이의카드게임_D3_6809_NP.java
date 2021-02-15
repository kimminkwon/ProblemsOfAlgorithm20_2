package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_규영이와인영이의카드게임_D3_6809_NP {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_규영이와인영이의카드게임.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        
        for(int tc = 1; tc <= T; tc++) {
        	int[] cardOne = new int[9];
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int i = 0; i < 9; i++) 
        		cardOne[i] = Integer.parseInt(st.nextToken());

        	int[] cardTwo = makeCardTwo(cardOne);
        	System.out.println("#" + tc + " " + numOfWinAndLoseCase(cardOne, cardTwo));
        }
        
        br.close();
	}
	
	private static String numOfWinAndLoseCase(int[] cardOne, int[] cardTwo) {
		Arrays.sort(cardTwo);
		int win = 0; int lose = 0;
		do {
			int res = isWin(cardOne, cardTwo);
			if(res > 0) win++;
			else if(res < 0) lose++;
		} while(findCaseOfCardUsingNP(cardTwo));
		
		return winAndLoseToString(win, lose);
	}
	

	private static boolean findCaseOfCardUsingNP(int[] cardTwo) {
		int N = 9;
		int i = N - 1;
		while(i > 0 && cardTwo[i - 1] >= cardTwo[i]) i--;
		if(i <= 0) return false;
		
		int j = N - 1;
		while(cardTwo[i - 1] >= cardTwo[j]) j--;
		
		swap(i - 1, j, cardTwo);
		
		int k = N - 1;
		while(i < k) swap(i++, k--, cardTwo);
		
		return true;
	}

	private static int isWin(int[] cardOne, int[] cardTwo) {
		int oneScore = 0; int twoScore = 0;
		
		for(int i = 0; i < 9; i++) {
			if(cardOne[i] > cardTwo[i]) oneScore += (cardOne[i] + cardTwo[i]);
			else twoScore += (cardOne[i] + cardTwo[i]);
		}
		return Integer.compare(oneScore, twoScore);
	}

	private static int[] makeCardTwo(int[] cardOne) {
    	int[] cardTwo = new int[9];
    	boolean[] cardCheck = new boolean[19];
    	
    	for(int i = 0; i < 9; i++)
    		cardCheck[cardOne[i]] = true;
    	
    	int cnt = 0;
		for(int i = 1; i < 19; i++) 
			if(!cardCheck[i]) cardTwo[cnt++] = i;
    	
		return cardTwo;
	}

	private static String winAndLoseToString(int win, int lose) {
		StringBuilder sb = new StringBuilder();
		sb.append(win).append(" ").append(lose);
		return sb.toString();
	}	
	
	private static void swap(int i, int j, int[] card) {
		int temp = card[i];
		card[i] = card[j];
		card[j] = temp;
	}
}
