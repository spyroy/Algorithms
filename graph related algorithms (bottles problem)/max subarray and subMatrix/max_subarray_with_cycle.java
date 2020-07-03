package Graph_Related_algorithms;

import java.util.Arrays;

public class max_subarray_with_cycle {
	int maxCircle;
	int sumAll;
	int[] plusArr;
	int[] minusArr;
	int beginCircle;
	int endCircle;
	int sizeOfBest;

	public max_subarray_with_cycle(int arr[]) {
		int size = arr.length;
		maxCircle = arr[0];
		sumAll = 0;
		plusArr = new int[size];
		minusArr = new int[size];
		beginCircle = 0;
		endCircle = 0;
		sizeOfBest = 0;
		for (int i = 0; i < size; i++) {
			plusArr[i] = arr[i];
			minusArr[i] = (-1) * arr[i];
			sumAll = sumAll + arr[i];
		}
	}

	/**
	 * find the Best sub Array in circle Array
	 */
	public int getCircleBest() {
		max_subarray positive = new max_subarray(plusArr);
		int maxPositive = positive.getBest();
		if (maxPositive < 0) {
			maxCircle = maxPositive;
			beginCircle = positive.beginMax;
			endCircle = positive.end;
			sizeOfBest = 1;
			return maxCircle;
		}
		max_subarray negative = new max_subarray(minusArr);
		int maxNegative = negative.getBest();
		if (maxPositive >= sumAll + maxNegative) {
			maxCircle = maxPositive;
			beginCircle = positive.beginMax;
			endCircle = positive.end;
			sizeOfBest = endCircle - beginCircle + 1;

		} else {
			maxCircle = sumAll + maxNegative;
			beginCircle = negative.end + 1;
			endCircle = negative.beginMax - 1;
			sizeOfBest = plusArr.length - beginCircle + endCircle + 1;
		}
		return maxCircle;

	}

	public void printResult() {
		System.out.println("the Array is: " + Arrays.toString(plusArr));
		System.out.println("= = = = = = = = = = = = = = = = = = = = = =");
		System.out.println("the start index is: " + beginCircle);
		System.out.println("the end index is: " + endCircle);
		System.out.print(" the max sub Array is: [\t");
		for (int i = 0; i < sizeOfBest; i++) {
			System.out.print(plusArr[(i + beginCircle) % plusArr.length] + "\t");
		}
		System.out.println("]");
		System.out.println("the max sum is: " + maxCircle);
		System.out.println("the number of element is: " + sizeOfBest);
		System.out.println("============================================");
	}

	public static void main(String[] args) {
		int[] arr = { 0,-1,-6,4,-4,7 };
		max_subarray_with_cycle max = new max_subarray_with_cycle(arr);
		int ans = max.getCircleBest();
		System.out.println("==============The answer is:" + ans + "==============\n\n");
		max.printResult();
	}
}