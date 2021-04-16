package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 스도쿠_2239 {

    private static int numOfBlank;
    private static int[][] sdoku = new int[9][9];
    private static List<int[]> blanks = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i = 0; i < 9; i++) {
            String box = br.readLine();
            for(int j = 0; j < 9; j++) {
                sdoku[i][j] = Character.getNumericValue(box.charAt(j));
                if(sdoku[i][j] == 0) blanks.add(new int[]{i, j});
            }
        }
        numOfBlank = blanks.size();
        doSdoku(0);
    }

    private static void doSdoku(int index) {
        if (index == numOfBlank) {
            printMap();
            System.exit(0);
        }
        int x = blanks.get(index)[0], y = blanks.get(index)[1];
        for (int num = 1; num < 10; num++) {
            sdoku[x][y] = num;
            if (isSdoku(x, y)) doSdoku(index + 1);
            sdoku[x][y] = 0;
        }
    }

    private static boolean isSdoku(int x, int y) {
        // 1. 3 * 3 사각형 안에 겹치는 숫자가 없는가?
        int[] start = getStartIndexInSubSquare(x, y);
        for(int i = start[0]; i < start[0] + 3; i++) {
            for(int j = start[1]; j < start[1] + 3; j++) {
                if(i != x && j != y && sdoku[i][j] == sdoku[x][y]) return false;
            }
        }
        // 2. 가로 라인에 겹치는 숫자가 없는가?
        for(int i = 0; i < 9; i++) {
            if(i != y && sdoku[x][i] == sdoku[x][y]) return false;
        }

        // 3. 세로 라인에 겹치는 숫자가 없는가?
        for(int i = 0; i < 9; i++) {
            if(i != x && sdoku[i][y] == sdoku[x][y]) return false;
        }
        return true;
    }

    private static int[] getStartIndexInSubSquare(int x, int y) {
        int[] start = new int[2];

        if(x < 3) start[0] = 0;
        else if(x < 6) start[0] = 3;
        else start[0] = 6;

        if(y < 3) start[1] = 0;
        else if(y < 6) start[1] = 3;
        else start[1] = 6;
        return start;
    }

    private static void printMap() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 9; i++) {
            for(int j = 0; j < 9; j++)
                sb.append(sdoku[i][j]);
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
