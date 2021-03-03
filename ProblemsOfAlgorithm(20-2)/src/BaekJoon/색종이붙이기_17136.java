package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 색종이붙이기_17136 {

    private static class Coor {
        int x, y;
        public Coor(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" + x + ", " + y + ")";
        }
    }
    private static int[][] paper = new int[10][10];
    private static int[] remainPaper = {0, 5, 5, 5, 5, 5};

    private static int result = Integer.MAX_VALUE, numOfOne;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 10; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 10; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
                if(paper[i][j] == 1) numOfOne++;
            }
        }
        findMinimumPaperOverlap(0, 0, 0);
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void findMinimumPaperOverlap(int x, int y, int numofPaper) {
//        System.out.println();
//        System.out.println(x + ", " + y + " 에서 실행");
//        System.out.println("현재 PAPER=======");
//        printPaper();
        boolean isNoOne = true;
        loop: for(int i = 0; i < 10; i++)
            for(int j = 0; j < 10; j++) {
                if(paper[i][j] == 1) {
                    x = i; y = j;
                    isNoOne = false;
                    break loop;
                }
            }
//
//        System.out.println();
//        System.out.println(x + ", " + y + " 에서 STOP");
        if(isNoOne) {
//            System.out.println("더이상 1이 없다!");
            result = Math.min(numofPaper, result);
            return;
        }

        for(int i = 5; i > 0; i--) {
            if(isOut(x + i - 1, y + i - 1) || remainPaper[i] <= 0) continue;
            if(isAllOneCheck(x, y, i)) {
//                System.out.println(x + ", " + y + "에서 " + i + " 사이즈 종이를 붙인다!");
                pastePaper(x, y, i);
                remainPaper[i]--;
                findMinimumPaperOverlap(x, y, numofPaper + 1);
                removePaper(x, y, i);
                remainPaper[i]++;
            }
        }
    }

    private static void printPaper() {
        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {
                System.out.print(paper[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static boolean isOut(int x, int y) {
        return x >= 10 || y >= 10 || x < 0 || y < 0;
    }

    private static boolean isAllOneCheck(int x, int y, int length) {
        for(int i = x; i < x + length; i++)
            for(int j = y; j < y + length; j++)
                if(paper[i][j] == 0) return false;
        return true;
    }

    private static void pastePaper(int x, int y, int length) {
        for(int i = x; i < x + length; i++)
            for(int j = y; j < y + length; j++)
                paper[i][j] = 0;
    }

    private static void removePaper(int x, int y, int length) {
        for(int i = x; i < x + length; i++)
            for(int j = y; j < y + length; j++)
                paper[i][j] = 1;
    }
}
