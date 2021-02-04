package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class 늑대와양_16956 {
    private static int R, C;
    private static char[][] farm;
    private static int isBlock = 1;
    private static int[] dOne = {0, 1, 0, -1};
    private static int[] dTwo = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        makeInput();
        makeFence2();
        System.out.println(isBlock);
        StringBuilder sb = new StringBuilder("");
        if(isBlock == 1) {
            for(int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    sb.append(farm[i][j]);
                }
                sb.append("\n");
            }
            System.out.println(sb.toString());
        }
    }
    private static void makeFence2() {
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(farm[i][j] == 'S') {
                    for(int d = 0; d < 4; d++) {
                        int nextOne = i + dOne[d];
                        int nextTwo = j + dTwo[d];
                        if(!isOut(nextOne, nextTwo) && farm[nextOne][nextTwo] == 'W') {
                            isBlock = 0;
                            break;
                        }
                    }
                } else if(farm[i][j] == 'W') {
                    for(int d = 0; d < 4; d++) {
                        int nextOne = i + dOne[d];
                        int nextTwo = j + dTwo[d];
                        if(!isOut(nextOne, nextTwo) && farm[nextOne][nextTwo] == '.') {
                            farm[nextOne][nextTwo] = 'D';
                        }
                    }
                }
            }
        }
    }

    private static boolean isOut(int one, int two) {
        return one >= R || one < 0 || two >= C || two < 0 ? true : false;
    }

    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        farm = new char[R][C];
        for(int i = 0; i < R; i++)
            farm[i] = br.readLine().toCharArray();
    }
}

