package BaekJoon;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

public class 요세푸스문제_1158 {
	
	private static int N, K;
	private static List<Integer> result = new ArrayList<>();
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		makeYosepus();
		printResult();
	}

	private static void printResult() {
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		result.stream().forEach(i -> sb.append(i + ", "));
		sb.setLength(sb.length() - 2);
		sb.append(">");
		System.out.println(sb.toString());
	}

	private static void makeYosepus() {
		Queue<Integer> q = new LinkedList<>();
		IntStream.range(1, N + 1).forEach(i -> q.add(i));
		
		while(!q.isEmpty()) {
			for(int i = 1; i < K; i++) q.offer(q.poll());
			result.add(q.poll());
		}
	}

}
