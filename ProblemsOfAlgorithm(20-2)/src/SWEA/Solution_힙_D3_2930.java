package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution_힙_D3_2930 {

	private static int[][] honey;

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("ProblemsOfAlgorithm(20-2)/src/SWEA/Input/input_힙.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int T = Integer.parseInt(br.readLine());

		for (int tc = 1; tc <= T; tc++) {
			sb.append("#").append(tc).append(" ");
			int N = Integer.parseInt(br.readLine());
			PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
			for(int i = 0; i < N; i++) {
				StringTokenizer st = new StringTokenizer(br.readLine());
				int flag = Integer.parseInt(st.nextToken());
				if(flag == 1) pq.offer(Integer.parseInt(st.nextToken()));
				else {
					if(pq.isEmpty()) sb.append(-1).append(" ");
					else sb.append(pq.poll()).append(" ");
				}
			}
			sb.append("\n");
		}
		System.out.print(sb.toString());
		br.close();
	}

}

