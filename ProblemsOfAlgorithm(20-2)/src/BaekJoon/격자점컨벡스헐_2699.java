package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 격자점컨벡스헐_2699 {

    private static class Point {
        int x, y;
        public Point(int x, int y) {
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

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        for(int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            Point[] points = new Point[N];
            int index = 0;
            while(index < N) {
                if(!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
                points[index++] = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }

            sb.append(makeConvexHall(N, points));
        }
        System.out.print(sb.toString());
    }

    private static String makeConvexHall(int n, Point[] points) {
        // 1. y값이 가장 작은 것을 찾아 인덱스를 반환받고, 해당 인덱스를 0번으로 보낸다.
        sortedByYValue(n, points);

        // 2. 0번 인덱스를 기준으로 시계방향으로 값을 정렬한다.
        sortedByCW(n, points);

        // 3. 그라함 알고리즘
        Stack<Point> st = new Stack<>();
        st.push(points[0]); st.push(points[1]);
        int nextIndex = 2;

        while(nextIndex < n) {
            Point first, second, next;
            // 다음 차례 정점을 확인한다.
            next = points[nextIndex];
            // st 크기가 2보다 클 동안만 반복한다.
            while(st.size() >= 2) {
                // 현재 스택에서 상위 2개를 꺼내온다.
                second = st.pop(); first = st.peek();
                // first, second, next의 CCW가 LEFT라면 second를 push하면 된다.
                if(counterClockWise(first, second, next).equals(State.RIGHT)) {
                    st.push(second);
                    break;
                }
            }
            st.push(next);
            nextIndex++;
        }

        String result = printResult(st);
        return result;
    }

    private static String printResult(Stack<Point> st) {
        StringBuilder sb = new StringBuilder();
        sb.append(st.size()).append("\n");
        Iterator<Point> itr = st.iterator();
        while(itr.hasNext()) {
            Point p = itr.next();
            sb.append(p.x).append(" ").append(p.y).append("\n");
        }
        return sb.toString();
    }

    private static void sortedByCW(int N, Point[] points) {
        Comparator<Point> cwPointComparator = new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                State state = counterClockWise(points[0], o1, o2);
                if(state.equals(State.LEFT)) return 1;
                else if(state.equals(State.RIGHT)) return -1;
                else {
                    long o1Dist = (points[0].x - o1.x) * (points[0].x - o1.x) + (points[0].y - o1.y) * (points[0].y - o1.y);
                    long o2Dist = (points[0].x - o2.x) * (points[0].x - o2.x) + (points[0].y - o2.y) * (points[0].y - o2.y);
                    if(o1Dist > o2Dist) return 1;
                    else if(o1Dist < o2Dist) return -1;
                    else return 0;
                }
            }
        };
        Arrays.sort(points, 1, N, cwPointComparator);
    }

    private static void sortedByYValue(int N, Point[] points) {
        Arrays.sort(points, (o1, o2) -> o2.y == o1.y ? o1.x - o2.x : o2.y - o1.y);
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

