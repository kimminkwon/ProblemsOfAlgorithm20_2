package BaekJoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

class Top {
	private int index;
	private long height;
	
	public Top(int index, long height) { 
		this.index = index; this.height = height; 
	}
	
	public int getIndex() { return index; }
	public long getHeight() { return height; }
}

public class 탑_2493 {
	
	private static int N;
	private static Stack<Top> tops = new Stack<>();
	private static Stack<Top> buffer = new Stack<>();
	private static int[] listenTopNum;
	
	public static void main(String[] args) throws IOException {
		makeInput();
		findListenSignalTop();
		printResult();
	}

	private static void findListenSignalTop() {
		while(!tops.isEmpty()) {
			if(buffer.isEmpty()) buffer.push(tops.pop()); 
			else if(tops.peek().getHeight() < buffer.peek().getHeight()) buffer.push(tops.pop());
			else listenTopNum[buffer.pop().getIndex()] = tops.peek().getIndex();
		}
	}

	private static void makeInput() throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		listenTopNum = new int[N + 1];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 1; i <= N; i++) tops.push(new Top(i, Long.parseLong(st.nextToken())));
	}

	private static void printResult() {
		StringBuilder sb = new StringBuilder();
		for(int i = 1; i <= N; i++) sb.append(listenTopNum[i] + " ");
		System.out.println(sb.toString());
	}
}
