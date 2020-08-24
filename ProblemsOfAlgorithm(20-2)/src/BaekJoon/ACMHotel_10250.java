package BaekJoon;

import java.util.*;
import java.io.*;

public class ACMHotel_10250 {
	
	static int t; // Num of TestCase
	static long[] h; // 호텔의 층수
	static long[] w; // 호텔의 방수
	static long[] n; // 몇번째 손님
	static String[] results;
	
	public static void main(String[] args) {
		makeInput();
		findHotelRoomNumbers();	
		printResults();
	}

	private static void printResults() {
		for(int i = 0; i < t; i++) {
			System.out.println(results[i]);
		}
		
	}

	private static void findHotelRoomNumbers() {
		for(int i = 0; i < t; i++) {
			findHotelRoomNumber(i, h[i], w[i], n[i]);
		}
	}

	private static void findHotelRoomNumber(int index, long h, long w, long n) {
		long check = n % h;
		long floor = check == 0? h : check;
		long roomNum = check == 0? (n / h) : ((n / h) + 1);
		
		String roomNumber = makeRoomNumber(floor, roomNum);
		results[index] = roomNumber;
	}

	private static String makeRoomNumber(long floor, long roomNum) {
		String floorString = String.valueOf(floor);
		String roomNumString = roomNum >= 10? 
				String.valueOf(roomNum) : "0" + String.valueOf(roomNum);
		String roomNumber = floorString + roomNumString;
		
		return roomNumber;
	}

	private static void makeInput() {
		Scanner input = new Scanner(System.in);
		t = input.nextInt();
		h = new long[t]; w = new long[t]; n = new long[t];
		results = new String[t];
		for(int i = 0; i < t; i++) {
			h[i] = input.nextLong();
			w[i] = input.nextLong();
			n[i] = input.nextLong();
		}
	}

}
