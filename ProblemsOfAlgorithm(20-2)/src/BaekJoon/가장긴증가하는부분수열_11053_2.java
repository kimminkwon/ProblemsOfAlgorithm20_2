package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 가장긴증가하는부분수열_11053_2 {

	private static int N;
	private static int[] numArr;
	private static int result = 1;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		numArr = new int[N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < N; i++)
			numArr[i] = Integer.parseInt(st.nextToken());

		makeLIS();
		System.out.println(result);
	}

	private static void makeLIS() {
		int[] numArrDp = new int[N];
		Arrays.fill(numArrDp, 1);
		numArrDp[0] = 1;

		for(int i = 1; i < N; i++)
			for(int j = 0; j < i; j++)
				if(numArr[i] > numArr[j] && numArrDp[i] < numArrDp[j] + 1) {
					numArrDp[i] = numArrDp[j] + 1;
					result = Math.max(result, numArrDp[i]);
				}
	}
}
