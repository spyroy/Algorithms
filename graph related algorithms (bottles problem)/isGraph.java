package Graph_Related_algorithms;

import java.util.Arrays;

/*
 * complexity O(log n * n^2)
 */

public class isGraph {
	int[] degrees;
	public isGraph(int [] degrees) {
		this.degrees = new int [degrees.length];
		for(int i = 0; i < degrees.length; i++) {
			this.degrees[i] = degrees[i];
		}
	}
	public boolean isItGraph() {
		int n = degrees.length;
		int sum = 0;
		for(int i = 0; i < n; i ++) {
			sum += degrees[i];
		}
		if(sum%2 != 0) {
			return false;
		}
		Arrays.sort(degrees);
		for(int i = n-1; i>= 0; i--) {
			if(degrees[i] != 0) {
				int d = degrees[i];
				if(i-d < 0) {
					return false;
				}
				degrees[i] = 0;
				for(int j = i-1; j >= i-d;j--) {
					if(degrees[j] == 0) {
						return false;
					}
					degrees[j]--;
				}
				Arrays.sort(degrees,0,i-1);
			}
		}
		return true;
	}

}
