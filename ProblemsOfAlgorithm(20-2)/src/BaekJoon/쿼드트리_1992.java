package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class 쿼드트리_1992 {

    private static int N;
    private static int[][] movie;
    private static String zip = "";


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
        doSplitPaper(N, movie);
        System.out.println(zip);
    }

    private static void doSplitPaper(int n, int[][] movie) {
        int colorNum = isSameColor(n, movie);

        if(n == 2) {
            String colorStr = twoLengthPaper(movie);
            zip += colorStr;
            return;
        }
        if(colorNum != -1) {
            zip += colorNum;
            return;
        }

        zip += "(";
        doSplitPaper(n/2, splitPaper(movie, n/2, 0, 0, n/2, n/2));
        doSplitPaper(n/2, splitPaper(movie, n/2, 0, n/2, n/2, n));
        doSplitPaper(n/2, splitPaper(movie, n/2, n/2, 0, n, n/2));
        doSplitPaper(n/2, splitPaper(movie, n/2, n/2, n/2, n, n));
        zip += ")";
    }

    private static int[][] splitPaper(int[][] paper, int length, int x1, int y1, int x2, int y2) {
        int[][] subPaper = new int[length][length];
        for(int i = x1; i < x2; i++)
            for(int j = y1; j < y2; j++) {
                subPaper[i - x1][j - y1] = paper[i][j];
            }
        return subPaper;
    }

    private static String twoLengthPaper(int[][] subPaper) {
        String box = "";
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++) {
                if(subPaper[i][j] == 1) box += "1";
                else if(subPaper[i][j] == 0) box += "0";
            }
        if(box.equals("1111")) return "1";
        else if(box.equals("0000")) return "0";
        else return "(" + box + ")";
    }

    private static int isSameColor(int n, int[][] subMovie) {
        int b = 0; int w = 0;
        for(int i = 0; i < subMovie.length; i++)
            for(int j = 0; j < subMovie[i].length; j++) {
                if(subMovie[i][j] == 1) b++;
                else if(subMovie[i][j] == 0) w++;
            }
        if(b == Math.pow(n, 2)) {
            return 1;
        } else if(w == Math.pow(n, 2)) {
            return 0;
        } else return -1;
    }

    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        movie = new int[N][N];
        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            for(int j = 0; j < N; j++)
                movie[i][j] = Character.getNumericValue(str.charAt(j));
        }
    }
}
