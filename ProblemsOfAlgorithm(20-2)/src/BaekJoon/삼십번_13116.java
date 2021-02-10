package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class 삼십번_13116 {

    private static int[] tree;

    public static void main(String[] args)  throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        tree = new int[1024];
        IntStream.range(1, 1024).forEach(i -> tree[i] = i);

        for(int tc = 0; tc < T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            sb.append(findMaximumSameNode(A, B) * 10).append("\n");
        }

        System.out.print(sb.toString());
    }

    private static int findMaximumSameNode(int A, int B) {
        boolean[] parentListOfA = getParentList(A);
        boolean[] parentListOfB = getParentList(B);
        int maxIndex = 1;
        for(int i = 1; i < 1024; i++)
            if(parentListOfA[i] && parentListOfB[i]) maxIndex = i;

        return maxIndex;
    }

    private static boolean[] getParentList(int num) {
        boolean[] parentList = new boolean[1024];
        int n = num;
        while(true) {
            parentList[n] = true;
            n = n / 2;
            if(n <= 0) break;
        }
        return parentList;
    }
}
