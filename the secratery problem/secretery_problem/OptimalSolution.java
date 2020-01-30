package secretery_problem;

import java.util.Arrays;

/**
 * a secretary need need to serve -n clients
 * and she need to reduce the average waiting time
 * how will she do it?
 * solution:
 * Ti is the time of client - i
 * so the average time is = (T1+T2...+Tn)/n
 * to do so we need to sort from shortest time to longest time.
 * proof:
 * waiting time is = T1+...+Ti-1+Ti+Ti+1...+Tn = t1+...+(t1+...+ti-1)+(t1+...+ti-1+ti)+(t1+...ti)+...+(t1...tn)
 * if we assume that ti > ti+1 (not sorting from short to long)
 * we get = T1+...+Ti-1+Ti'+Ti+1...+Tn = t1+...+(t1+...+ti-1)+(t1+...+ti-1+*ti+1*)+(t1+...+ti-1+*ti+1*+*ti*)+...+(t1...tn)
 * subtruct between two sums we get: ti-ti+1 > 0
 * which means the first sum is bigger therefore its the optimal solution
 * @author spyro
 *
 */
public class OptimalSolution {
	public static double getAverageTime(int[] times) {
		Arrays.parallelSort(times);
		double avg = 0;
		for(int i =0 ;i < times.length; i++) {
			avg += times[i];
			for(int j = 0; j < i; j++)
				avg += times[j];
		}
		return (double)avg/times.length;
	}
	
	public static void main(String[] args) {
		int [] arr = {1,10,8};
		System.out.println(getAverageTime(arr));
	}
	
	
}
