package BaekJoon;

import java.util.*;
import java.io.*;

public class 종이의개수_1780 {

    private static int N, numOfMinus, numOfZero, numOfOne;
    private static int[][] paper;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < N; j++)
                paper[i][j] = Integer.parseInt(st.nextToken());
        }

        splitPaper(0, 0, N);

        System.out.println(numOfMinus);
        System.out.println(numOfZero);
        System.out.println(numOfOne);
    }

    private static void splitPaper(int x, int y, int n) {
        if(n == 1 || isSameNum(x, y, n)) {
            switch (paper[x][y]) {
                case -1: numOfMinus++; break;
                case 0: numOfZero++; break;
                case 1: numOfOne++; break;
            }
            return;
        }
        int splitLength = n / 3;
        splitPaper(x, y, splitLength);
        splitPaper(x, y + splitLength, splitLength);
        splitPaper(x, y + (splitLength * 2), splitLength);

        splitPaper(x + splitLength, y, splitLength);
        splitPaper(x + splitLength, y + splitLength, splitLength);
        splitPaper(x + splitLength, y + (splitLength * 2), splitLength);

        splitPaper(x + (splitLength * 2), y, splitLength);
        splitPaper(x + (splitLength * 2), y + splitLength, splitLength);
        splitPaper(x + (splitLength * 2), y + (splitLength * 2), splitLength);
    }

    private static void printMap(int x, int y, int n) {
        System.out.println("(" + x + ", " + y + ")에서 길이가 " + n);
        for(int i = x; i < n + x; i++) {
            for(int j = y; j < n + y; j++)
                System.out.print(paper[i][j] + " ");
            System.out.println();
        }
        System.out.println();
    }

    private static boolean isSameNum(int x, int y, int n) {
        int flag = paper[x][y];
        for(int i = x; i < n + x; i++)
            for(int j = y; j < n + y; j++)
                if(paper[i][j] != flag) return false;
        return true;
    }
}
