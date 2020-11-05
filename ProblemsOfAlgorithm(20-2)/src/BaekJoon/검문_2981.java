package BaekJoon;

import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

public class 검문_2981 {

    private static int n;
    private static List<Long> numbers, result;

    public static void main(String[] args) {
        makeInput();
        findM();
        printResult();
    }

    private static void printResult() {
        Collections.sort(result);
        result.stream().distinct().filter(aLong -> aLong != 1).forEach(
                aLong -> System.out.println(aLong)
        );
    }

    private static void findM() {
        Long m = numbers.get(0) - numbers.get(1);
        for (int i = 2; i < n; i++)
            m = gcd(m, numbers.get(i) - numbers.get(i - 1));
        Long finalM = m;
        IntStream.range(1, (int) (Math.sqrt(m) + 1)).filter(i -> finalM % i == 0).forEach( i -> {
            result.add((long) i);
            if(i != finalM / i) result.add(finalM / i);
        });
    }

    private static Long gcd(Long num1, Long num2) {
        return num1 % num2 == 0 ? num2 : gcd(num2, num1%num2);
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        numbers = new ArrayList<>(); result = new ArrayList<>();
        n = input.nextInt();
        IntStream.range(0, n).forEach(i -> numbers.add(input.nextLong()));
        Collections.sort(numbers);
    }
}
