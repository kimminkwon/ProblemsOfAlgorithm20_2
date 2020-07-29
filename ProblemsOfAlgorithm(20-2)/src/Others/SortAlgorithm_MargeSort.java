package Others;

import java.util.*;

class MargeSort {
	private int[] list;
	private int[] buffer;

	public MargeSort(int[] list) {
		this.list = list;
		buffer = new int[this.list.length];
	}

	public int[] getSortedList() {
		margeSort(0, list.length - 1);
		return list;
	}

	private void margeSort(int start, int end) {
		if (start < end) {
			int middle = (start + end) / 2;
			margeSort(start, middle);
			margeSort(middle + 1, end);
			marge(start, middle, end);
		}
	}

	private void marge(int start, int middle, int end) {
		int arr1Start = start;
		int arr1End = middle;
		int arr2Start = middle + 1;
		int arr2End = end;
		int flag = arr1Start;

		while (arr1Start <= arr1End && arr2Start <= arr2End) {
			if (list[arr1Start] > list[arr2Start]) {
				buffer[flag] = list[arr2Start];
				arr2Start++;
			} else {
				buffer[flag] = list[arr1Start];
				arr1Start++;
			}
			flag++;
		}

		if (arr1Start <= arr1End) {
			while (arr1Start <= arr1End) {
				buffer[flag] = list[arr1Start];
				flag++;
				arr1Start++;
			}
		} else {
			while (arr2Start <= arr2End) {
				buffer[flag] = list[arr2Start];
				flag++;
				arr2Start++;
			}
		}

		for (int i = start; i <= end; i++)
			list[i] = buffer[i];
	}

}

public class SortAlgorithm_MargeSort {
	public static void main(String[] args) {
		int size = 20;
		int[] testList = new int[size];
		ArrayList<Integer> al = new ArrayList<>();
		for (int i = 1; i <= testList.length; i++) {
			al.add(i);
		}
		Collections.shuffle(al);
		for (int i = 0; i < testList.length; i++) {
			testList[i] = al.get(i);
		}
		MargeSort margeSort = new MargeSort(testList);
		int[] answer = margeSort.getSortedList();

		System.out.println("\n========= 정렬 결과: " + Arrays.toString(answer) + " =========");
	}

}
