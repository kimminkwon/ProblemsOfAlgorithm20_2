package Programmers;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Level2_구명보트 {
    public static void main(String[] args) {
        int[] people = {40, 40, 40, 40, 40, 40, 80};
        int limit = 240;

        int answer = findMinimumBoats(people, limit);
        System.out.println(answer);
    }

    private static int findMinimumBoats(int[] people, int limit) {
        // 1. 사람들을 낮은 무게 순으로 정렬한다.
        Arrays.sort(people);

        // 2. 낮은 사람 + 큰 사람순으로 태우면서 최대 보트 값을 찾는다.
        int start = 0;
        int end = people.length - 1;
        int boatCnt = 0;

        while(start <= end) {
            if(people[start] + people[end] > limit) {
                end--;
            } else {
                start++;
                end--;
            }
            boatCnt++;
        }

        return boatCnt;
    }
}
