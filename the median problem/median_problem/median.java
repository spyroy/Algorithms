package median_problem;

import java.util.Arrays;
import java.util.Random;

/**
 * 
 * 
 * @author spyro
 *
 */
public class median {

	/**
	 * * given array we need to find a number that is bigger than the median. the
	 * solution is to take the 64 first elements and the biggest among them is 100%
	 * bigger than the median thats because the probability to get it right is
	 * 1-1/(2^64) which is 1.
	 * 
	 * the array must not be sorted!
	 * 
	 * @param arr
	 * @return
	 */
	public static int BiggerThanMedian(int arr[]) {
		int max = arr[0];
		int tmp = 0;
		for (int i = 1; i < arr.length && i < 64 -1; i = i + 2) {
			if ((i + 1) < arr.length && arr[i] > arr[i + 1]) {
				if (max < arr[i])
					max = arr[i];
			} else if ((i + 1) < arr.length && max < arr[i + 1])
				max = arr[i + 1];
			tmp = i;
		}
		if (arr[tmp] > max)
			max = arr[tmp];
		return max;
	}
	
	/**
	 * given two sorted arrays with same size, A[], B[]
	 * we want to find all numbers that bigger than the median of both arrays
	 * to do so we check max(A[0],B[n-1]), first element of A and last element on B.
	 * proof in induction that this algorithm works:
	 * in the first time we choose max(A[0],B[n-1]) there are two cases:
	 * 1) A[0] > B[n-1], since B is sorted by transitive relation A[0]
	 * is bigger than all elements in B therefore all elements in A are bigger than median
	 * 2)A[0] < B[n-1], there are - n elements smaller than B[n-1]: (A[0]<B[0]...<B[n-2])
	 * Therefore B[n-1] is bigger than median
	 * and so on in induction...
	 * 
	 * @param A
	 * @param B
	 * @return
	 */
	public static int[] AllBiggerThanMedian(int[]A, int[]B) {
		if(A.length != B.length)
			return null;// both array should be the same size
		int n = A.length;
		int i = 0;
		int j = n-1;
		int k = n-1;
		int [] C = new int[n];
		C[k--] = Math.max(B[j--], A[i++]);
		while(k >= 0) {
			if(A[i] >= B[j]) {
				for(int l = i; l < A.length; l ++ ) {
					C[k--] = A[l];
					j--;
				}
				break;
			}
			else {
				C[k--] = B[j--];
				i++;
			}
		}
		return C;
	}

	public static void main(String[] args) {
		Random r = new Random();
		int[] arr = new int[137];
		for(int i = 0; i < 1; i++)
			arr[i] = r.nextInt(1000);
		System.out.println(Arrays.toString(arr));
		System.out.println(BiggerThanMedian(arr));
		r = new Random();
		int[] A = new int[8];
		int[] B = new int[8];
		for(int i = 0; i < 8; i++) {
			A[i] = r.nextInt(1000);
			B[i] = r.nextInt(1000);
		}
		Arrays.sort(A);
		Arrays.sort(B);
		int[] C = new int[8];
		System.out.println(Arrays.toString(A) + "\t" + Arrays.toString(B));
		C = AllBiggerThanMedian(A,B);
		System.out.println(Arrays.toString(C));
	}
}
