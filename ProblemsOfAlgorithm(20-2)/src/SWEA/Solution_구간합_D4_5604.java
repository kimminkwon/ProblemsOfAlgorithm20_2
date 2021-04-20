package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution_구간합_D4_5604 {

	private static Map<Long, Long> hm = new HashMap<>();
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_구간합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		setDefaultValues();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long A = Long.parseLong(st.nextToken());
			long B = Long.parseLong(st.nextToken());
			sb.append("#").append(tc).append(" ").append(calculatePrefixSum(A, B)).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}
	// 가장 먼저 호출되는 메소드
	private static long calculatePrefixSum(long A, long B) {
		// F(B) - F(A - 1)를 통해 답을 구할 수 있다. (A도 포함하기 때문)
		return F(B) - F(A == 0 ? 0 : A - 1); // 예외 케이스: 단 A가 0일 경우는 F(B) 자체가 구해져야한다.
	}
	// F(n): Part 1 + Part 2 + Part 3
	// Part 1: F(N의 바로 아래층의 9단위 수) ex: 28 -> 19 / 280 -> 199
	// Part 2: G(n): 현재 수에서 가장 큰 자리수의 합 ex: 25라면 21, 22, 23, 24, 25의 10의 자리수 2 + 2 + 2 + 2 + 2를 반환
	// Part 3: "한 단계 낮은 자리수로 함수를 재귀 호출" ex: 25라면 10의 자리수는 Part2에서 처리했으므로 1 + 2 + 3 + 4 + 5 = F(5)를 호출
	private static long F(long n) {
		if(hm.containsKey(n)) return hm.get(n);
		else {
			long unitNum = getUnitNumber(n);
			long currRes = F(n - 1 - n % unitNum) + G(n, unitNum) + F(n % unitNum);
			hm.put(n, currRes);
			return hm.get(n);
		}
	}
	// G(n): "현재 수에서 가장 큰 자리수의 합"을 반환하는 메소드
	// n / unitNum = 3630이면 3, 212면 2, 즉 가장 큰 자릿수 값을 가져와서 기준 값부터 현재값(212면 199~212) 사이의 큰 자릿수 값(2)을 더해주기 위함
	// 위의 수는 그래서 기준값에서 현재값 사이 몇번 나오는가? (n % unitNum + 1)번 나온다. (212면 13번)
	private static long G(long n, long unitNum) {
		if(n < 10) return hm.get(n);
		else return (n / unitNum) * (n % unitNum + 1L);
	}
	// 6234면 1000을, 39면 10을(해당 자릿수의 10의자리수를) 반환해주는 메소드
	private static long getUnitNumber(long number) {
		return (long) Math.pow(10, String.valueOf(number).length() - 1);
	}
	// 기본 값을 저장하는 메소드
	private static void setDefaultValues() {
		// 0 ~ 9 까지의 값을 저장
		hm.put(0L, 0L); hm.put(1L, 1L); hm.put(2L, 3L); hm.put(3L, 6L); hm.put(4L, 10L); hm.put(5L, 15L);
		hm.put(6L, 21L); hm.put(7L, 28L); hm.put(8L, 36L); hm.put(9L, 45L);
		// 99부터의 최대 자릿수까지의 값을 저장
		// 1. String으로 9에 9를 계속 append하며 key 생성
		// 2. i in 2 ~ 17 ==> value: (i * 10의 (i-1)승 * F(9)) ex: F(999) = 3 * 10^2 * F(9), F(9999) = 4 * 10^3 * F(9)
		String flagNum = "9"; long fNine = 45L;
		for(int i = 2; i < 18; i++) {
			flagNum += "9";
			hm.put(Long.parseLong(flagNum), (long) i * (long) Math.pow(10, i - 1) * fNine);
		}
	}
}

