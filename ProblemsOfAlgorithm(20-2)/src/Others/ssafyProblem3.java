package Others;

import java.util.Scanner;
import java.util.stream.IntStream;

/*
3. m개 원소를 가진 집합에서 n개 원소를 가진 집합으로 가는 전사함수의 개수를 출력하는 프로그램을 작성하라.
m과 n의 값을 바꿔보며 값이 너무 커지지 않는 입력범위를 확인해보라.


 */
public class ssafyProblem3 {

    private static int m, n;

    public static void main(String[] args) {
        makeInput();
        findNumOfSurjection();
    }

    // 포함 배제 원리 사용
    private static void findNumOfSurjection() {
        int result = (int) Math.pow(n, m);
        boolean flag = false;
        for (int i = 1; i < n; i++) {
            int sub = (int) (makeComb(n, i) * Math.pow((n - i), m));
            result = flag == false ? result - sub : result + sub;
            flag = flag == false ? true : false;
        }

        System.out.println(result);
    }

    // nCr의 값을 반환하는 메서드
    private static int makeComb(int n, int r) {
        int nFactorial = 1;
        for (int i = 1; i <= n; i++) nFactorial = nFactorial * i;
        int nMinusrFactorial = 1;
        for (int i = 1; i <= n-r; i++) nMinusrFactorial = nMinusrFactorial * i;
        int rFactorial = 1;
        for (int i = 1; i <= r; i++) rFactorial = rFactorial * i;

        return nFactorial / (nMinusrFactorial * rFactorial);
    }

    private static void makeInput() {
        Scanner input = new Scanner(System.in);
        m = input.nextInt();
        n = input.nextInt();
    }
}
