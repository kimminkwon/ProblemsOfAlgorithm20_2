package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_조합_D3_5607 {

	private static final int REMAINDER = 1234567891;
	private static final int N_MAX = 1000010;
	private static long[] factorial = new long[N_MAX];

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_조합.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());
		makeFactorial();
		for(int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int N = Integer.parseInt(st.nextToken());
			int R = Integer.parseInt(st.nextToken());
			sb.append("#").append(tc).append(" ").append(calculateCombination(N, R)).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

	private static long calculateCombination(int N, int R) {
		long Numerator = factorial[N] % REMAINDER; // 분모
		long denominator = pow((factorial[R] * factorial[N - R]) % REMAINDER, REMAINDER - 2) % REMAINDER;

		return (Numerator * denominator) % REMAINDER;
	}

	private static long pow(long number, int pow) {
		if(pow == 1) return number % REMAINDER;
		long halfValue = pow(number, pow / 2) % REMAINDER;
		long result = (halfValue * halfValue) % REMAINDER;
		if(pow % 2 == 0) return result;
		else return (result * number) % REMAINDER;
	}

	private static void makeFactorial() {
		factorial[1] = 1;
		for(int i = 2; i < N_MAX; i++)
			factorial[i] = ((factorial[i - 1] % REMAINDER) * i) % REMAINDER;
	}
}
