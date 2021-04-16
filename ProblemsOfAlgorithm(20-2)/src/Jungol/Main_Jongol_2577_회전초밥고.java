package Jungol;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main_Jongol_2577_회전초밥고 {

	private static int N, d, k, c;
	private static int[] sushi;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       	StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		sushi = new int[N];
		for(int i = 0; i < N; i++)
			sushi[i] = Integer.parseInt(br.readLine());

		System.out.println(findMaximumEat());
	}

	private static int findMaximumEat() {
    	int maxEat = 0, currEat = 0;
    	int[] isEat = new int[3001]; // 이 번호를 몇개 먹었는가?
    	// 0부터 k까지의 케이스 먼저 고려
		for(int i = 0; i < k; i++) {
			if(isEat[sushi[i]] == 0) currEat++; // 해당 초밥이 먹지 않은 경우일 때는 개수를 늘려줌
			isEat[sushi[i]]++;
		}
		maxEat = Math.max(maxEat, currEat);

		// 1부터의 케이스를 고려 ==> 부분합과 유사하게 고려한다.
    	for(int i = 1; i <= N - k; i++) {
			isEat[sushi[i - 1]]--; // 이전에 먹었던 마지막 것을 지운다.
			if(isEat[sushi[i - 1]]  == 0) currEat--; // 이전에 먹었던 걸 지웠더니 0이라면 중복되는게 없었다 ==> 개수가 줄어야한다.
			if(isEat[sushi[i + k - 1]] == 0) currEat++; // 이번에 먹을 것이 0이라면 새로 먹을 수 있는 케이스이다.
			isEat[sushi[i + k - 1]]++; // 새로 먹을 초밥의 개수를 증가한다.

			if(isEat[c] == 0) maxEat = Math.max(maxEat, currEat + 1); // 쿠폰으로 먹을 수 있다면 하나 더 추가해서 비교
			else maxEat = Math.max(maxEat, currEat);
		}

    	// N - k부터의 경우의 수를 고려
		for(int i = (N - k + 1); i < N; i++) {
			int startIndex = i, endIndex = (i + k - 1) % N;

			isEat[sushi[startIndex - 1]]--; // 이전에 먹었던 마지막 것을 지운다.
			if(isEat[sushi[startIndex - 1]]  == 0) currEat--; // 이전에 먹었던 걸 지웠더니 0이라면 중복되는게 없었다 ==> 개수가 줄어야한다.
			if(isEat[sushi[endIndex]] == 0) currEat++; // 이번에 먹을 것이 0이라면 새로 먹을 수 있는 케이스이다.
			isEat[sushi[endIndex]]++; // 새로 먹을 초밥의 개수를 증가한다.

			if(isEat[c] == 0) maxEat = Math.max(maxEat, currEat + 1); // 쿠폰으로 먹을 수 있다면 하나 더 추가해서 비교
			else maxEat = Math.max(maxEat, currEat);
		}
    	return maxEat;
	}
}
