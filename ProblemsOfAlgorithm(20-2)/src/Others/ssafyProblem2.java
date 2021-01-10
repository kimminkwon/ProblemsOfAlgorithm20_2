package Others;

/*
2. x + y + z = 100 의 자연수 해를 모두 출력하는 프로그램을 작성하라.
 */

/*
[Solve]
x를 1부터 98까지 선정한다.
y와 z를 100 - x 사이에서 분배한다.
 */
public class ssafyProblem2 {
    public static void main(String[] args) {
        make100();
    }

    private static void make100() {
        for(int x = 1; x < 99; x++) {
            for(int y = 1; y < 100 - x; y++) {
                int z = 100 - x - y;
                System.out.println(x + " + " + y + " + " + z + " = 100");
            }
        }
    }
}
