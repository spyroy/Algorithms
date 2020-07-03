package Graph_Related_algorithms;

/* 
 * The complexity is O(n^4)
 * fill helpMat in O(n^2)
 * initial columns and rows by:
 * helpMat[i][0] = helpMat[i - 1][0] + mat[i][0];
 * helpMat[0][j] = helpMat[0][j - 1] + mat[0][j];
 * and then fill by Inclusion Exclusion:
 * helpMat[i][j] = mat[i][j] + helpMat[i - 1][j] + helpMat[i][j - 1] - helpMat[i - 1][j - 1];
 * and then the main function with four loops as befor
 * Therefore O(n^2 + O(n^4) =  O(n^4) 
 */

public class max_subMatrix_InclusionExclusion {
	int mat[][];
	int helpMat[][];
	int n, m;

	public max_subMatrix_InclusionExclusion(int matrix[][]) {
		n = matrix.length;
		m = matrix[0].length;
		mat = new int[n][m];
		helpMat = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++)
				mat[i][j] = matrix[i][j];
		fillHelpMat();
	}

	public void fillHelpMat() {
		helpMat[0][0] = mat[0][0];
		for (int i = 1; i < n; i++)
			helpMat[i][0] = helpMat[i - 1][0] + mat[i][0];
		for (int j = 1; j < m; j++)
			helpMat[0][j] = helpMat[0][j - 1] + mat[0][j];
		for (int i = 1; i < n; i++) {
			for (int j = 1; j < m; j++) {
				helpMat[i][j] = mat[i][j] + helpMat[i - 1][j] + helpMat[i][j - 1] - helpMat[i - 1][j - 1];
			}
		}
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
						tempSum = getSubSum(i, j, p, q); // O(1)
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
		System.out.println("From: [i,j] -> [" + theI + "," + theJ + "]");
		System.out.println("To: [p,q] -> [" + theP + "," + theQ + "]");
		System.out.println("the maxSum = " + maxSum);
		return maxSum;
	}

	/**
	 * @param I the start index (rows)
	 * @param J the start index (cols)
	 * @param P the end index (rows)
	 * @param Q the end index (cols)
	 * @return the sum of the sub matrix
	 */
	public int getSubSum(int i, int j, int p, int q) {
		if (i == 0 && j == 0)
			return helpMat[p][q];
		if (i == 0 && j > 0)
			return (helpMat[p][q] - helpMat[p][j - 1]);
		if (i > 0 && j == 0)
			return (helpMat[p][q] - helpMat[i - 1][q]);
		else
			return (helpMat[p][q] - helpMat[i - 1][q] - helpMat[p][j - 1] + helpMat[i - 1][j - 1]);

	}

	public static void main(String[] args) {
		int[][] mat = { { 0, 2, 4, 8, 5, 6, 58, 4 }, { 0, 3, 6, 8, 4, 5, 9, -4 }, { 5, 2, 5, 6, 9, -7, 5, 1 },
				{ -4, -4, 1, -1, -2, -5, -5, -8 } };
		max_subMatrix_InclusionExclusion m = new max_subMatrix_InclusionExclusion(mat);
		m.maxMatrix();
	}
}
