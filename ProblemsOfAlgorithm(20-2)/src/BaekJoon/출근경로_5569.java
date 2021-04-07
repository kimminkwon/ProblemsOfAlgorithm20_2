package BaekJoon;

import java.io.*;
import java.util.*;

public class 출근경로_5569 {

    private static int w, h;
    private static int[][][][] numOfPath;
    private static final int DIVIDE_NUM = 100000;

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        numOfPath = new int[w + 1][h + 1][2][2];

        makeNumOfPath();
        System.out.println(getPathResult());
    }

    private static int getPathResult() {
        int pathResult = 0;
        for(int i = 0; i < 2; i++)
            for(int j = 0; j < 2; j++)
                pathResult += numOfPath[w][h][i][j];
        return pathResult;
    }

    private static void makeNumOfPath() {
        for(int i = 2; i < w + 1; i++) numOfPath[i][1][0][0] = 1;
        for(int i = 2; i < h + 1; i++) numOfPath[1][i][1][0] = 1;

        for(int i = 2; i < w + 1; i++){
            for(int j = 2; j < h + 1; j++){
                numOfPath[i][j][0][0] = (numOfPath[i - 1][j][0][0] + numOfPath[i - 1][j][0][1]) % DIVIDE_NUM;
                numOfPath[i][j][0][1] = numOfPath[i - 1][j][1][0] % DIVIDE_NUM;
                numOfPath[i][j][1][0] = (numOfPath[i][j - 1][1][0] + numOfPath[i][j - 1][1][1]) % DIVIDE_NUM;
                numOfPath[i][j][1][1] = numOfPath[i][j - 1][0][0] % DIVIDE_NUM;
            }
        }
    }
}
