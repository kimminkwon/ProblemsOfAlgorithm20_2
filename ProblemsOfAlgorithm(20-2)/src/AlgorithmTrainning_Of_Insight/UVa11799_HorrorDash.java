/*
 * 11799 - Horror Dash
 * is a Ad-hok Problem
 * As part of the festival, there is a unique event known as the “Horror Dash”. 
 * At this event, N (1 ≤ N ≤ 100) students dressed in the scariest costumes possible start out in a race to catch a poor clown running on the same track. 
 * To keep the event running for as long as possible, the clown must run fast enough not to be caught by any of the scary creatures. 
 * However, to keep the audience on the edge of their seats, the clown must not run too fast either. 
 * This is where you are to help. 
 * Given the speed of every scary creature, you are to find out the minimum speed that the clown must maintain so as not to get caught even if they keep on running forever.
 */

package AlgorithmTrainning_Of_Insight;

import java.util.*;

/*
Sample Input Data
2
5 9 3 5 2 6
1 2
 */

public class UVa11799_HorrorDash {
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		
		for (int k = 0; k < n; k++) {
			int numberOfKid = s.nextInt();
			int maxSpeed = -1;
			for (int i = 0; i < numberOfKid; i++) {
				int speedOfkid = s.nextInt();
				if (maxSpeed < speedOfkid)
					maxSpeed = speedOfkid;
			}
			System.out.printf("Case %d: %d\n", k+1, maxSpeed);
		}
	}
}
