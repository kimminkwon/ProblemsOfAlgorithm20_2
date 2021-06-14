package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class 틱택토_7682 {

    private static Set<String> hs = new HashSet<>();
    private static char[][] currBoard = new char[3][3];

    public static void main(String[] args) throws Exception {
        char[][] board = new char[3][3];
        for(int i = 0; i < 3; i++) Arrays.fill(board[i], '.');
        makeAllStates(".........", 'X');

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            String line = br.readLine().trim();
            if(line.equals("end")) break;
            if(hs.contains(line)) sb.append("valid").append("\n");
            else sb.append("invalid").append("\n");
        }
        System.out.print(sb.toString());
    }

    private static void makeAllStates(String board, char turn) {
        if(hs.contains(board)) {
            return; // 1. 이미 같은 경우의 수를 본 적 있다면 볼 필요 없다.
        }
        if(isEnd(board)) {
            hs.add(board); // 2. 최종 상태라면 현재 board를 hash 처리한다.
            return; // 3. 이미 끝난 게임이므로 리턴한다.
        }

        for(int i = 0; i < 9; i++) {
            if(board.charAt(i) == '.') {
                StringBuilder sb = new StringBuilder(board);
                sb.setCharAt(i, turn);
                char nextTurn = turn == 'O' ? 'X' : 'O';
                makeAllStates(sb.toString(), nextTurn);
                sb.setCharAt(i, '.');
                board = sb.toString();
            }
        }
    }

    private static boolean isEnd(String boardLine) {
        char[][] board = new char[3][3];
        board[0][0] = boardLine.charAt(0); board[0][1] = boardLine.charAt(1); board[0][2] = boardLine.charAt(2);
        board[1][0] = boardLine.charAt(3); board[1][1] = boardLine.charAt(4); board[1][2] = boardLine.charAt(5);
        board[2][0] = boardLine.charAt(6); board[2][1] = boardLine.charAt(7); board[2][2] = boardLine.charAt(8);

        // 1. 가로에 3칸이 이어져있는가?
        for(int i = 0; i < 3; i++)
            if(board[i][0] != '.' && board[i][0] == board[i][1] && board[i][1] == board[i][2]) return true;

        // 2. 세로에 3칸이 이어져있는가?
        for(int i = 0; i < 3; i++)
            if(board[0][i] != '.' && board[0][i] == board[1][i] && board[1][i] == board[2][i]) return true;

        // 3. 대각선이 이어져있는가?
        if(board[0][0] != '.' && board[0][0] == board[1][1] && board[1][1] == board[2][2]) return true;
        if(board[0][2] != '.' && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return true;

        // 4. 모든 게임판이 가득 찼는가?
        int cnt = 0;
        for(int i = 0; i < 3; i++) for(int j = 0; j < 3; j++)
            if(board[i][j] != '.') cnt++;
        if(cnt >= 9) return true;

        return false;
    }
}