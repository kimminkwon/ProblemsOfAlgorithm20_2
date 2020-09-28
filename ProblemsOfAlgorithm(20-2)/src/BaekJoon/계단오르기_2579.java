package BaekJoon;

import java.util.Arrays;
import java.util.Scanner;

public class 계단오르기_2579 {

	private static int numOfStair;
	private static int[] costOfStair;
	private static int[] maxCostOfStair;
	
	public static void main(String[] args) {
		makeInput();
		makeMaximumCostOfStair();
		printResult();
	}

	private static void printResult() {
		System.out.println(Arrays.toString(costOfStair));
		System.out.println(Arrays.toString(maxCostOfStair));
		System.out.println(maxCostOfStair[numOfStair]);
	}

	private static void makeMaximumCostOfStair() {
		int[] isThree = new int[numOfStair + 1];
		Arrays.fill(isThree, 0);
		
		for(int i = 0; i <= numOfStair; i++) {
			if(i == 0) {
				maxCostOfStair[i] = 0;
			}
			else if(i == 1) {
				maxCostOfStair[i] = costOfStair[i];
				isThree[i] = 1;
			}
			else if(i == 2) {
				maxCostOfStair[i] = costOfStair[i] + maxCostOfStair[i - 1];
				isThree[i] = 2;
			}
			else {
				if(isThree[i - 1] < 2){
					int twoStiarValue = costOfStair[i] + maxCostOfStair[i - 2];
					int oneStiarValue = costOfStair[i] + maxCostOfStair[i - 1];
					if(oneStiarValue > twoStiarValue) {
						maxCostOfStair[i] = oneStiarValue;
						isThree[i] = isThree[i - 1] + 1;
					}
					else {
						maxCostOfStair[i] = twoStiarValue;
						isThree[i] = 1;
					}
				}
				else {
					int threeStiarValue = costOfStair[i] + maxCostOfStair[i - 3] + costOfStair[i - 1];
					int twoStiarValue = costOfStair[i] + maxCostOfStair[i - 2];
					if(threeStiarValue > twoStiarValue) {
						maxCostOfStair[i] = threeStiarValue;
						isThree[i] = 2;
					}
					else {
						maxCostOfStair[i] = twoStiarValue;
						isThree[i] = 1;
					}
				}
			}
			
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		numOfStair = input.nextInt();
		costOfStair = new int[numOfStair + 1]; maxCostOfStair = new int[numOfStair + 1];
		costOfStair[0] = 0;
		for(int i = 1; i <= numOfStair; i++) {
			costOfStair[i] = input.nextInt();
		}
	}

}
