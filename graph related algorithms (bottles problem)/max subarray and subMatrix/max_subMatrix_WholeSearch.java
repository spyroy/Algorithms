package Graph_Related_algorithms;

/* 
 * The complexity is O(n^6)
 * we have the begin index in matrix
 * [i,j] and end index [p,q]
 * Therefore four loops but we need to 
 * sum the sub matrix so add two loops
 * hence - 6 loops.
 */

public class max_subMatrix_WholeSearch {
	int mat[][];
	int m, n;

	public max_subMatrix_WholeSearch(int matrix[][]) {
		n = matrix.length;
		m = matrix[0].length;
		mat = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				mat[i][j] = matrix[i][j];

	}

	/**
	 * @return the sum of the maximum sub Matrix
	 */
	public int maxMatrix() {
		int maxSum = 0;
		int tempSum = 0;
		int theI = 0;
		int theJ = 0;
		int theP = 0;
		int theQ = 0;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				for (int p = i; p < n; p++) {
					for (int q = j; q < m; q++) {
						tempSum = subSum(i, j, p, q);
						if (tempSum > maxSum) {
							maxSum = tempSum;
							theI = i;
							theJ = j;
							theP = p;
							theQ = q;
						}
					}
				}
			}
		}
		System.out.println("the max sum is: " + maxSum);
		System.out.println(" FROM: [i,j] -> [" + theI + "," + theJ + "]");
		System.out.println(" TO: [p,q] -> [" + theP + "," + theQ + "]");
		return maxSum;
	}

	/**
	 * @param I the start index (rows)
	 * @param J the start index (cols)
	 * @param P the end index (rows)
	 * @param Q the end index (cols)
	 * @return the sum of the sub matrix
	 */
	public int subSum(int I, int J, int P, int Q) {
		int sum = 0;
		for (int i = I; i <= P; i++) {
			for (int j = J; j <= Q; j++) {
				sum = sum + mat[i][j];
			}
		}
		return sum;
	}
	
	public static void main(String[] args) {
		int [][] mat = {{0 , 2,4, 8, 5, 6,58, 4},
						{0 , 3,6, 8, 4, 5, 9, -4},
						{5 , 2,5 ,6, 9,-7, 5, 1},
						{-4,-4,1,-1,-2,-5,-5,-8}};
		max_subMatrix_WholeSearch m = new max_subMatrix_WholeSearch(mat);
		m.maxMatrix();
	}
}
