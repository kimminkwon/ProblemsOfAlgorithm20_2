package BaekJoon;

import java.util.*;
import java.io.*;

public class 세수정렬_2752 {
	public static void main(String[] args) {
		int[] numArr = new int[3];
		int min, index = 0, box;
		Scanner input = new Scanner(System.in);

		for (int i = 0; i < numArr.length; i++) {
			numArr[i] = input.nextInt();
		}
		
		for(int i = 0; i < numArr.length; i++) {
			min = Integer.MAX_VALUE;
			for(int j = i; j < numArr.length; j++) {
				if(min > numArr[j]) {
					min = numArr[j];
					index = j;
				}
			}
			box = numArr[i];
			numArr[i] = numArr[index];
			numArr[index] = box;
		}
		System.out.printf("%d %d %d\n", numArr[0], numArr[1], numArr[2]);
	}
}
