package BaekJoon;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class 주유소_13305 {
    private static int numOfCity;
    private static long[] dist;
    private static long[] cost;
    private static long result;

    public static void main(String[] args) {
        makeInput();
        findMinimumCost();
        printResult();
    }

    private static void printResult() {
        System.out.println(result);
    }

    private static void findMinimumCost() {
        List<Long> costs = new ArrayList<>();

        // 1번째는 무조건 사용해야한다.
        long minCost = cost[0];
        long currDist = dist[0];
        costs.add(minCost * currDist);

        for(int i = 1; i < cost.length - 1; i++) {
            if(minCost > cost[i])
                minCost = cost[i];
            costs.add(minCost * dist[i]);
        }

        result = costs.stream().reduce(0L, Long::sum);
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        numOfCity = input.nextInt();
        dist = new long[numOfCity - 1];
        cost = new long[numOfCity];

        for(int i = 0; i < dist.length; i++)
            dist[i] = input.nextInt();

        for(int i = 0; i < cost.length; i++)
            cost[i] = input.nextInt();
    }
}
