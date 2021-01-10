package Programmers;

import java.util.Arrays;

public class Level2_타겟넘버 {

    private static int result;

    public static void main(String[] args) {
        int[] numbers = {1, 1, 1, 1, 1};
        int target = 3;
        boolean[] isSelected = new boolean[numbers.length];
        result = 0;
        makeTargetValue(0, 0, isSelected, numbers, target);

        System.out.println(result);
    }

    private static void makeTargetValue(int value, int index, boolean[] isSelected, int[] numbers, int target) {

        if(index == numbers.length) {
            if(value == target) {
                result++;
            }
            return;
        }

        isSelected[index] = true;

        makeTargetValue(value + numbers[index], index + 1, isSelected.clone(), numbers, target);
        makeTargetValue(value - numbers[index], index + 1, isSelected.clone(), numbers, target);

        isSelected[index] = false;
    }

    private static boolean isTrue(boolean[] isSelected) {
        for(boolean b : isSelected) {
            if(b == false) return false;
        }
        return true;
    }
}
