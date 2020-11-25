package BaekJoon;

import java.util.*;
import java.util.stream.IntStream;

public class 패션왕신해빈_9375 {

    private static int numOfTest;
    private static String[][][] wears;
    private static int[] results;

    public static void main(String[] args) {
        makeInput();
        findNumOfWearSets();
        printResult();
    }

    private static void printResult() {
        Arrays.stream(results).forEach(result -> System.out.println(result));
    }

    private static void findNumOfWearSets() {
        IntStream.range(0, numOfTest).forEach(i -> {
            results[i] = findNumOfWearSet(wears[i]);
        });
    }

    private static int findNumOfWearSet(String[][] wear) {
        /*
        옷1 = n1, 옷2 = n2, 옷3 = n3일 때,
        옷1을 입는 경우의 수는 1번옷, 2번옷, ..., n1번옷, 안입기까지 총 n1+1가지 경우의 수
        즉 n1+1 * n2+1 * n3+1이 모든 경우의 수이고, 여기에서 모든 옷을 입지않은 경우의 수 1을 빼줘야한다.
        */
        Map<String, Integer> wearMap = new HashMap<>();
        IntStream.range(0, wear.length).forEach(i -> {
           if(!wearMap.containsKey(wear[i][1])) {
               wearMap.put(wear[i][1], 1);
           } else {
               wearMap.put(wear[i][1], wearMap.get(wear[i][1]) + 1);
           }
        });
        int result = 1;
        for(Integer num : wearMap.values())
            result = result * (num + 1);

        return result - 1;
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        numOfTest = input.nextInt();
        wears = new String[numOfTest][][];
        results = new int[numOfTest];
        IntStream.range(0, numOfTest).forEach(i -> {
            wears[i] = new String[input.nextInt()][2];
            IntStream.range(0, wears[i].length).forEach(j -> {
                wears[i][j][0] = input.next();
                wears[i][j][1] = input.next();
            });
        });

        // test();
    }

    private static void test() {
        IntStream.range(0, numOfTest).forEach(i -> {
            System.out.println("TESTCASE: " + i);
            IntStream.range(0, wears[i].length).forEach(j -> {
                System.out.println(Arrays.toString(wears[i][j]));
            });
        });
    }
}
