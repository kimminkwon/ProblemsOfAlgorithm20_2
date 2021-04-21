package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 달이차오른다가자_1194 {

    private static class State {
        int x, y, move;
        boolean[] key;

        public State(int x, int y, int move, boolean[] key) {
            this.x = x;
            this.y = y;
            this.move = move;
            this.key = key;
        }
    }
    private static int N, M, result = Integer.MAX_VALUE;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    private static char[][] map;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new char[N][M];

        for(int i = 0; i < N; i++) {
            String box = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = box.charAt(j);
            }
        }
        findMinimumPath();
        System.out.println(result == Integer.MAX_VALUE ? -1 : result);
    }

    private static void findMinimumPath() {
        Deque<State> q = new ArrayDeque<>();
        q.offer(getInitState());
        Map<String, boolean[][]> visited = makeVisited();

        while (!q.isEmpty()) {
            State s = q.poll();
            if(map[s.x][s.y] == '1') result = Math.min(s.move, result);
            for(int d = 0; d < 4; d++) {
                int nx = s.x + dx[d], ny = s.y + dy[d];
                // 1. 벗어났거나 다음 위치가 벽인가?
                if(isOut(nx, ny) || map[nx][ny] == '#') continue;
                if(map[nx][ny] == 'a' || map[nx][ny] == 'b' || map[nx][ny] == 'c' || map[nx][ny] == 'd' || map[nx][ny] == 'e' || map[nx][ny] == 'f') {
                    // 2. 열쇠인가?
                    boolean[] newKey = copyKey(s);
                    newKey[map[nx][ny] - 'a'] = true;
                    if(visited.get(getKeyString(newKey))[nx][ny]) continue;
                    visited.get(getKeyString(newKey))[nx][ny] = true;
                    q.offer(new State(nx, ny, s.move + 1, newKey));
                } else if(map[nx][ny] == 'A' || map[nx][ny] == 'B' || map[nx][ny] == 'C' || map[nx][ny] == 'D' || map[nx][ny] == 'E' || map[nx][ny] == 'F') {
                    // 3. 문인가?
                    // 3-1. 해당 키가 현재 상태에 없다면 진행할 수 없다.
                    if(!s.key[map[nx][ny] - 'A']) continue;
                    if(visited.get(getKeyString(s.key))[nx][ny]) continue;
                    visited.get(getKeyString(s.key))[nx][ny] = true;
                    q.offer(new State(nx, ny, s.move + 1, s.key));
                } else {
                    // 4. 아니라면 출구거나 민식이의 위치거나 빈 곳이다.
                    if(visited.get(getKeyString(s.key))[nx][ny]) continue;
                    visited.get(getKeyString(s.key))[nx][ny] = true;
                    q.offer(new State(nx, ny, s.move + 1, s.key));
                }
            }
        }
    }

    private static Map<String, boolean[][]> makeVisited() {
        Map<String, boolean[][]> visited = new HashMap<>();
        boolean[] key = new boolean[6];
        subsetForKey(0, key, visited);
        return visited;
    }

    private static void subsetForKey(int index, boolean[] key, Map<String,boolean[][]> visited) {
        if(index == 6) {
            visited.put(getKeyString(key), new boolean[N][M]);
            return;
        }
        key[index] = true;
        subsetForKey(index + 1, key, visited);
        key[index] = false;
        subsetForKey(index + 1, key, visited);
    }

    private static boolean isOut(int x, int y) {
        return x >= N || y >= M || x < 0 || y < 0;
    }

    private static boolean[] copyKey(State s) {
        boolean[] copyKey = new boolean[6];
        for(int i = 0; i < 6; i++)
            copyKey[i] = s.key[i];
        return copyKey;
    }

    private static String getKeyString(boolean[] key) {
        String strKey = "";
        for(int i = 0; i < 6; i++)
            if(key[i]) strKey += String.valueOf((char)('A' + i));
        return strKey.equals("") ? "X" : strKey;
    }

    private static State getInitState() {
        for(int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                if(map[i][j] == '0') return new State(i, j, 0, new boolean[6]);
        return null;
    }
}

