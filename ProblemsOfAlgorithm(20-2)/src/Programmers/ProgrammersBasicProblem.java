package Programmers;

import java.util.*;
import java.util.stream.IntStream;

class Dot {
    private int x, y;

    public Dot(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() { return x; }
    public int getY() { return y; }
    public void setX(int x) { this.x = x; }
    public void setY(int y) { this.y = y; }
}

public class ProgrammersBasicProblem {

    public static void main(String[] args) {
        // INPUT & OUTPUT DATA
        int[][] v = {{1, 4}, {3, 4}, {3, 10}};
        int[] answer;
        List<Dot> dots = makeDotList(v);

        // LOGIC
        answer = makeMiddleCoor(dots);

        // RETURN DATA: 여기에서 정답을 반환한다.
        System.out.println(Arrays.toString(answer));
    }

    private static int[] makeMiddleCoor(List<Dot> dots) {
        int[] xCnt = new int[3];
        int[] yCnt = new int[3];
        int[] xArrays = {dots.get(0).getX(), dots.get(1).getX(), dots.get(2).getX()};
        int[] yArrays = {dots.get(0).getY(), dots.get(1).getY(), dots.get(2).getY()};
        IntStream.range(0, xArrays.length).forEach(i -> xCnt[i] = (int) Arrays.stream(xArrays).filter(x -> x == xArrays[i]).count());
        IntStream.range(0, yArrays.length).forEach(i -> yCnt[i] = (int) Arrays.stream(yArrays).filter(y -> y == yArrays[i]).count());
        int[] answer = {xArrays[IntStream.range(0, xCnt.length).filter(i -> xCnt[i] == 1).findFirst().getAsInt()], yArrays[IntStream.range(0, yCnt.length).filter(i -> yCnt[i] == 1).findFirst().getAsInt()]};
        return answer;
    }

    private static List<Dot> makeDotList(int[][] v) {
        List<Dot> dots = new ArrayList<>();
        Arrays.stream(v).forEach(xy -> dots.add(new Dot(xy[0], xy[1])));
        return dots;
    }
}
