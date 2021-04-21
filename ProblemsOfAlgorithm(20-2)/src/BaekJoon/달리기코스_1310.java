package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 달리기코스_1310 {

    private static class Point {
        long x, y;
        public Point(long x, long y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return "(" +
                    "" + x +
                    ", " + y +
                    ')';
        }
    }
    private static enum State {LEFT, RIGHT, SAME};
    private static int C;
    private static Point[] loads;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        C = Integer.parseInt(br.readLine());
        loads = new Point[C];
        for(int i = 0; i < C; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            loads[i] = new Point(Long.parseLong(st.nextToken()), Long.parseLong(st.nextToken()));
        }
        long result = findMaxDistBetweenloads();
        System.out.println(result);
    }

    private static long findMaxDistBetweenloads() {
        // 1. y값이 가장 작은 것을 0번에 넣어놓는다.
        sortedByYValue();

        // 2. 0번 인덱스 기준 반시계 방향으로 정렬한다.
        sortedByCCW();

        // 3. 그라함 알고리즘 사용
        Stack<Point> st = new Stack<>();
        st.push(loads[0]); st.push(loads[1]);
        int nextIndex = 2;

        while(nextIndex < C) {
            Point first, second, next;
            next = loads[nextIndex];
            while(st.size() >= 2) {
                second = st.pop(); first = st.peek();
                if(counterClockWise(first, second, next).equals(State.LEFT)) {
                    st.push(second);
                    break;
                }
            }
            st.push(next);
            nextIndex++;
        }

        List<Point> convexList = getConvexHallList(st);

        long maxDist = getMaxDist(convexList);
        return maxDist;
    }

    private static long getMaxDist(List<Point> convexList) {
        long maxDist = 0;
        for(int i = 0; i < convexList.size(); i++) {
            for(int j = i + 1; j < convexList.size(); j++) {
                maxDist = Math.max(getDist(convexList.get(i), convexList.get(j)), maxDist);
            }
        }
        return maxDist;
    }

    private static long getDist(Point p1, Point p2) {
        long xDist = (p1.x - p2.x) * (p1.x - p2.x);
        long yDist = (p1.y - p2.y) * (p1.y - p2.y);
        return xDist + yDist;
    }

    private static List<Point> getConvexHallList(Stack<Point> st) {
        List<Point> convexList = new ArrayList<>();
        while(!st.isEmpty()) convexList.add(st.pop());
        return convexList;
    }

    private static void sortedByCCW() {
        Comparator<Point> ccwPointComparator = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                State state = counterClockWise(loads[0], o1, o2);
                if(state.equals(State.LEFT)) return -1;
                else if(state.equals(State.RIGHT)) return 1;
                else {
                    long o1Dist = (loads[0].x - o1.x) * (loads[0].x - o1.x) + (loads[0].y - o1.y) * (loads[0].y - o1.y);
                    long o2Dist = (loads[0].x - o2.x) * (loads[0].x - o2.x) + (loads[0].y - o2.y) * (loads[0].y - o2.y);
                    if(o1Dist > o2Dist) return 1;
                    else if(o1Dist < o2Dist) return -1;
                    else return 0;
                }
            }
        };
        Arrays.sort(loads, 1, C, ccwPointComparator);
    }

    private static void sortedByYValue() {
        Arrays.sort(loads, (o1, o2) -> (int) (o2.y == o1.y ? o1.x - o2.x : o1.y - o2.y));
    }

    private static State counterClockWise(Point A, Point B, Point C) {
        long part1 = (A.x * B.y + B.x * C.y + C.x * A.y);
        long part2 = (A.x * C.y + B.x * A.y + C.x * B.y);
        long ccw = part1 - part2;

        if(ccw < 0L) return State.RIGHT;
        else if(ccw > 0L) return State.LEFT;
        else return State.SAME;
    }
}
