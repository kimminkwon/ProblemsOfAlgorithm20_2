import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 암호생성기_D3_1225 {
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_암호생성기.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        for(int i = 1; i <= T; i++) {
        	Queue<Integer> queue = new LinkedList<>();
        	int tc = Integer.parseInt(br.readLine());
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	for(int j = 0; j < 8 ; j++)
        		queue.add(Integer.parseInt(st.nextToken()));
        	System.out.println("#" + tc + " " + makePassword(queue));
        }	
	}

	private static String makePassword(Queue<Integer> queue) {
		int minus = 1;
		while(true) {
			int currNum = queue.poll() - minus++;
			if(minus > 5) minus = 1;
			if(currNum <= 0) {
				queue.offer(0);
				break;
			} else queue.offer(currNum);
		}
		
		return queueToString(queue);
	}

	private static String queueToString(Queue<Integer> queue) {
		StringBuilder sb = new StringBuilder();
		while(!queue.isEmpty())
			sb.append(queue.poll() + " ");
		return sb.toString();
	}
}
