package Programmers;
import java.util.*;

public class Level2_IronStick {

	public static void main(String[] args) {
		// input example
		String arrangement = "()(((()())(())()))(())";
		int answer = 0;
		ArrayList<Integer> box = new ArrayList<Integer>();
		char[] arr = arrangement.toCharArray();
		int cnt = 0;
		for (int i = 0; i < arr.length; i++) {
			if (arr[i] == '(')
				cnt++;
			else
				cnt--;
			box.add(cnt);
		}
		System.out.println(box);
		
		boolean isLazer = false;
		int buffer = -1;
		for (int i = 1; i < box.size(); i++) {
			System.out.println(i+"번째 사이클, 현재 값: " + box.get(i) + ", char: " + arr[i]);
			if(box.get(i) == (box.get(i-1) - 1) && arr[i-1] == '(') {
				System.out.println("이전 값에 비해 1 감소했고, 이전 값이 (이기 때문에 이것은 레이저입니다. 현재 값만큼의 막대기를 얻습니다. 얻은 막대기: " + box.get(i));
				answer = answer + box.get(i);
			}
			else if(box.get(i) == (box.get(i-1) - 1)) {
				System.out.println("이전 값에 비해 1 감소했으나, 이전 값이 )이기 때문에 이것은 막대기 하나의 끝입니다. 하나의 막대기를 얻습니다. 얻은 막대기: 1");	
				answer = answer + 1;
			}
		}
		System.out.println("정답은 " + answer);
	}

}
