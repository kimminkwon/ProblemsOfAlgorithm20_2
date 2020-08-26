package BaekJoon;

import java.io.*;
import java.util.*;

public class 단어의개수_1152 {

	static String sentence;
	static String[] words;
	
	public static void main(String[] args) {
		makeInput();
		removeBlank();
		partitioningByWord();
		printResult();
	}
	
	private static void printResult() {
		if(sentence.equals(""))
			System.out.println("0");
		else
			System.out.println(words.length);
	}
	
	private static void removeBlank() {
		sentence = sentence.trim();
	}

	private static void partitioningByWord() {
		words = sentence.split(" ");
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		sentence = input.nextLine();
	}

}
