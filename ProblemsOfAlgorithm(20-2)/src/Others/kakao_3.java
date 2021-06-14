package Others;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class kakao_3 {

    private static final int INF = 2000000000;
    private static int[] visited;
    private static boolean[] isTrap;

    public static void main(String[] args) {
        int n = 4;
        int start = 1;
        int end = 4;
        int[][] roads = {{1, 2, 1},{3, 2, 1}, {2, 4, 1}};
        int[] traps = {1, 2};

        visited = new int[n];
        Arrays.fill(visited, -1);
        isTrap = new boolean[n];
        for(int trapNum : traps) isTrap[trapNum] = true;

        int[][] adjArr = new int[n][n];
        start = start - 1; end = end - 1; // 인덱스 번호 조절

        for(int[] road : roads)
            adjArr[road[0] - 1][road[1] - 1] = road[2];

        printArr(adjArr);

        printArr(adjArr);


        findMinimumPath(n, traps, start, adjArr);
    }

    private static void findMinimumPath(int n, int[] traps, int start, int[][] adjArr) {

    }

    private static void printArr(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
