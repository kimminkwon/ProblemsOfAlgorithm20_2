package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class IPv6_3107 {

    private static String zipIp;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        zipIp = br.readLine();
        System.out.println(findOriginalIp());
    }

    private static String findOriginalIp() {
        // 1. :: 로 생략된 구간을 바꿔준다.
        zipIp = zipIp.replaceAll("::", ":0000:");
        String[] splitIp = zipIp.split(":");
        if(splitIp[0] == "") {
            int cnt = 0;
            String[] splitIp2 = new String[splitIp.length - 1];
            for(int i = 1; i < splitIp.length; i++) splitIp2[cnt++] = splitIp[i];
            splitIp = splitIp2;
        }

        // 2. 좌끝 -> 우끝 순으로 데이터를 넣어준다.
        String[] splitIp2 = new String[8];
        Arrays.fill(splitIp2, "0000");
        int left2 = 0, right2 = 7;
        int left1 = 0, right1 = splitIp.length - 1;
        while(left1 <= right1) {
            if(!splitIp[left1].contains("0000")) splitIp2[left2++] = splitIp[left1++];
            if(!splitIp[right1].contains("0000")) splitIp2[right2--] = splitIp[right1--];
            if(splitIp[left1].contains("0000") && splitIp[right1].contains("0000")) break;
            System.out.println(Arrays.toString(splitIp2));

        }

        // 3. 데이터에서 생략된 0을 채워준다.
        for(int i = 0; i < 8; i++) {
            String data = splitIp2[i];
            if(data.length() < 4) {
                String zeros = "";
                int numOfZero = 4 - data.length();
                for(int z = 0; z < numOfZero; z++) zeros += "0";
                zeros += data;
                splitIp2[i] = zeros;
            }
        }

        return toIpString(splitIp2);
    }

    private static String toIpString(String[] splitIp) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < 7; i++)
            sb.append(splitIp[i]).append(":");
        sb.append(splitIp[7]);
        return sb.toString();
    }
}
