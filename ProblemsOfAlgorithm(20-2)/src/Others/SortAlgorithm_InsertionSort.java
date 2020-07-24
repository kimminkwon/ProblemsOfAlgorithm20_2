package Others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class InsertionSort {
	private int[] list;

	public InsertionSort(int[] list) {
		this.list = list;
	}
	
	public int[] getSortedList() {
		insertionSort();
		return list;
	}
	
	private void insertionSort() {
		System.out.println("\n정렬 시작할 리스트: " + Arrays.toString(list) + " ================");
		int flag = 0;
		for(int i = 1; i < list.length; i++) {
			flag = i;
			while(list[flag - 1] > list[flag]) {
				System.out.println("list[" + (flag - 1) + "] = " + list[flag - 1] + "와 list[" + flag + "] = " + list[flag] + "를 Swap한다.");
				swap(flag - 1, flag);
				System.out.println("현재 리스트: " + Arrays.toString(list));
				if(flag == 1) break;
				flag--;
			}
		}
		
	}
	
	private void swap(int index1, int index2) {
		int box = list[index1];
		list[index1] = list[index2];
		list[index2] = box;
	}
}

public class SortAlgorithm_InsertionSort {
	public static void main(String[] args) {
		int size = 10;
		int[] testList = new int[size];
		ArrayList<Integer> al = new ArrayList<>();
		for(int i = 1; i <= testList.length; i++) {
			al.add(i);
		}
		Collections.shuffle(al);
		for(int i = 0; i < testList.length; i++) {
			testList[i] = al.get(i);
		}
		InsertionSort insertionSort = new InsertionSort(testList);
		int[] answer = insertionSort.getSortedList();
		System.out.println("\n========= 정렬 결과: " + Arrays.toString(answer) + " =========");

	}

}
