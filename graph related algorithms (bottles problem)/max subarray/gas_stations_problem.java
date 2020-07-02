package Graph_Related_algorithms;

import java.util.Arrays;

import javax.management.BadAttributeValueExpException;

public class gas_stations_problem {
	int[] gasInStation;
	int[] gasNeeded;
	int[] toRun;
	int startIndex;
	int endIndex;
	int count;

	public gas_stations_problem(int[] A, int[] B) throws Exception {
		gasInStation = A;
		gasNeeded = B;
		if (A.length != B.length) {
			throw new Exception();
		}
		for (int i = 0; i < B.length; i++) {
			if (B[i] < 0 || A[i] < 0) {
				throw new Exception();
			}
		}
		int[] ans = new int[A.length];
		for (int i = 0; i < A.length; i++) {
			ans[i] = A[i] - B[i];
		}
		toRun = ans;
	}

	public int getStartIndex() {
		max_subarray_with_cycle max = new max_subarray_with_cycle(toRun);
		max.getCircleBest();
		startIndex = max.beginCircle;
		int sum = 0;
		for (int i = startIndex; i < toRun.length + startIndex; i++) {
			count++;
			sum += gasInStation[i % toRun.length] - gasNeeded[i % toRun.length];
			if (sum < 0) {
				endIndex = (i - 1) % (toRun.length - 1);
				break;
			}
		}
		if (count == 1) {
			endIndex = startIndex;
		}
		if (sum >= 0) {
			endIndex = startIndex;
		}
		return startIndex;
	}

	public void printResult() {
		System.out.println("the Array of stations is:\t\t\t    " + Arrays.toString(gasInStation));
		System.out.println("the Array amount of gas needed between stations is: " + Arrays.toString(gasNeeded));
		System.out.println("= = = = = = = = = = = = = = = = = = = = = =");
		System.out.println("the best position to start is in index: " + startIndex);
		System.out.println("the cycle ends in position: " + endIndex);
		if (startIndex == endIndex) {
			if (count == 1) {
				System.out.println("there is no way to move at all");
			} else {
				System.out.println("there is a way to complete circle!");
			}
		}
		else {
			System.out.println("there is not a way to complete circle :(");
		}
	}

	public static void main(String[] args) {
		int[] A = { 1, 5, 2, 1, 3, 8 };
		int[] B = { 1, 6, 8, 3, 7, 1 };
		try {
			gas_stations_problem g = new gas_stations_problem(A, B);
			int ans = g.getStartIndex();
			System.out.println("==============The answer is: index " + ans + "==============\n\n");
			g.printResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
