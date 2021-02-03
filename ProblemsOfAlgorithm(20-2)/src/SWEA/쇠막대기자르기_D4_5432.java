package SWEA;

import java.io.*;

public class 쇠막대기자르기_D4_5432 {

    public static void main(String[] args) throws IOException {
		System.setIn(new FileInputStream("input_쇠막대기자르기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        for(int tc = 1; tc <= T; tc++)
            System.out.println("#" + tc + " " + splitIronStick(br.readLine().replace("()", "L").toCharArray()));
    }

    // 코어메소드
    private static int splitIronStick(char[] stickState) {
    	int overLapCnt = 0; int numOfSpiltStick = 0;
    	for(char c : stickState) {
    		switch(c) {
    		case '(':
    			overLapCnt++;
    			break;
    		case ')':
    			numOfSpiltStick++;
    			overLapCnt--;
    			break;
    		case 'L':
    			numOfSpiltStick += overLapCnt;
    			break;
    		}
    	}
        return numOfSpiltStick;
    }
}
