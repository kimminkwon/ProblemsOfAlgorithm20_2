package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 택배_8980 {

    private static class Box {
        int start, end, numOfBox;

        public Box(int start, int end, int numOfBox) {
            this.start = start;
            this.end = end;
            this.numOfBox = numOfBox;
        }
    }

    private static int N, C, M;
    private static Box[] boxes;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(br.readLine());

        boxes = new Box[M];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            boxes[i] = new Box(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        System.out.println(findMaximumBox());
    }

    private static int findMaximumBox() {
        Arrays.sort(boxes, (o1, o2) -> o1.end != o2.end ? o1.end - o2.end : o1.start - o2.start);

        int result = 0;
        int[] homes = new int[N + 1];
        for(int i = 1; i <= N; i++) homes[i] = C;

        for(int i = 0; i < M; i++) {
            int num = boxes[i].numOfBox;
            for(int j = boxes[i].start; j < boxes[i].end; j++)
                num = Math.min(homes[j], num);
            for(int j = boxes[i].start; j < boxes[i].end; j++)
                homes[j] -= num;
            result += num;
        }

        return result;
    }
}
