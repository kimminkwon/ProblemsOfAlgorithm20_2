package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_PokerGame_D4_9760 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_PokerGame.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String[] cards = new String[5];
			for(int i = 0; i < 5; i++)
				cards[i] = st.nextToken();

			sb.append("#").append(tc).append(" ").append(getCardHand(cards)).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

	private static String getCardHand(String[] cards) {
		char[] cardShot = new char[5];
		int[] cardNum = new int[5];

		for(int i = 0; i < 5; i++) {
			cardShot[i] = cards[i].charAt(0);
			cardNum[i] = getCardNum(cards[i].charAt(1));
		}
		if(isStraightFlush(cardShot, cardNum)) return "Straight Flush";
		else if(isFourofaKind(cardShot, cardNum)) return "Four of a Kind";
		else if(isFullHouse(cardShot, cardNum)) return "Full House";
		else if(isFlush(cardShot, cardNum)) return "Flush";
		else if(isStraight(cardShot, cardNum)) return "Straight";
		else if(isThreeofakind(cardShot, cardNum)) return "Three of a kind";
		else if(isTwopair(cardShot, cardNum)) return "Two pair";
		else if(isOnepair(cardShot, cardNum)) return "One pair";
		else return "High card";
	}

	private static boolean isOnepair(char[] cardShot, int[] cardNum) {
		for(int rank = 1; rank <= 13; rank++) {
			int cnt = 0;
			for(int i = 0; i < 5; i++)
				if(cardNum[i] == rank) cnt++;
			if(cnt >= 2) return true;
		}
		return false;
	}

	private static boolean isTwopair(char[] cardShot, int[] cardNum) {
		for(int rank1 = 1; rank1 <= 13; rank1++) {
			for (int rank2 = rank1 + 1; rank2 <= 13; rank2++) {
				int cnt1 = 0, cnt2 = 0;
				for(int i = 0; i < 5; i++) {
					if(cardNum[i] == rank1) cnt1++;
					if(cardNum[i] == rank2) cnt2++;
				}
				if(cnt1 == 2 && cnt2 == 2) return true;
			}
		}
		return false;
	}

	private static boolean isThreeofakind(char[] cardShot, int[] cardNum) {
		for(int rank = 1; rank <= 13; rank++) {
			int cnt = 0;
			for(int i = 0; i < 5; i++)
				if(cardNum[i] == rank) cnt++;
			if(cnt >= 3) return true;
		}
		return false;
	}

	private static boolean isStraight(char[] cardShot, int[] cardNum) {
		Arrays.sort(cardNum);
		for(int i = 1; i < 5; i++)
			if(cardNum[i] - cardNum[i - 1] != 1) return false;
		return true;
	}

	private static boolean isFlush(char[] cardShot, int[] cardNum) {
		for(int i = 1; i < 5; i++)
			if(cardShot[i] != cardShot[i - 1]) return false;
		return true;
	}

	private static boolean isFullHouse(char[] cardShot, int[] cardNum) {
		for(int rank1 = 1; rank1 <= 13; rank1++) {
			for (int rank2 = rank1 + 1; rank2 <= 13; rank2++) {
				int cnt1 = 0, cnt2 = 0;
				for(int i = 0; i < 5; i++) {
					if(cardNum[i] == rank1) cnt1++;
					if(cardNum[i] == rank2) cnt2++;
				}
				if(cnt1 == 3 && cnt2 == 2) return true;
				if(cnt1 == 2 && cnt2 == 3) return true;
			}
		}
		return false;
	}

	private static boolean isFourofaKind(char[] cardShot, int[] cardNum) {
		for(int rank = 1; rank <= 13; rank++) {
			int cnt = 0;
			for(int i = 0; i < 5; i++)
				if(cardNum[i] == rank) cnt++;
			if(cnt >= 4) return true;
		}
		return false;
	}

	private static boolean isStraightFlush(char[] cardShot, int[] cardNum) {
		for(int i = 1; i < 5; i++)
			if(cardShot[i] != cardShot[i - 1]) return false;
		Arrays.sort(cardNum);
		for(int i = 1; i < 5; i++)
			if(cardNum[i] - cardNum[i - 1] != 1) return false;
		return true;
	}

	private static int getCardNum(char charNum) {
		switch (charNum) {
			case 'A': return 1;
			case 'T': return 10;
			case 'J': return 11;
			case 'Q': return 12;
			case 'K': return 13;
			default: return Character.getNumericValue(charNum);
		}
	}

}

