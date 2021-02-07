package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이만들기_2630 {

    private static int N, blue, white;
    private static int[][] paper;

    public static void print(int[][] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();

        }

    }
    public static void main(String[] args) throws IOException {
        makeInput();
        doSplitPaper(N, paper);
        System.out.println(white);
        System.out.println(blue);
    }

    private static void doSplitPaper(int n, int[][] subPaper) {
        print(subPaper);
        if(n == 2) {
            twoLengthPaper(subPaper);
            return;
        }
        if(isSameColor(subPaper)) return;

        doSplitPaper(n/2, splitPaper(subPaper, n/2, 0, 0, n/2, n/2));
        doSplitPaper(n/2, splitPaper(subPaper, n/2, n/2, 0, n/2, n));
        doSplitPaper(n/2, splitPaper(subPaper, n/2, 0, n/2, n, n/2));
        doSplitPaper(n/2, splitPaper(subPaper, n/2, n/2, n/2, n, n));
    }

    private static int[][] splitPaper(int[][] paper, int length, int x1, int y1, int x2, int y2) {
        int[][] subPaper = new int[length][length];
        for(int i = x1; i < x2; i++)
            for(int j = y1; j < y2; j++) {
                subPaper[i - x1][j - y1] = paper[i][j];
            }
        return subPaper;
    }

    private static boolean isSameColor(int[][] subPaper) {
        int b = 0;
        int w = 0;
        for(int i = 0; i < subPaper.length; i++)
            for(int j = 0; j < subPaper[i].length; j++) {
                if(subPaper[i][j] == 1) b++;
                else if(subPaper[i][j] == 0) w++;
            }
        if(b == 4) {
            blue++;
            return true;
        } else if(w == 4) {
            white++;
            return true;
        } else return false;
    }

    private static void twoLengthPaper(int[][] subPaper) {
        int b = 0;
        int w = 0;
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++) {
                if(subPaper[i][j] == 1) b++;
                else if(subPaper[i][j] == 0) w++;
            }
        if(b == 4) blue++;
        else if(w == 4) white++;
        else {
            blue += b;
            white += w;
        }
    }

    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        paper = new int[N][N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine(), " ");
            for(int j = 0; j < N; j++)
                paper[i][j] = Integer.parseInt(st.nextToken());
        }
        blue = 0; white = 0;
    }
}
