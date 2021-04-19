package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Stack;
import java.util.StringTokenizer;

public class 볼록껍질_1708 {

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
    private static int N;
    private static Point[] points;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        points = new Point[N];

        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken()), y = Long.parseLong(st.nextToken());
            points[i] = new Point(x, y);
        }

        System.out.println(findNumOfConvexHallVertex());
    }

    private static int findNumOfConvexHallVertex() {
        // 1. y값이 가장 작은 것을 찾아 인덱스를 반환받고, 해당 인덱스를 0번으로 보낸다.
        int flagIndex = getFlagIndex();
        swapVertex(flagIndex);

        // 2. y값이 가장 작은 정점을 기준 점으로 각도에 따라 정렬한다.
        sortPointArrForDegree(points[0]);

        // 3. 그라함 알고리즘
        Stack<Point> st = new Stack<>();
        st.push(points[0]); st.push(points[1]);
        int nextIndex = 2;

        while(nextIndex < N) {
            Point first, second, next;
            // 다음 차례 정점을 확인한다.
            next = points[nextIndex];
            // st 크기가 2보다 클 동안만 반복한다.
            while(st.size() >= 2) {
                // 현재 스택에서 상위 2개를 꺼내온다.
                second = st.pop(); first = st.peek();
                // first, second, next의 CCW가 LEFT라면 second를 push하면 된다.
                if(counterClockWise(first, second, next).equals(State.LEFT)) {
                    st.push(second);
                    break;
                }
            }
            st.push(next);
            nextIndex++;
        }
        return st.size();
    }

    private static void swapVertex(int flagIndex) {
        Point box = points[0];
        points[0] = points[flagIndex];
        points[flagIndex] = box;
    }

    private static int getFlagIndex() {
        int minIndex = -1;
        long min = Long.MAX_VALUE;
        for(int i = 0; i < N; i++) {
            if(points[i].y < min) {
                min = points[i].y;
                minIndex = i;
            }
        }
        return minIndex;
    }

    private static void sortPointArrForDegree(Point flagPoint) {
        Comparator<Point> anglePointComparator = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                State state = counterClockWise(flagPoint, o1, o2);
                if(state.equals(State.LEFT)) return -1;
                else if(state.equals(State.RIGHT)) return 1;
                else {
                    long o1Dist = (flagPoint.x - o1.x) * (flagPoint.x - o1.x) + (flagPoint.y - o1.y) * (flagPoint.y - o1.y);
                    long o2Dist = (flagPoint.x - o2.x) * (flagPoint.x - o2.x) + (flagPoint.y - o2.y) * (flagPoint.y - o2.y);
                    if(o1Dist > o2Dist) return 1;
                    else if(o1Dist < o2Dist) return -1;
                    else return 0;
                }
            }
        };
        Arrays.sort(points, 1, points.length, anglePointComparator);
    }

    // 직선 AB와 정점 C의 관계를 ENUM으로 표현
    private static State counterClockWise(Point A, Point B, Point C) {
        long part1 = (A.x * B.y + B.x * C.y + C.x * A.y);
        long part2 = (A.x * C.y + B.x * A.y + C.x * B.y);
        long ccw = part1 - part2;

        if(ccw < 0L) return State.RIGHT;
        else if(ccw > 0L) return State.LEFT;
        else return State.SAME;
    }
}
