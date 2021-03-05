package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 보석도둑_1202 {

    private static class Diamond {
        int m, v;
        public Diamond(int m, int v) {
            this.m = m;
            this.v = v;
        }
    }
    private static int N, K;
    private static long result;
    private static List<Integer> bag = new ArrayList<>();
    private static Diamond[] diamonds;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        diamonds = new Diamond[N];
        K = Integer.parseInt(st.nextToken());

        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            diamonds[i] = new Diamond(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }
        for(int i = 0; i < K; i++)
            bag.add(Integer.parseInt(br.readLine()));

        findMaximumDiamondPrice();
        System.out.println(result);
    }

    private static void findMaximumDiamondPrice() {
        Arrays.sort(diamonds, (o1, o2) -> o1.m - o2.m);
        Collections.sort(bag);
        PriorityQueue<Diamond> pq = new PriorityQueue<>((o1, o2) -> o2.v - o1.v);

        int cnt = 0;
        for(int i = 0; i < K; i++) {
            while(cnt < N && bag.get(i) >= diamonds[cnt].m) pq.offer(diamonds[cnt++]);
            if(!pq.isEmpty()) result += pq.poll().v;
        }
    }
}
