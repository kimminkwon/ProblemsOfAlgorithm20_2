package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 퍼펙트셔플_D3_3499 {
    // 상-하-좌-우
    private static int[] dOne = {-1, 1, 0, 0};
    private static int[] dTwo = {0, 0, -1, 1};
    
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_퍼펙트셔플.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			int N = Integer.parseInt(br.readLine());
			String[] cards = new String[N];
			StringTokenizer st = new StringTokenizer(br.readLine(), " ");
			for(int i = 0; i < N; i++) 
				cards[i] = st.nextToken();
			
			System.out.println("#" + tc + " " + doPerfectShuffle(N, cards));
		}
		br.close();
	}

	private static String doPerfectShuffle(int N, String[] cards) {
		Queue<String> shuffledCards = new LinkedList<>();
		int midIndex = N/2 - 1;
		for(int i = 0, j = N % 2 == 0 ? midIndex + 1 : midIndex + 2; i <= midIndex; i++, j++) {
			shuffledCards.offer(cards[i]);
			shuffledCards.offer(cards[j]);
		}
		if(N % 2 == 1) shuffledCards.offer(cards[midIndex + 1]);
		return shuffledCardsToString(N, shuffledCards);
	}

	private static String shuffledCardsToString(int N, Queue<String> shuffledCards) {
		StringBuilder sb = new StringBuilder();
		shuffledCards.stream().forEach(s -> sb.append(s + " "));
		return sb.toString();
	}
}
