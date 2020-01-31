package LIS;

import java.util.Arrays;
/**
 * this class represents the LIS problem
 * we need to find the longest increasing subsequence.
 * @author spyro
 *
 */
public class LongestIncreasingSubsequence {
	/**
	 * finding the length of the longest increasing subsequence
	 * by using side array, we put the first element of the original array
	 * in the side array, then if the next number on the original array 
	 * is bigger than the last element in the side array then we add him 
	 * to the side array.
	 * if the number is smaller then the first element in the side array
	 * we swap between them because a smaller number has a bigger chance 
	 * for longest increasing subsequence.
	 * else we locate the right location between two numbers in the side array
	 * using binary search and swap with the bigger among them.
	 * 
	 * proof in induction of the algorithm:
	 * base - a1 series with one element, lis = 1;
	 * series with size 2, there two cases
	 * 1) if a1<a2 then the sequence will be a1a2 lis = 2
	 * 2) a1>a2 then we swap between them and the subsequence will be a2.
	 * assuming for ai we need to prove for ai+1
	 * if ai+1 is smaller than one of the elements then we swap between them
	 * and the subsequence will remain the same.
	 * if ai+1 is bigger than all elements so the subsequance will be lis+1
	 * and will end with ai+1.
	 * 
	 *complexity: O(nlogn)
	 * @param a
	 * @return
	 */
	public static int LISLength(int a[]) {
		int n = a.length;
		int help[] = new int [n];
		help[0] = a[0];
		int lis = 1;
		for(int i = 1; i < n; i++) {
			int index = binarySearchBetween(help,a[i],lis);
			help[index] = a[i];
			if(index+1 > lis)
				lis++;
		}
		return lis;
	}

	/**
	 * binary search for array
	 * @param help
	 * @param i
	 * @param lis
	 * @return
	 */
	private static int binarySearchBetween(int[] help, int i, int lis) {
		if(i < help[0])
			return 0;// smaller than first need to swap
		if(i > help[lis-1])
			return lis;// bigger than last need to copy
		int start = 0;
		while(start <= lis) {
			int middle = (start+lis)/2;
			if(start == lis)
				return start;
			else {
				if(help[middle] == i)
					return middle;
				if(i < help[middle])
					lis = middle;
				else
					start = middle+1;
			}
		}
		return -1;
	}
	
	/**
	 * returns the longest increasing subsequence
	 * by building side matrix, mat[0][0] = original[0]
	 * then if the next element in original is bigger than all
	 * elements in mat main diagonal we copy the row above and add that element
	 * if the element is smaller than mat[0][0], then we swap
	 * else we will search the right position for the next number in mat main diagonal
	 * using binary search and replace him with the bigger of the two numbers 
	 * he positioned.
	 * the last row (which is not '0') is the longest longest increasing subsequence.
	 * 
	 * proof in induction of the algorithm:
	 * base - a1 series with one element, lis = 1;
	 * series with size 2, there two cases
	 * 1) if a1<a2 then we copy a1 to next row in the matrix and add a2 
	 * the diagonal remained sorted and the subsequence is the last row
	 * 2) a1>a2 then we swap between them and the subsequence will be a2.
	 * assuming for ai we need to prove for ai+1
	 * if ai+1 is between some two numbers in the main diagonal we swap the bigger
	 * with ai+1, the diagonal remained sorted and last row didnt changed
	 * if ai+1 is bigger than all elements in the diagonal
	 * we copy the last row and add ai+1 LIS will increase by 1 and the diagonal 
	 * is still sorted, so the last row is the LIS.
	 * 
	 * example:
	 * 8,4,12,2,14,13
	 * 8 -> 4 -> 4
	 *           4,12 -> 2
	 *                   4,12 -> 2
	 *                           4,12
	 *                           4,12,14 -> 2
	 *                                      4,12
	 *                                      4,12,13
	 * 
	 * complexity: O(n*(n+logn) = O(n^2+ nlogn) = O(n^2)                                     
	 * @param a
	 * @return
	 */
	public static int[] LIS(int [] a) {
		int n = a.length;
		int [][] mat =new int[n][n];
		mat[0][0] =a[0];
		int lis = 0;
		for(int i =1; i < n; i++) {
			int index = binarySearchBetween(mat,a[i],lis);
			for(int j = 0; j<index;j++) {
				mat[index][j] = mat[index-1][j];
			}
			mat[index][index] = a[i];
			if (index>lis)
				lis++;
		}
		int[] ans = new int[lis+1];
		for(int i = 0; i <= lis; i++) {
			ans[i] = mat[lis][i];
		}
		return ans;
	}
	
	
	private static int binarySearchBetween(int[][] mat, int i, int lis) {
		int start = 0;
		int end = lis;
		if(i < mat[0][0])
			return 0;// smaller than first need to swap
		if(i > mat[end][end])
			return end+1;// bigger than last need to copy
		while(start <= end) {
			int middle = (start+end)/2;
			if(start == end)
				return start;
			else {
				if(mat[middle][middle] == i)
					return middle;
				if(i < mat[middle][middle])
					end = middle;
				else
					start = middle+1;
			}
		}
		return -1;
	}
	/**
	 * same as LIS
	 * just decreasing
	 * @param a
	 * @return
	 */
	public static int[] LDS(int[] a) {
		int t[] = new int[a.length];
		for (int i = 0; i < a.length; i++) {
			t[i] = -a[i];
		}
		int []ans = LIS(t);
		for (int i = 0; i < ans.length; i++) {
			ans[i] = -ans[i];
		}
		return ans;
	}


	
	

	public static void main(String[] args) {
		int[] a = {8,4,12,2,14,13};
		System.out.println("LIS length is: " + LISLength(a));
		int[] ans = (LIS(a));
		int [] ans2 = LDS(a);
		System.out.println("and the increasing subsequance is: " + Arrays.toString(ans));
		System.out.println("and the decreasing subsequance is: " + Arrays.toString(ans2));
	}
	


}
