package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class 백조의호수_3197 {

    private static class Coor {
        int x, y;
        Coor(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    private static Deque<Coor> q = new ArrayDeque<>();
    private static Deque<Coor> waterQ = new ArrayDeque<>();
    private static char[][] map;
    private static boolean[][] visited;
    private static Coor[] swan;
    private static int R, C;
    private static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        swan = new Coor[2];
        map = new char[R][C];
        visited = new boolean[R][C];

        // 데이터 입력
        int swanIdx = 0;
        for(int i = 0 ; i < R ; ++i) {
            String box = br.readLine();
            for(int j = 0 ; j < C ; ++j) {
                map[i][j] = box.charAt(j);
                if(map[i][j] == 'L') swan[swanIdx++] = new Coor(i, j);
                if(map[i][j] != 'X') waterQ.offer(new Coor(i, j));
            }
        }

        q.offer(swan[0]);
        visited[swan[0].x][swan[0].y] = true;

        int result = 0;

        while(true) {
            Deque<Coor> nextQ = new ArrayDeque<>();
            if(isMeetedSwan(nextQ)) break;
            q = nextQ;
            doMelting();
            result++;
        }

        System.out.println(result);
    }

    private static void doMelting() {
        int size = waterQ.size();
        for(int i = 0 ; i < size ; ++i) {
            Coor currC = waterQ.poll();
            for(int d = 0 ; d < 4 ; ++d) {
                int nx = currC.x + dx[d];
                int ny = currC.y + dy[d];

                if(isOut(nx, ny)) continue;
                if(map[nx][ny] == 'X') {
                    map[nx][ny] = '.';
                    waterQ.offer(new Coor(nx, ny));
                }
            }
        }
    }

    private static boolean isMeetedSwan(Deque<Coor> nextQ) {
        while(!q.isEmpty()) {
            Coor currC = q.poll();

            if(currC.x == swan[1].x && currC.y == swan[1].y) return true;

            for(int d = 0 ; d < 4 ; ++d) {
                int nx = currC.x + dx[d], ny = currC.y + dy[d];
                if(isOut(nx, ny) || visited[nx][ny]) continue;
                visited[nx][ny] = true;
                if(map[nx][ny] == 'X') {
                    nextQ.offer(new Coor(nx, ny));
                    continue;
                }
                q.offer(new Coor(nx, ny));
            }
        }
        return false;
    }

    private static boolean isOut(int x, int y) {
        return x >= R || x < 0 || y >= C || y < 0;
    }
}
