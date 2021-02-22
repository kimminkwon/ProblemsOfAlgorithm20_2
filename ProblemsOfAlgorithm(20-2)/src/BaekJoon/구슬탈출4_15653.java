package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 구슬탈출4_15653 {

    private static class State {
        int rx, ry, bx, by;
        public String getStr() { return rx + "+" +  ry + "+" + bx + "+" + by; }

        @Override
        public String toString() {
            return "[ r(" + rx + ", " + ry + "), b(" + bx + ", " + by + ") ]";
        }
    }
    private static int N, M, gx, gy, result;
    private static char[][] map;
    private static int[] dX = {-1, 1, 0, 0}, dY = {0, 0, -1, 1};
    private static Set<String> hashSet = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        map = new char[N][M];
        State initState = new State();

        for(int i = 0; i < N; i++) {
            String box = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = box.charAt(j);
                if(map[i][j] == 'B') { initState.bx = i; initState.by = j; }
                else if(map[i][j] == 'R') { initState.rx = i; initState.ry = j; }
                else if(map[i][j] == 'O') { gx = i; gy = j; }
            }
        }

        findMinimumTry(initState);
        System.out.println(result);
    }

    private static void findMinimumTry(State initState) {
        Deque<State> q = new ArrayDeque<>();
        q.offer(initState);
        hashSet.add(initState.getStr());

        while(!q.isEmpty()) {
            int size = q.size();
            while(--size >= 0) {
                State currState = q.poll();
                if(currState.rx == -1) return;

                for(int d = 0; d < 4; d++) {
                    State nextState = doMove(currState, d);
                    if(nextState.bx  == -1) continue;
                    if(!hashSet.contains(nextState.getStr())) {
                        q.offer(nextState);
                        hashSet.add(nextState.getStr());
                    }
                }
            }
            result++;
        }
        result = -1;
    }

    private static State doMove(State s, int dir) {
        State nextState = new State();
        nextState.bx = s.bx; nextState.by = s.by;
        nextState.rx = s.rx; nextState.ry = s.ry;

        boolean rOut = false, bOut = false;
        boolean first = findFirst(nextState, dir);
        if(first) {
            while(true) {
                int rnX = nextState.rx == -1 ? -1 : nextState.rx + dX[dir], rnY = nextState.ry + dY[dir];
                if(isOut(rnX, rnY, nextState.bx, nextState.by)) break;
                else {
                    if(rnX == gx && rnY == gy) {
                        nextState.rx = -1;
                    } else nextState.rx = rnX; nextState.ry = rnY;
                }
            }
            while(true) {
                int bnX = nextState.bx == -1 ? -1 : nextState.bx + dX[dir], bnY = nextState.by + dY[dir];
                if(isOut(bnX, bnY, nextState.rx, nextState.ry)) break;
                else {
                    if(bnX == gx && bnY == gy) {
                        nextState.bx = -1;
                    } else nextState.bx = bnX; nextState.by = bnY;
                }
            }
        } else {
            while(true) {
                int bnX = nextState.bx == -1 ? -1 : nextState.bx + dX[dir], bnY = nextState.by + dY[dir];
                if(isOut(bnX, bnY, nextState.rx, nextState.ry)) break;
                else {
                    if(bnX == gx && bnY == gy) {
                        nextState.bx = -1;
                    } else nextState.bx = bnX; nextState.by = bnY;
                }
            }
            while(true) {
                int rnX = nextState.rx == -1 ? -1 : nextState.rx + dX[dir], rnY = nextState.ry + dY[dir];
                if(isOut(rnX, rnY, nextState.bx, nextState.by)) break;
                else {
                    if(rnX == gx && rnY == gy) {
                        nextState.rx = -1;
                    } else nextState.rx = rnX; nextState.ry = rnY;
                }
            }
        }

        return nextState;
    }

    private static boolean findFirst(State nextState, int dir) {
        switch (dir) {
            case 0:
                return nextState.rx <= nextState.bx;
            case 1:
                return nextState.rx >= nextState.bx;
            case 2:
                return nextState.ry <= nextState.by;
            case 3:
                return nextState.ry >= nextState.by;
            default:
                return true;
        }
    }

    private static boolean isOut(int x, int y, int ox, int oy) {
        return x >= N - 1 || y >= M - 1 || x <= 0 || y <= 0 || map[x][y] == '#' || (x == ox && y == oy);
    }
}
