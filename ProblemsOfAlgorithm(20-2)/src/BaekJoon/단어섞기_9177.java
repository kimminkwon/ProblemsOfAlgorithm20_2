package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 단어섞기_9177 {

    private static String res;
    private static int[][] visit;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            String s1 = st.nextToken(), s2 = st.nextToken(), s3 = st.nextToken();
            res = "no";
            isMakeStringThree(s1, s2, s3);
            sb.append("Data set ").append(tc).append(": ").append(res).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void isMakeStringThree(String s1, String s2, String s3) {
        int length1 = s1.length(), length2 = s2.length(), length3 = s3.length();
        visit = new int[length1 + 1][length2 + 1];

        isPossible(length1, length2, length3, 0, 0, 0, s1, s2, s3);
    }

    private static void isPossible(int length1, int length2, int length3, int index1, int index2, int index3, String s1, String s2, String s3) {
        if(index3 == length3) {
            res = "yes";
            return;
        }
        if(visit[index1][index2] != 0) return;
        visit[index1][index2] = 1;
        if(index1 < length1 && s1.charAt(index1) == s3.charAt(index3))
            isPossible(length1, length2, length3, index1 + 1, index2, index3 + 1, s1, s2, s3);

        if(index2 < length2 && s2.charAt(index2) == s3.charAt(index3))
            isPossible(length1, length2, length3, index1, index2 + 1, index3 + 1, s1, s2, s3);
    }
}
