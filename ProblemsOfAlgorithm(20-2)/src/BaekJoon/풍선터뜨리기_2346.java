package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

class Balloon {
    private int index;
    private int num;

    public Balloon(int index, int num) {
        this.index = index;
        this.num = num;
    }

    public int getIndex() { return index; }

    public int getNum() { return num; }
}

public class 풍선터뜨리기_2346 {
    private static int N;
    private static Deque<Balloon> balloons;

    public static void main(String[] args) throws IOException {
        makeInput();
        System.out.println(popTheBalloons());
    }

    private static String popTheBalloons() {
        StringBuilder sb = new StringBuilder();
        while(true) {
            Balloon currBalloon = balloons.pollFirst();
            sb.append(currBalloon.getIndex() + " ");
            if(balloons.isEmpty()) break;
            if(currBalloon.getNum() > 0) frontToBackBalloon(currBalloon.getNum() - 1);
            else backToFrontBalloon(currBalloon.getNum() * -1);
        }
        return sb.toString();
    }

    private static void frontToBackBalloon(int tryNum) {
        for(int i = 0; i < tryNum; i++) balloons.addLast(balloons.pollFirst());
    }

    private static void backToFrontBalloon(int tryNum) {
        for(int i = 0; i < tryNum; i++) balloons.addFirst(balloons.pollLast());
    }

    private static void makeInput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        balloons = new ArrayDeque<>();
        for(int i = 1; i <= N; i++) balloons.add(new Balloon(i, Integer.parseInt(st.nextToken())));
    }
}
