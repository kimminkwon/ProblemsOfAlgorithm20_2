package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_성수의비밀번호공격_D4_6026 {

	private static final int MAX_COMB = 110;
	private static final long MOD = 1000000007L;

	private static long[][] pascalTriangle = new long[MAX_COMB][MAX_COMB];
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_성수의비밀번호공격.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		makePascalTriangle();
		for (int tc = 1; tc <= T; tc++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int M = Integer.parseInt(st.nextToken());
			int N = Integer.parseInt(st.nextToken());

			sb.append("#").append(tc).append(" ").append(calculateOntoFunction(M, N)).append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

	private static void makePascalTriangle() {
		pascalTriangle[0][0] = 1;
		for(int i = 1; i < MAX_COMB; i++) {
			pascalTriangle[i][0] = 1;
			pascalTriangle[i][1] = 1;
			pascalTriangle[i][i] = 1;
		}
		for(int i = 2; i < MAX_COMB; i++)
			for(int j = 1; j < MAX_COMB; j++)
				pascalTriangle[i][j] = (pascalTriangle[i - 1][j - 1] % MOD + pascalTriangle[i - 1][j] % MOD) % MOD;
	}

	private static long calculateOntoFunction(int M, int N) {
		// 즉, M개 원소를 갖는 정의역 + N개 원소를 갖는 공역에서 전사함수의 개수를 구하는 것
		// 구하고자 하는 값: sigma(k in 0~m): (-1)^k * mCk * (m - k)^n
		long result = 0L;
		for(int k = 0; k <= M; k++) {
			long currRes = pascalTriangle[M][k] % MOD; // mCk를 먼저 저장
			currRes = (currRes * (pow((M - k), N) % MOD)) % MOD; // mCk * (m - k)^n
			currRes = currRes * pow(-1L, k); // (-1)^k * mCk * (m - k)^n
			result = (result + currRes) % MOD; // 이를 result에 더해준다. 이 과정을 반복해 시그마를 구현하는 것
		}
		// 결과 반환시 MOD로 나눠져서 음수가 되는 경우가 존재한다. 이 경우는 MOD를 더해줘서 MOD 이하의 양수값으로 만들어 줄 필요가 있다. 근데 이런거 안알려줬잖아요 아저씨
		return result >= 0 ? result % MOD : result + MOD;
	}

	private static long pow(long number, int pow) {
		if(pow == 0) return 1;
		if(pow == 1) return number % MOD;
		long halfValue = pow(number, pow / 2) % MOD;
		long result = (halfValue * halfValue) % MOD;
		if(pow % 2 == 0) return result;
		else return (result * number) % MOD;
	}
}

