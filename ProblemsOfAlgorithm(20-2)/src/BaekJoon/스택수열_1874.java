package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 스택수열_1874 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());

        int temp;
        int max = 0;
        int top = 0;
        int[] stack = new int[n];

        while(n-- > 0){
            temp = Integer.parseInt(br.readLine());
            if(temp > max){
                for(int i=max+1; i<=temp; i++){
                    stack[top++] = i;
                    sb.append("+\n");
                }
                max = temp;
            }else if(stack[top-1] != temp) {
                System.out.println("NO");
                return;
            }
            top--;
            sb.append("-\n"); // pop
        }
        System.out.println(sb);
    }
}
