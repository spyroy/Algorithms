package Graph_Related_algorithms;

/* 
 * The complexity is O(n^3)
 * fill helpMat in O(n^2) as before
 * but this time we add vector
 * to have the sum of each row
 * then we go through the matrix and we 
 * use max_subarray on each row O(n)
 * therefore O(n^3)
 * 
 */

public class max_subMatrix_Using_max_subarray {
	int mat[][];
	int helpMat[][];
	int n, m;

	public max_subMatrix_Using_max_subarray(int firstMat[][]) {
		mat = firstMat;
		n = mat.length;
		m = mat[0].length;
		helpMat = new int[n][m + 1]; // a column to sum each row
		fillHelpMatrix();
	}

	public void fillHelpMatrix() {
		for (int i = 0; i < n; i++) {
			for (int j = 1; j < m + 1; j++) {
				helpMat[i][j] = mat[i][j - 1] + helpMat[i][j - 1];
			}
		}
	}

	/**
	 * @return the sum of the maximum sub Matrix
	 */
	public int getMaxMatrix() {
		int maxSum = 0;
		int startI = 0;
		int startJ = 0;
		int endI = 0;
		int endJ = 0;
		int v[] = new int[n];
		max_subarray SuperBest;
		for (int jb = 0; jb < m + 1; jb++) {
			for (int je = jb + 1; je < m + 1; je++) {
				for (int i = 0; i < n; i++) {
					v[i] = helpMat[i][je] - helpMat[i][jb];
				}
				SuperBest = new max_subarray(v);
				SuperBest.getBest();
				if (SuperBest.max > maxSum) {
					maxSum = SuperBest.max;
					startI = SuperBest.beginMax;
					endI = SuperBest.end;
					startJ = jb;
					endJ = je - 1;
				}
			}
		}
		System.out.println("the maxi sum is " + maxSum);
		System.out.println("[i1,j1] -> [" + startI + "," + startJ + "]");
		System.out.println("[i2,j2] -> [" + endI + "," + endJ + "]");
		return maxSum;
	}

	public static void main(String[] args) {
		int[][] mat = { { 0, 2, 4, 8, 5, 6, 58, 4 }, { 0, 3, 6, 8, 4, 5, 9, -4 }, { 5, 2, 5, 6, 9, -7, 5, 1 },
				{ -4, -4, 1, -1, -2, -5, -5, -8 } };
		max_subMatrix_Using_max_subarray m = new max_subMatrix_Using_max_subarray(mat);
		m.getMaxMatrix();
	}
}
