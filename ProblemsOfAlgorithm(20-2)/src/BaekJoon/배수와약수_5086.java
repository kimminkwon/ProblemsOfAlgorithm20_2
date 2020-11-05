package BaekJoon;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class 배수와약수_5086 {

    private static List<int[]> numList;
    private static List<String> result;

    public static void main(String[] args) {
        makeInput();
        caclulateRelationBetweenNumberList();
        printResult();
    }

    private static void caclulateRelationBetweenNumberList() {
        numList.forEach(
                ints -> result.add(caclulateRelationBetweenNumbers(ints[0], ints[1]))
        );
    }

    private static String caclulateRelationBetweenNumbers(int num1, int num2) {
        if(num2 % num1 == 0) // 첫번째 숫자가 두번째 숫자의 약수이다.
            return "factor";
        else if(num1 % num2 == 0) // 첫번째 숫자가 두번째 숫자의 배수이다.
            return "multiple";
        else
            return "neither";
    }

    private static void printResult() {
        result.forEach(
                s -> System.out.println(s)
        );
    }

    private static void makeInput() {
        initVariables();
        Scanner input = new Scanner(System.in);
        int[] nums = new int[2];
        while(true) {
            nums[0] = input.nextInt();
            nums[1] = input.nextInt();
            if(nums[0] == 0 && nums[1] == 0)
                break;
            else
                numList.add(nums.clone());
        }
    }

    private static void initVariables() {
        numList = new ArrayList<>();
        result = new ArrayList<>();
    }
}
