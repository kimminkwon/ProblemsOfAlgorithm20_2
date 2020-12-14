package Others;

import java.util.Arrays;

public class 모음순서변경 {
    public static void main(String[] args) {

        // 모음: a, e, i, o, u
        String flag = "aeiou";
        String instance = "naver webtoon";
        char[] instanceArray = instance.toCharArray();

        int start = 0;
        int end = instanceArray.length - 1;

        while(true) {
            if(start >= end) break;

            // 왼쪽에서부터 마주치는 모음 찾기
            for(int i = start + 1; i < end; i++) {
                start = i;
                if(flag.contains(String.valueOf(instanceArray[i]))) {
                    break;
                }
            }

            // 오른쪽에서부터 마주치는 모음 찾기
            for(int i = end - 1; i >= start; i--) {
                end = i;
                if(flag.contains(String.valueOf(instanceArray[i]))) {
                    break;
                }
            }

            swapText(start, end, instanceArray);
        }

        String result = String.valueOf(instanceArray);
        System.out.println(result);

    }

    private static void swapText(int start, int end, char[] instanceArray) {
        if(start == end) return;

        char box = instanceArray[start];
        instanceArray[start] = instanceArray[end];
        instanceArray[end] = box;
    }

}
