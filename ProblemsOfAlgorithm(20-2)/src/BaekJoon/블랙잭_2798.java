package BaekJoon;

import java.util.Scanner;

public class 블랙잭_2798 {

	static int numOfCard, jackNum;
	static int[] cards;
	static int result;
	
	public static void main(String[] args) {
		makeInput();
		findCombinationOfCard();
		printResult();
	}

	private static void printResult() {
		System.out.println(result);
	}

	private static void findCombinationOfCard() {
		result = 0;
		for(int one = 0; one < numOfCard; one++) {
			for(int two = 0; two < numOfCard; two++) {
				if(two == one)
					break;
				for(int three = 0; three < numOfCard; three++) {
					if(three == one || three == two)
						break;
					int sumOfCards = cards[one] + cards[two] + cards[three];
					if(sumOfCards <= jackNum && sumOfCards > result) {
						result = sumOfCards;
					}
				}
			}
		}
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		numOfCard = input.nextInt();
		jackNum = input.nextInt();
		cards = new int[numOfCard];
		for(int i = 0; i < numOfCard; i++) {
			cards[i] = input.nextInt();
		}
	}

}
