package Programmers;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Truck {
    private int weight;
    private int remainTime;

    public Truck(int weight) {
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public int getRemainTime() {
        return remainTime;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setRemainTime(int remainTime) {
        this.remainTime = remainTime;
    }

    @Override
    public String toString() {
        return "(w=" + weight +
                ", t=" + remainTime +
                ')';
    }
}

public class Level2_다리를지나는트럭 {

    public static void main(String[] args) {
        // instance
        int bridge_length = 5;
        int weight = 5;
        int[] truck_weights= {2, 2, 2, 2, 1, 1, 1, 1, 1};

        // logic
        int time = calculatingTime(bridge_length, weight, truck_weights);
        System.out.println(time);

    }

    private static int calculatingTime(int bridge_length, int weight, int[] truck_weights) {
        Queue<Integer> remainQ = new LinkedList<>();
        Queue<Integer> bridgeQ = new LinkedList<>();
        Queue<Integer> goalQ = new LinkedList<>();

        Arrays.stream(truck_weights).forEach(i -> remainQ.add(i));

        int bridgeWeight = 0;
        int time = 0;
        while(goalQ.size() < truck_weights.length) {
            // 1. 다리가 비어있다.
            if(bridgeQ.isEmpty()) {
                bridgeWeight = bridgeWeight + remainQ.peek();
                bridgeQ.offer(remainQ.poll());
                time++;
            } else if(bridgeQ.size() == bridge_length) { // 2. 다리가 꽉 차있다.
                if(bridgeQ.peek() == -1) {
                    bridgeQ.poll();
                } else {
                    bridgeWeight = bridgeWeight - bridgeQ.peek();
                    goalQ.offer(bridgeQ.poll());
                }
            } else { // 3. 다리가 꽉 차지는 않았다.
                if(!remainQ.isEmpty() && bridgeWeight + remainQ.peek() <= weight) { // 3-1. 다음 트럭이 들어올 무게가 된다.
                    bridgeWeight = bridgeWeight + remainQ.peek();
                    bridgeQ.offer(remainQ.poll());
                    time++;
                } else { // 3-2. 다음 트럭이 들어올 무게가 안된다.
                    bridgeQ.offer(-1); // 더미데이터 삽입
                    time++;
                }
            }
        }

        return time + 1;
    }

}
