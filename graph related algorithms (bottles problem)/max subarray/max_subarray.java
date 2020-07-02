package Graph_Related_algorithms;

import java.util.Arrays;

public class max_subarray {
	int max;
	int beginMax;
	int end;
	int arr[];

	public max_subarray(int Array[]) {
		arr = Array;
		max = arr[0];
		beginMax = 0;
		end = 0;
	}

	/**
	 * 
	 * function find the best Sub-section
	 * 
	 * @return the sum of the best Sub-section
	 */
	public int getBest() {
		int i = 0;
		int tempSum, tempBegin;
		while (arr[i] <= 0) {
			i++;
			if (i == arr.length)
				return max;
			if (arr[i] > max) {
				max=arr[i];
				beginMax = i;
				end = i;
			}
		}
		tempSum = 0;
		tempBegin = beginMax;
		while (i < arr.length) {
			tempSum = tempSum + arr[i];
			if (tempSum < 0) {
				tempSum = 0;
				tempBegin = i + 1;
			} else if (tempSum >= max) {
				max = tempSum;
				beginMax = tempBegin;
				end = i;
			}
			i++;
		}
		return max;
	}

	/**
	 * this function help to print the result of Best
	 */
	public void printResult() {
		System.out.println("the Array is: " + Arrays.toString(arr));
		System.out.println("= = = = = = = = = = = = = = = = = = = = = =");
		System.out.println("the start index is: " + beginMax);
		System.out.println("the end index is: " + end);
		System.out.print(" the max sub Array is: [\t");
		for (int i = beginMax; i <= end; i++) {
			System.out.print(arr[i] + "\t");
		}
		System.out.println("]");
		System.out.println("the max sum is: " + max);
		System.out.println("============================================");
	}
	
	public static void main(String[] args) {
		int [] arr = {-10,8,-1,8,-9,1,-1,-9,1,-9};
		max_subarray max = new max_subarray(arr);
		int ans = max.getBest();
		System.out.println("==============The answer is:" + ans + "==============\n\n");
		max.printResult();
	}
}