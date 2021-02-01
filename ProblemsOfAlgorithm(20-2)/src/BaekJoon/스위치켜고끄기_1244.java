package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 스위치켜고끄기_1244 {

    private static int numOfSwitch, numOfStudent;
    private static boolean[] switches;
    private static int[][] students;

    public static void main(String[] args) throws Exception {
        makeInput();
        doSomeInSwitch();
        printResult();
    }

    private static void printResult() {
        StringBuilder sb = new StringBuilder();
        for(int i = 1; i <= numOfSwitch; i++) {
            sb.append(switches[i] ? "1" : "0");
            sb.append(" ");
            if(i % 20 == 0) sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    private static void doSomeInSwitch() {
        for(int i = 0; i < numOfStudent; i++) {
            if(students[i][0] == 2) doWomen(students[i][1]);
            else doMen(students[i][1]);
        }
    }

    private static void doMen(int switchNum) {
        int cnt = 1;
        while(true) {
            int currSwitchNum = switchNum * cnt++;
            if(isOut(currSwitchNum)) break;
            switches[currSwitchNum] = switches[currSwitchNum] ? false : true;
        }
    }

    private static void doWomen(int switchNum) {
        int cnt = 1;
        switches[switchNum] = switches[switchNum] ? false : true;
        while(true) {
            int leftNum = switchNum - cnt;
            int rightNum = switchNum + cnt;
            if(isOut(leftNum) || isOut(rightNum)) break;
            if(switches[leftNum] != switches[rightNum]) break;
            switches[leftNum] = switches[leftNum] ? false : true;
            switches[rightNum] = switches[rightNum] ? false : true;
            cnt++;
        }
    }

    private static void makeInput() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        numOfSwitch = Integer.parseInt(br.readLine());
        switches = new boolean[numOfSwitch + 1];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 1; i <= numOfSwitch; i++)
            switches[i] = st.nextToken().equals("0") ? false : true;

        numOfStudent = Integer.parseInt(br.readLine());
        students = new int[numOfStudent][2];
        for(int i = 0; i < numOfStudent; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            students[i][0] = Integer.parseInt(st.nextToken()); students[i][1] = Integer.parseInt(st.nextToken());
        }
    }

    private static boolean isOut(int num) {
        return num > numOfSwitch || num < 1 ? true : false;
    }
}
