package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Solution_규영이와인영이의카드게임_D3_6808 {
	
	private static List<int[]> caseOfCard;
	
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
		caseOfCard = new ArrayList<>(); 
		findCaseOfCard(cardTwo, new boolean[9], 0, new int[9]);
		
		int win = 0; int lose = 0;
		for(int i = 0; i < caseOfCard.size(); i++) {
			int res = isWin(cardOne, caseOfCard.get(i));
			if(res > 0) win++;
			else if(res < 0) lose++;
		}
		
		return winAndLoseToString(win, lose);
	}
	
	private static void findCaseOfCard(int[] card, boolean[] selected, int cnt, int[] makedCard) {
		if(cnt == 9) {
			caseOfCard.add(makedCard.clone());
			return;
		} 
		for(int i = 0; i < 9; i++) {
			if(!selected[i]) {
				selected[i] = true;
				makedCard[cnt] = card[i];
				findCaseOfCard(card, selected, cnt + 1, makedCard);
				selected[i] = false;
			}
		}
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
}
