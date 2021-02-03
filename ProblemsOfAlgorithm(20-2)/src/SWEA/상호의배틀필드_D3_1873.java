package SWEA;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 상호의배틀필드_D3_1873 {

    // 상-하-좌-우
    private static int[] dOne = {-1, 1, 0, 0};
    private static int[] dTwo = {0, 0, -1, 1};
    private static char[] tankShape = {'^', 'v', '<', '>'};
    private static int tankOne, tankTwo, tankState;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int H = Integer.parseInt(st.nextToken());
            int W = Integer.parseInt(st.nextToken());
            char[][] map = new char[H][W];
            for(int i = 0; i < H; i++)
                map[i] = br.readLine().toCharArray();
            int N = Integer.parseInt(br.readLine());
            char[] userInput = br.readLine().toCharArray();

            System.out.println("#" + tc + " " + doBattleField(H, W, map, N, userInput));
        }
    }

    private static String doBattleField(int H, int W, char[][] map, int N, char[] userInput) {
        findTank(H, W, map);

        for(int i = 0; i < N; i++) {
            switch (userInput[i]) {
                case 'U':
                    doMove(H, W, map, 0);
                    break;
                case 'D':
                    doMove(H, W, map, 1);
                    break;
                case 'L':
                    doMove(H, W, map, 2);
                    break;
                case 'R':
                    doMove(H, W, map, 3);
                    break;
                case 'S':
                    doShoot(H, W, map);
                    break;
            }
        }

        return mapToString(H, W, map);
    }

    private static void doShoot(int H, int W, char[][] map) {
        int slugOne = tankOne + dOne[tankState];
        int slugTwo = tankTwo + dTwo[tankState];

        while(!isDistory(H, W, map, slugOne, slugTwo)) {
            slugOne = slugOne + dOne[tankState];
            slugTwo = slugTwo + dTwo[tankState];
        }
    }

    private static void doMove(int H, int W, char[][] map, int dir) {
        int nextOne = tankOne + dOne[dir]; int nextTwo = tankTwo + dTwo[dir];
        tankState = dir;
        map[tankOne][tankTwo] = tankShape[tankState];

        if(isStop(H, W, map, nextOne, nextTwo)) return;

        map[nextOne][nextTwo] = tankShape[dir];
        map[tankOne][tankTwo] = '.';
        tankOne = nextOne; tankTwo = nextTwo;
    }

    private static boolean isDistory(int H, int W, char[][] map, int slugOne, int slugTwo) {
        if(slugOne >= H || slugOne < 0 || slugTwo >= W || slugTwo < 0 || map[slugOne][slugTwo] == '#') return true;
        else if(map[slugOne][slugTwo] == '*') {
            map[slugOne][slugTwo] = '.';
            return true;
        }

        return false;
    }

    private static boolean isStop(int H, int W, char[][] map, int one, int two) {
        if(one >= H || one < 0 || two >= W || two < 0) return true;
        else if(map[one][two] != '.') return true;

        return false;
    }

    private static void findTank(int H, int W, char[][] map) {
        // 탱크의 위치 탐색!
        for(int i = 0; i < H; i++)
            for(int j = 0; j < W; j++)
                switch (map[i][j]) {
                    case '^':
                        tankOne = i; tankTwo = j;
                        tankState = 0;
                        break;
                    case 'v':
                        tankOne = i; tankTwo = j;
                        tankState = 1;
                        break;
                    case '<':
                        tankOne = i; tankTwo = j;
                        tankState = 2;
                        break;
                    case '>':
                        tankOne = i; tankTwo = j;
                        tankState = 3;
                        break;
                }
    }

    private static String mapToString(int H, int W, char[][] map) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < H; i++){
            for(int j = 0; j < W; j++) {
                sb.append(map[i][j]);
            }
            if(i != H - 1) sb.append("\n");
        }
        return sb.toString();
    }
}
