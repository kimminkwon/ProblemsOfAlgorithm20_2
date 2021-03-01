package BaekJoon;

import java.util.*;
import java.io.*;

public class 곱셈_1629 {

    private static long A, B, C;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Long.parseLong(st.nextToken());
        B = Long.parseLong(st.nextToken());
        C = Long.parseLong(st.nextToken());

        long result = calculatePow(A, B, C);
        System.out.println(result);
    }

    private static long calculatePow(long a, long b, long c) {
        if(b == 1) return a % c;
        long halfValue = calculatePow(a, b / 2, c);
        return b % 2 == 0 ? ((halfValue % c * halfValue % c) % c) : ((halfValue % c * halfValue % c) * (a % c)) % c;
    }
}
