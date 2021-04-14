package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 게임_1103 {

    private static int N, M, result;
    private static int[][] map;
    private static int[][] countArr;
    private static boolean[][] visited;
    private static boolean isCycle;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        countArr = new int[N][M];
        visited = new boolean[N][M];

        for(int i = 0; i < N; i++) {
            String box = br.readLine();
            for(int j = 0; j < M; j++) {
                char c = box.charAt(j);
                if(c == 'H') c = '0';
                map[i][j] = Character.getNumericValue(c);
            }
        }
        doDFS(0, 0, 1);
        System.out.println(isCycle ? -1 : result);
    }

    private static void doDFS(int x, int y, int count) {
        result = Math.max(count, result);
        countArr[x][y] = count;

        for(int d = 0; d < 4; d++) {
            int nx = x + (dx[d] * map[x][y]), ny = y + (dy[d] * map[x][y]);
            if(isOut(nx, ny)) continue;
            if(visited[nx][ny]) {
                isCycle = true;
                return;
            }
            if(countArr[nx][ny] > count) continue;
            visited[nx][ny] = true;
            doDFS(nx, ny, count + 1);
            visited[nx][ny] = false;
        }
    }

    private static boolean isOut(int x, int y) {
        return x >= N || y >= M || x < 0 || y < 0 || map[x][y] == 0;
    }

    private static void printArr(int[][] map) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[i].length; j++) {
                sb.append(String.format("%2d", map[i][j]));
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }
}
