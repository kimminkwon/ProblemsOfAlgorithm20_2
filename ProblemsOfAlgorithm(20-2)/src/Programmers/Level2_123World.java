package Programmers;
import java.util.*;
import java.math.*;

public class Level2_123World {
	public static void main(String[] args) {
		// input example
		String answer = "";
		String ans = "4";
		long flag = 1;
		long param = Long.parseLong(ans);
		int cnt = 1;
		long another = 0;
		
		while(true) {
			if((param / (long) (flag + Math.pow(3, cnt))) == 0) {
				System.out.println("몫이 0이므로 계산 이전 값이 적절함");
				System.out.println("현재 값 : " + flag);
				System.out.println("이후 값 : " + (long) (flag + Math.pow(3, cnt)));
				System.out.println("자릿수: " + cnt);
				System.out.println("param에서 현재 값을 빼면 : " + (param - flag));				
				another = (param - flag);
				break;
			}
			else {
				flag = (long) (flag + Math.pow(3, cnt));
				cnt++;
				System.out.println("현재 flag는 " + flag);
				System.out.println("현재까지 자릿수는 " + cnt);
			}
		}
		
		int[] value = new int[cnt];
		
		int i = 0;
		while(cnt > 0) {
			int m = (int) Math.pow(3, cnt-1);
			int mok = (int) (another / m);
			another = another % m;
			System.out.println(i + "번째 자리 수 남은 값을 나눈 몫: " + mok);
			System.out.println(i + "번째 자리 수 남은 값을 나눈 나머지: " + another);
			value[i] = mok;
			i++;
			cnt--;
		}
		String box = "";
		for (int k = 0; k < value.length; k++) {
			System.out.print(value[k] + " ");
			if(value[k] == 0) {
				box = "1";
			}
			else if(value[k] == 1) {
				box = "2";
			}
			else if(value[k] == 2) {
				box = "4";
			}
			answer = answer + box;
		}
		System.out.println("정답은 " + answer);
		
	}

}
