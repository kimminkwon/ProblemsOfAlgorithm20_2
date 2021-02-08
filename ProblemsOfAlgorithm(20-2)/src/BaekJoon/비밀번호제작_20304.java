import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 비밀번호제작_20304 {
	
	private static int maxSecurity = Integer.MIN_VALUE;
	private static class PW {
		int num; int dist;
		public PW(int num, int dist) { this.num = num; this.dist = dist; }
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 
		int N = Integer.parseInt(br.readLine()); 
		int M = Integer.parseInt(br.readLine()); 
		
		Queue<PW> pwOfHacker = new LinkedList<>();
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i = 0; i < M; i++) 
			pwOfHacker.offer(new PW(Integer.parseInt(st.nextToken()), 0));
		
		findMaxSecurity(N, M, pwOfHacker);
		
		System.out.println(maxSecurity);
	}

	private static void findMaxSecurity(int N, int M, Queue<PW> pwOfHacker) {
		int[] visited = new int[N + 1];
		Arrays.fill(visited, -1);
		
		while(!pwOfHacker.isEmpty()) {
			PW p = pwOfHacker.poll();
			maxSecurity = Math.max(maxSecurity, p.dist);
			
			for(int i = 1; i <= N; i = i << 1) {
				if((p.num & i) > 0) {
					if(visited[p.num - i] != -1) continue;
					pwOfHacker.offer(new PW(p.num - i, p.dist + 1));
					visited[p.num - i] = p.dist + 1;
				} else {
					if(p.num + i > N || visited[p.num + i] != -1) continue;
					pwOfHacker.offer(new PW(p.num + i, p.dist + 1));
					visited[p.num + i] = p.dist + 1;
				}
			}
		}
	}
}