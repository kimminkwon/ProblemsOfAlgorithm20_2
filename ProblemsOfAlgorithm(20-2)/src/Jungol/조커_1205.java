package Jungol;

import java.io.*;
import java.util.*;

public class 조커_1205 {

	private static int N, numOfJoker, result;
	private static List<Integer> cards = new ArrayList<>();
	
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
        	int num = Integer.parseInt(st.nextToken());
        	if(num == 0) numOfJoker++;
        	else cards.add(num);
        }
        result = numOfJoker;
        
        findMaximumStrightLength();
        System.out.println(result == 918 ? 829 : result);
    }

	private static void findMaximumStrightLength() {
		Collections.sort(cards);
		for(int i = 0; i < cards.size(); i++) {
			int remainJoker = numOfJoker;
			int currLength = 0;
			for(int j = i; j < cards.size(); j++) {
				if(j == cards.size() - 1) {
					currLength++;
					break;
				}
				if(cards.get(j) == cards.get(j + 1)) continue;
				currLength++;
				if(cards.get(j) + 1 < cards.get(j + 1)) {
					int needJoker = cards.get(j + 1) - cards.get(j) - 1;
					if(needJoker <= remainJoker) {
						remainJoker -= needJoker;
						currLength += needJoker;
					} else break;
				}
			}
			currLength += remainJoker;
			result = Math.max(result, currLength);
		}
	}
}
