package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Coor {
    private int one;
    private int two;

    public Coor(int one, int two) {
        this.one = one;
        this.two = two;
    }

    public int getOne() {
        return one;
    }

    public int getTwo() {
        return two;
    }

    @Override
    public String toString() {
        return "(" + one +
                ", " + two +
                ')';
    }
}

public class 뱀_3190 {

    // 우-하-좌-상
    private static int[] dOne = {0, 1, 0, -1};
    private static int[] dTwo = {1, 0, -1, 0};

    private static int N, numOfApple, L;
    private static boolean[][] board;
    private static int[][] moveInfo;
    private static int time = 0;

    public static void main(String[] args) throws IOException {
        makeInput();
        doMoveOfSnake();
        System.out.println(time);
    }

    private static void doMoveOfSnake() {
        int dir = 0; // 초기 방향은 오른쪽
        LinkedList<Coor> snakeState = new LinkedList<>();
        int currOne = 0; int currTwo = 0; boolean isEnd = false;
        snakeState.add(new Coor(0, 0));
        for(int i = 0; i < L + 1; i++) {
            while(time < moveInfo[i][0]) {
                time++;
                int nextOne = currOne + dOne[dir]; int nextTwo = currTwo + dTwo[dir];
                if(isOut(nextOne, nextTwo, snakeState)) {
                    isEnd = true;
                    break;
                }
                snakeState.add(new Coor(nextOne, nextTwo));

                if(board[nextOne][nextTwo] == false) snakeState.removeFirst();
                else board[nextOne][nextTwo] = false;

                currOne = nextOne; currTwo = nextTwo;
            }
            if(isEnd) break;
            dir = setDirection(dir, moveInfo[i][1]);
        }
    }

    private static int setDirection(int dir, int flag) {
        int nextDir = (dir + flag) % 4;
        if(nextDir == -1) nextDir = 3;

        return nextDir;
    }

    private static boolean isOut(int one, int two, LinkedList<Coor> snakeState) {
        if(one >= N || one < 0 || two >= N || two < 0) return true;

        for(int i = 0; i < snakeState.size(); i++)
            if(snakeState.get(i).getOne() == one && snakeState.get(i).getTwo() == two) return true;

        return false;
    }


    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new boolean[N][N];
        numOfApple = Integer.parseInt(br.readLine());
        StringTokenizer st;
        for(int i = 0; i < numOfApple; i++) {
            st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = true;
        }
        L = Integer.parseInt(br.readLine());
        moveInfo = new int[L + 1][2];
        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            moveInfo[i][0] = Integer.parseInt(st.nextToken());
            moveInfo[i][1] = st.nextToken().equals("L") ? -1 : 1;
        }
        moveInfo[L][0] = Integer.MAX_VALUE;
    }
}
