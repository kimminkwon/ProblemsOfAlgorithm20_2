package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_이항계수_D5_3238 {

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_이항계수.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			long N = Long.parseLong(st.nextToken());
			long R = Long.parseLong(st.nextToken());
			int P = Integer.parseInt(st.nextToken());
			sb.append("#").append(tc).append(" ").append(calculateCombinationUsingPNumber(N, R, P)).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

	private static long calculateCombinationUsingPNumber(long N, long R, int P) {
		long result = 1;
		long[] factorial = makeFactorial(P);
		while(N > 0 && R > 0) {
			int n_pNumber = (int) (N % P); // N을 P진수로 바꾸었을 때 i번째 계수를 얻어낸다.
			int r_pNumber = (int) (R % P); // R을 P진수로 바꾸었을 때 i번째 계수를 얻어낸다.

			if(n_pNumber < r_pNumber) return 0; // r이 더 커진다면 0을 리턴한다.
			long currResult = calculateCombination(n_pNumber, r_pNumber, P, factorial) % P; // P진수의 i번째 계수 값을 통해 조합을 계산한다.
			result = (result * currResult) % P; // 해당 값을 결과에 곱해준다.

			N = N / P; R = R / P;
		}
		return result % P;
	}

	private static long calculateCombination(int N, int R, int P, long[] factorial) {
		long Numerator = factorial[N] % P; // 분모
		long denominator = pow((factorial[R] * factorial[N - R]) % P, P - 2, P) % P;
		return (Numerator * denominator) % P;
	}

	private static long pow(long number, int pow, int P) {
		if(pow == 1) return number % P;
		long halfValue = pow(number, pow / 2, P) % P;
		long result = (halfValue * halfValue) % P;
		if(pow % 2 == 0) return result;
		else return (result * number) % P;
	}

	private static long[] makeFactorial(int P) {
		long[] factorial = new long[P + 1];
		factorial[0] = 1; factorial[1] = 1;
		for(int i = 2; i < P + 1; i++)
			factorial[i] = ((factorial[i - 1] % P) * i) % P;
		return factorial;
	}
}
