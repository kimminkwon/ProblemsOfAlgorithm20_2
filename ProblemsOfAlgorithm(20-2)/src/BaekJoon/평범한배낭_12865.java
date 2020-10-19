package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class 평범한배낭_12865 {

	private static int numOfItem;
	private static int maxWeight;
	private static int[][] items;
	private static int result;
	
	public static void main(String[] args) {
		makeInput();
		knapsack();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void knapsack() {
		int[][] dpKnap = new int[numOfItem + 1][maxWeight + 1];
		for(int i = 0; i < numOfItem + 1; i++)
			dpKnap[i][0] = 0;
		for(int i = 0; i < maxWeight + 1; i++)
			dpKnap[0][i] = 0;
		
		for(int i = 1; i < numOfItem + 1; i++) {
			for(int j = 1; j < maxWeight + 1; j++) {
				// 1) i번째 물체의 무게를 포함할 수 없는 경우, i-1번째 물체까지일때 같은 무게와 다를게 없다.
				if(items[i][0] > j) {
					dpKnap[i][j] = dpKnap[i-1][j];
				} else {
					// 2) i번째 물체의 무게를 포함할 수 있는 경우
					// 2-1) i-1번째 물체까지일때 같은 무게가 효율적이거나,
					int case1 = dpKnap[i-1][j];
					// 2-2) 현재 무게에서 i번쨰 물체의 무게를 뺀 뒤, i-1번째에서 해당 무게일 때의 최적값에 더한 값이 효율적이다.
					int w = j - items[i][0];
					int case2 = dpKnap[i-1][w] + items[i][1];
					dpKnap[i][j] = Math.max(case1, case2);
				}
			}
		}
		result = dpKnap[numOfItem][maxWeight];
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		numOfItem = input.nextInt();
		maxWeight = input.nextInt();
		items = new int[numOfItem + 1][2]; items[0][0] = 0; items[0][1] = 0;
		
		for(int i = 1; i < numOfItem + 1; i++) {
			items[i][0] = input.nextInt();
			items[i][1] = input.nextInt();
		}
	}
}
