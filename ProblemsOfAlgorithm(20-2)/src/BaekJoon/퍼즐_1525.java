package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 퍼즐_1525 {

    private static class Coor {
        int x, y;
        int[][] board;
        public Coor(int x, int y, int[][] board) { this.x = x; this.y = y; this.board = board; }
    }

    private static final int MAX_CASE = 362880;
    private static int dist;
    private static Set<String> visited = new HashSet<>();
    private static int[][] board = {{1, 2, 3}, {4, 5, 6}, {7, 8, 0}}, map = new int[3][3];
    private static int[] dX = {-1, 0, 1, 0}, dY = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for(int i = 0; i < 3; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 3; j++)
                map[i][j] = Integer.parseInt(st.nextToken());
        }
        findMinimumPath();
        System.out.println(dist);
    }

    private static void findMinimumPath() {
        Deque<Coor> q = makeQueue();
        visited.add(getHash(q.peek().board));

        loop: while(true) {
            if(dist > MAX_CASE || q.isEmpty()) {
                dist = -1;
                break loop;
            }
            int size = q.size();
            while(--size >= 0) {
                Coor c = q.poll();
                if(isSame(c.board)) break loop;
                for(int d = 0; d < 4; d++) {
                    int nX = c.x + dX[d], nY = c.y + dY[d];
                    if(!isOut(nX, nY)) {
                        int[][] currMap = copyMap(c.board, c.x, c.y, nX, nY);
                        String hash = getHash(currMap);
                        if(!visited.contains(hash)) {
                            visited.add(hash);
                            q.offer(new Coor(nX, nY, currMap));
                        }
                    }
                }
            }
            dist++;
        }
    }

    private static Deque<Coor> makeQueue() {
        Deque<Coor> q = new ArrayDeque<>();
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (map[i][j] == 0) q.offer(new Coor(i, j, copyMap(map, 0, 0, 0, 0)));
        return q;
    }

    private static boolean isOut(int x, int y) {
        return x >= 3 || y >= 3 || x < 0 || y < 0;
    }

    private static int[][] copyMap(int[][] map, int x1, int y1, int x2, int y2) {
        int[][] copyMap = new int[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                copyMap[i][j] = map[i][j];
        int temp = copyMap[x1][y1];
        copyMap[x1][y1] = copyMap[x2][y2];
        copyMap[x2][y2] = temp;
        return copyMap;
    }

    public static String getHash(int[][] board) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 3; i++)
            for(int j = 0; j < 3; j++)
                sb.append(board[i][j]);
        return sb.toString();
    }

    private static boolean isSame(int[][] makeMap) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] != makeMap[i][j]) return false;
        return true;
    }
}
