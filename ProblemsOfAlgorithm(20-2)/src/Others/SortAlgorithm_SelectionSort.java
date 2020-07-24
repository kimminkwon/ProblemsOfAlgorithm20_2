package Others;

import java.util.*;

class SelctionSort {
	private int[] list;

	public SelctionSort(int[] list) {
		this.list = list;
	}
	
	public int[] getSortedList() {
		selectionSort();
		return list;
	}
	
	private void selectionSort() {
		System.out.println("\n정렬 시작할 리스트: " + Arrays.toString(list) + " ================");
		int minValue, minIndex = 0;
		for(int i = 0; i < list.length; i++) {
			minValue = Integer.MAX_VALUE;
			for(int j = i; j < list.length; j++) {
				if(minValue > list[j]) {
					minValue = list[j];
					minIndex = j;
				}
			}
			System.out.println("list[" + i + "] = " + list[i] + "와 list[" + minIndex + "] = " + list[minIndex] + "를 Swap한다.");
			swap(i, minIndex);
			System.out.println("현재 리스트: " + Arrays.toString(list));
		}
	}

	private void swap(int index1, int index2) {
		int box = list[index1];
		list[index1] = list[index2];
		list[index2] = box;
	}
}

public class SortAlgorithm_SelectionSort {
	public static void main(String[] args) {
		int size = 30;
		int[] testList = new int[size];
		ArrayList<Integer> al = new ArrayList<>();
		for(int i = 1; i <= testList.length; i++) {
			al.add(i);
		}
		Collections.shuffle(al);
		for(int i = 0; i < testList.length; i++) {
			testList[i] = al.get(i);
		}
		SelctionSort selectionSort = new SelctionSort(testList);
		int[] answer = selectionSort.getSortedList();
		System.out.println("\n========= 정렬 결과: " + Arrays.toString(answer) + " =========");
	}

}
