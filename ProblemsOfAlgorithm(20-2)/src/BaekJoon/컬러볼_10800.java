package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 컬러볼_10800 {

    private static class Ball {
        int num, color, size;
        long res;

        public Ball(int num, int color, int size) {
            this.num = num;
            this.color = color;
            this.size = size;
        }

        @Override
        public String toString() {
            return "Ball{" +
                    "num=" + num +
                    ", color=" + color +
                    ", size=" + size +
                    ", res=" + res +
                    "]";
        }
    }
    private static int N;
    private static Ball[] balls;

    public static void main(String[] args)  throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        balls = new Ball[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            balls[i] = new Ball(i, color, size);
        }

        findEachBallSum();
        printResult();
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < N; i++) {
            sb.append(balls[i].res).append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void findEachBallSum() {
        Arrays.sort(balls, (o1, o2) -> o1.size == o2.size ? o1.color - o2.color : o1.size - o2.size);
        long[] colorSize = new long[N + 1];
        long[] sumSize = new long[N + 1];
        long[] sameSize = new long[2010];

        Deque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < N; i++) {
            colorSize[balls[i].color] += balls[i].size;
            sumSize[i] = i == 0 ? balls[i].size : sumSize[i - 1] + balls[i].size;
            sameSize[balls[i].size] += balls[i].size;
            balls[i].res = sumSize[i] - colorSize[balls[i].color] - sameSize[balls[i].size] + balls[i].size;
            if(i != 0 && balls[i].color == balls[i - 1].color && balls[i].size == balls[i - 1].size)
                balls[i].res = balls[i - 1].res;
        }
        Arrays.sort(balls, (o1, o2) -> o1.num - o2.num);
    }
}
