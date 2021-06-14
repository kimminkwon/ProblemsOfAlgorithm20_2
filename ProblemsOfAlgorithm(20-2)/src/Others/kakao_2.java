package Others;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

public class kakao_2 {

    private static class State {
        int x, y, move;

        public State(int x, int y, int move) {
            this.x = x;
            this.y = y;
            this.move = move;
        }

        @Override
        public String toString() {
            return "State{" +
                    "x=" + x +
                    ", y=" + y +
                    ", move=" + move +
                    '}';
        }
    }

    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) {
        String[] places = {"PXOPX", "OXOXP", "OXPXX", "OXXXP", "POOXX"};

        int res = isRight(places);
        System.out.println(res);
    }

    private static int isRight(String[] place) {
        char[][] map = makeArr(place);
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                if(map[i][j] == 'P')  // 사람이 앉아있다.
                    if(!isCurrDist(i, j, map)) return 0;
        return 1;
    }

    private static boolean isCurrDist(int px, int py, char[][] map) {
        // (px, py)의 사람에서 맨해튼 거리가 2인 위치들 중 사람이 있는가?
        boolean[][] visited = new boolean[5][5];
        Deque<State> q = new ArrayDeque<>();
        q.offer(new State(px, py, 0));

        while(!q.isEmpty()) {
            State s = q.poll();
            if(s.move > 2) continue;
            if((s.x != px || s.y != py) && map[s.x][s.y] == 'P') return false;
            for(int d = 0; d < 4; d++) {
                int nx = s.x + dx[d], ny = s.y + dy[d];
                if(isOut(nx, ny) || visited[nx][ny] || map[nx][ny] == 'X') continue;
                q.offer(new State(nx, ny, s.move + 1));
                visited[nx][ny] = true;
            }
        }
        return true;
    }

    private static boolean isOut(int x, int y) {
        return x >= 5 || y >= 5 || x < 0 || y < 0;
    }

    private static char[][] makeArr(String[] place) {
        char[][] map = new char[5][5];
        for(int i = 0; i < 5; i++)
            for(int j = 0; j < 5; j++)
                map[i][j] = place[i].charAt(j);
        return map;
    }

}
