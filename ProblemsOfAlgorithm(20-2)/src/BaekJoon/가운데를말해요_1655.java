package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class 가운데를말해요_1655 {

    private static int N;
    private static int[] speakNum;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        speakNum = new int[N];
        for(int i = 0; i < N; i++)
            speakNum[i] = Integer.parseInt(br.readLine());

        speakMiddleNumber();
        System.out.print(sb.toString());
    }

    private static void speakMiddleNumber() {
        PriorityQueue<Integer> minQ = new PriorityQueue<>(((o1, o2) -> o2 - o1));
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(((o1, o2) -> o1 - o2));
        int minQSize = 0, maxQSize = 0, midNum = speakNum[0];
        sb.append(midNum).append("\n");

        for(int i = 1; i < N; i++) {
            if(midNum < speakNum[i]) {
                maxQ.offer(speakNum[i]);
                maxQSize++;
            } else {
                minQ.offer(speakNum[i]);
                minQSize++;
            }
            if(minQSize < maxQSize - 1) {
                minQ.offer(midNum);
                minQSize++;
                midNum = maxQ.poll();
                maxQSize--;
            } else if(minQSize > maxQSize) {
                maxQ.offer(midNum);
                maxQSize++;
                midNum = minQ.poll();
                minQSize--;
            }
            System.out.println("현재 MID: " + midNum);
            System.out.println("현재 MinQ: " + minQ);
            System.out.println("현재 MaxQ: " + maxQ);
            sb.append(midNum).append("\n");
        }
    }
}
