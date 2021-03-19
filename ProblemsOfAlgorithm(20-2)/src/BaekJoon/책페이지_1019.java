package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 책페이지_1019 {

    private static long B;
    private static long[] result = new long[10];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long A = 1;
        B = Long.parseLong(br.readLine());

        // 0. 계산을 위한 기준 값 생성
        long mul = 1;

        while(A <= B) {
            // 1. A가 0으로 끝나고 B가 9로 끝날때까지 값을 조정한다.
            while(A % 10 != 0 && A <= B) {
                addResult(A, mul);
                A++;
            }
            if(A > B) break;
            while(B % 10 != 9 && A <= B) {
                addResult(B, mul);
                B--;
            }
            if(A > B) break;

            // 2. A가 0으로 끝나고 B가 9로 끝나므로 한번에 값을 알 수 있다.
            for(int i = 0; i < 10; i++)
                result[i] += (B / 10 - A / 10 + 1) * mul;

            A = A / 10;
            B = B / 10;
            mul = mul * 10;
        }
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 10; i++)
            sb.append(result[i]).append(" ");
        System.out.println(sb.toString());
    }

    private static void addResult(long num, long mul) {
        while(num > 0) {
            int flag = (int) (num % 10);
            result[flag] += mul;
            num = num / 10;
        }
    }
}
