package Others;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

class CountingSort {
	private int[] list;
	private int[] cnt;

	public CountingSort(int[] list) {
		this.list = list;
		int max = Integer.MIN_VALUE;
		for(int i = 0; i < list.length; i++) {
			if(list[i] > max)
				max = list[i];
		}
		cnt = new int[max+1]; // 값의 범위가 1 <= N <= max이므로
	}

	public int[] getSortedList() {
		System.out.println("정렬 전 리스트: " + Arrays.toString(list));
		countingSort();
		return list;
	}

	private void countingSort() {
		for(int i = 0; i < list.length; i++)
			cnt[list[i]]++;
		
		System.out.println("cnt: " + Arrays.toString(cnt));
		int flag = 0;
		for(int i = 0; i < cnt.length; i++) {
			if(cnt[i] != 0) {
				for(int j = 0; j < cnt[i]; j++) {
					list[flag] = i;
					flag++;
				}
			}
		}
	}
}
public class SortAlgorithm_CountingSort {
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
		CountingSort countingSort = new CountingSort(testList);
		int[] answer = countingSort.getSortedList();

		System.out.println("\n========= 정렬 결과: " + Arrays.toString(answer) + " =========");
	}

}
