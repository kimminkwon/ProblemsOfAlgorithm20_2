package BaekJoon;
import java.io.*;
import java.util.*;

public class 수정렬하기_2751 {
	public static void main(String[] args) throws IOException {
		int n;

		// 입력의 개수가 많을 때 Scanner를 사용하면 비효율젹이다.
		// Scanner를 통한 입력보다 BufferedReader를 사용하면 빠르게 입력받을 수 있다.
		BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(input.readLine());

		int[] numArr = new int[n];
		for(int i = 0; i < numArr.length; i++) {
			numArr[i] = Integer.parseInt(input.readLine());
		}
		qSort(numArr, 0, n-1);
		for(int num : numArr) {
			System.out.println(num);
		}
	}
	
	private static void qSort(int[] numArr, int start, int end) {
		if(start >= end) {
			return;
		}
		
		// Q sort의 Worst case는 {n, n-1, ..., 1}과 같이 역순으로 주어졌을 떄
		// 따라서  중앙 Index의 값을 앞으로 Swap함으로써 정렬 속도를 빠르게 할 수 있다.
		
		int mid = (start + end) / 2;
		swap(numArr, mid, start);
		
		int pivot = start;
		int maxIndex = start + 1;
		int minIndex = end;
		
		while(true) {
			while(maxIndex < end) {
				if(numArr[maxIndex] > numArr[pivot]) {
					break;
				}
				maxIndex++;
			}
			while(minIndex > start) {
				if(numArr[minIndex] < numArr[pivot]) {
					break;
				}
				minIndex--;
			}
			if(maxIndex < minIndex) {
				swap(numArr, maxIndex, minIndex);
			}
			else {
				swap(numArr, minIndex, pivot);
				break;
			}
		}
		qSort(numArr, start, minIndex - 1);
		qSort(numArr, minIndex + 1, end);
	}

	private static void swap(int[] numArr, int i, int j) {
		int box = numArr[i];
		numArr[i] = numArr[j];
		numArr[j] = box;
	}
}
