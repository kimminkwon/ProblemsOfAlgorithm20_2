package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Solution_수의새로운연산_D3_1493 {
	
	private static class Coor {
		private int x, y;
		public Coor(int x, int y) { this.x = x; this.y = y; }
	}
	
	private static List<Coor> coorList = new ArrayList<>();
	private static Map<String, Integer> coorHash = new HashMap<>();
	
	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("input_수의새로운연산.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        makeCoorList();
        for(int tc = 1; tc <= T; tc++) {
        	StringTokenizer st = new StringTokenizer(br.readLine());
        	int p = Integer.parseInt(st.nextToken());
        	int q = Integer.parseInt(st.nextToken());

        	sb.append("#").append(tc).append(" ").append(newOperation(p, q)).append("\n");
        }
        System.out.print(sb.toString());
        br.close();
	}

	private static int newOperation(int p, int q) {
		Coor c1 = coorList.get(p - 1);
		Coor c2 = coorList.get(q - 1);
		Coor cMain = new Coor(c1.x + c2.x, c1.y + c2.y);
		
		return coorHash.get(cMain.x + "/" + cMain.y);
	}
	
	private static void makeCoorList() {
		for(int i = 1; i <= 300; i++) {
			Coor c = new Coor(1, i);
			makeLowerCoor(c, i);
		}

		for(int i = 2; i <= 300; i++) {
			Coor c = new Coor(i, 300);
			makeUpperCoor(c, 300 - i + 1);
		}
	}

	private static void makeUpperCoor(Coor c, int cnt) {
		for(int i = 0; i < cnt; i++) {
			coorList.add(new Coor(c.x, c.y));
			coorHash.put(String.valueOf(c.x + "/" + c.y), coorList.size());
			c.x = c.x + 1;
			c.y = c.y - 1;
		}
	}

	private static void makeLowerCoor(Coor c, int cnt) {
		for(int i = 0; i < cnt; i++) {
			coorList.add(new Coor(c.x, c.y));
			coorHash.put(String.valueOf(c.x + "/" + c.y), coorList.size());
			c.x = c.x + 1;
			c.y = c.y - 1;
		}
	}
}
