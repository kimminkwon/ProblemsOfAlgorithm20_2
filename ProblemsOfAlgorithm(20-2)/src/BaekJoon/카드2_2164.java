package BaekJoon;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class 카드2_2164 {

    public static void main(String[] args) {
        int n = new Scanner(System.in).nextInt();
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 1; i <= n; i++)
            queue.offer(i);

        while(queue.size() > 1) {
            queue.poll();
            queue.offer(queue.poll());
        }

        System.out.println(queue.poll());
    }
}
