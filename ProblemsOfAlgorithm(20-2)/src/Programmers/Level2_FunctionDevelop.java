package Programmers;
import java.util.*;

public class Level2_FunctionDevelop {
	public static void main(String[] args) {
		int[] progresses = { 93, 30, 55, 90, 95, 0, 30 };
		int[] speeds = { 1, 30, 5, 23, 62, 23, 10 };
		int[] remainDays = new int[progresses.length];
		Queue<Integer> proQ = new LinkedList<>();
		ArrayList<Integer> solutionList = new ArrayList<Integer>();

		// 남은 일수 계산
		while (true) {
			int cnt = 0;
			for (int i = 0; i < progresses.length; i++) {
				if (progresses[i] < 100) {
					remainDays[i]++;
				} else
					cnt++;
			}
			if (cnt == progresses.length)
				break;

			for (int i = 0; i < progresses.length; i++) {
				progresses[i] = progresses[i] + speeds[i];
				System.out.print(progresses[i] + ", ");
			}
			System.out.println();
		}

		System.out.println("남은 일수 총 계산=========");
		for (int i = 0; i < progresses.length; i++) {
			System.out.print(remainDays[i] + ", ");
		}
		System.out.println();
		
		for (int i = 0; i < progresses.length; i++) {
			System.out.println(i + "번째 루프=====");
			if (remainDays[i] > 0) {
				System.out.println(i + "번째 값인 " + remainDays[i] + "가 0보다 크다. 여기서 남은 일수를 빼줄 것!");
				int box = remainDays[i];
				for (int j = i; j < progresses.length; j++) {
					remainDays[j] = remainDays[j] - box;
					System.out.print(remainDays[j] + ", ");
				}
				System.out.println();
				
				int cnt = 0;
				for (int j = i; j < progresses.length; j++) {
					if(remainDays[j] > 0) break;
					else cnt++;
				}
				System.out.println(cnt + "개가 끝남");
				solutionList.add(cnt);
				i = i + cnt - 1;
			}
		}

	
		int[] answer = new int[solutionList.size()]; 
		for (int i = 0; i < solutionList.size(); i++) {
			answer[i] = solutionList.get(i);
		}

	}
}
