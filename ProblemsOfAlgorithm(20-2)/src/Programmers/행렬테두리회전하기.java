package Programmers;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class 행렬테두리회전하기 {

    public static void main(String[] args) {
        int rows = 6;
        int columns = 6;
        int[][] queries = {{2, 2, 5, 4},{3, 3, 6, 6}, {5, 1, 6, 3}};

        int[][] matrix = new int[rows + 1][columns + 1];
        int cnt = 1;

        // 배열 초기화
        for(int i = 1; i <= rows; i++)
            for(int j = 1; j <= columns; j++)
                matrix[i][j] = cnt++;

        int[] answer = new int[queries.length];
        for(int i = 0; i < queries.length; i++) {
            int minValue = doCycle(rows, columns, matrix, queries[i]);
            answer[i] = minValue;
        }
        System.out.println(Arrays.toString(answer));
    }

    private static int doCycle(int rows, int columns, int[][] matrix, int[] query) {
        Deque<Integer> q = new ArrayDeque<>();
        int minValue = Integer.MAX_VALUE;
        // 1. 좌
        for(int i = query[1]; i < query[3]; i++) {
            minValue = Math.min(minValue, matrix[query[0]][i]);
            q.offer(matrix[query[0]][i]);
        }
        // 2. 하
        for(int i = query[0]; i < query[2]; i++) {
            minValue = Math.min(minValue, matrix[i][query[3]]);
            q.offer(matrix[i][query[3]]);
        }
        // 3. 우
        for(int i = query[3]; i > query[1]; i--) {
            minValue = Math.min(minValue, matrix[query[2]][i]);
            q.offer(matrix[query[2]][i]);
        }
        // 4. 상
        for(int i = query[2]; i > query[0]; i--) {
            minValue = Math.min(minValue, matrix[i][query[1]]);
            q.offer(matrix[i][query[1]]);
        }
        q.offerFirst(q.pollLast());
        // 1. 좌
        for(int i = query[1]; i < query[3]; i++) matrix[query[0]][i] = q.poll();
        // 2. 하
        for(int i = query[0]; i < query[2]; i++) matrix[i][query[3]] = q.poll();
        // 3. 우
        for(int i = query[3]; i > query[1]; i--) matrix[query[2]][i] = q.poll();
        // 4. 상
        for(int i = query[2]; i > query[0]; i--) matrix[i][query[1]] = q.poll();

        return minValue;
    }

    private static void print(int rows, int columns, int[][] matrix) {
        for(int i = 1; i <= rows; i++) {
            for(int j = 1; j <= columns; j++) {
                System.out.printf("%3d ", matrix[i][j]);
            }
            System.out.println();
        }
    }
}

