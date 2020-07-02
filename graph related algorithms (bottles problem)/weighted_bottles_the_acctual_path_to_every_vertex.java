package Graph_Related_algorithms;

public class weighted_bottles_the_acctual_path_to_every_vertex {
	public static final int max = Integer.MAX_VALUE;

	/**
	 * 
	 * @param i Value of first bottle
	 * @param j Value of second bottle
	 * @param n The size of the matrix
	 * @return the position in the matrix corresponds to the value of two bottles
	 */
	public static int getIndex(int i, int j, int n) {
		return (n + 1) * i + j;
	}

	/**
	 * @param n The maximum value of the first bottle
	 * @param m The maximum value of the second bottle
	 * @return Integer Matrix: MAX_VALUE- no connection, number- the edges for
	 *         connection
	 */
	public static int[][] initRibs(int n, int m) {
		System.out.println("The maximum value of the first bottle(n) = " + n);
		System.out.println("The maximum value of the second bottle(m) = " + m);
		System.out.println("********************[" + n + "," + m + "]********************");
		System.out.println("*********************************************\n");
		int dim = (n + 1) * (m + 1);
		int mat[][] = new int[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				mat[i][j] = max;
			}
		}
		int ind1, ind2;
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				ind1 = getIndex(i, j, m);
				mat[ind1][getIndex(0, j, m)] = 1; // Rib 1
				mat[ind1][getIndex(n, j, m)] = 1; // Rib 2
				mat[ind1][getIndex(i, 0, m)] = 1; // Rib 3
				mat[ind1][getIndex(i, m, m)] = 1; // Rib 4
				ind2 = getIndex(Math.min(i + j, n), i + j - Math.min(i + j, n), m);
				mat[ind1][ind2] = 1; // Rib 5
				ind2 = getIndex(i + j - Math.min(i + j, m), Math.min(i + j, m), m);
				mat[ind1][ind2] = 1; // Rib 6
			}
		}
		for (int t = 0; t < dim; t++)
			mat[t][t] = 0;
		return mat;
	}

	/**
	 * @param mat the Matrix after init
	 * @param n   the size of one Bottle
	 * @return String Matrix with all the cheapest tracks
	 */
	public static String[][] ConnectPossibleVertex(int mat[][], int n) {
		int dim = mat.length;
		int a0 = 0, b0 = 0, a1 = 0, b1 = 0;
		String path[][] = new String[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				a0 = i / (n + 1);
				b0 = i % (n + 1);
				a1 = j / (n + 1);
				b1 = j % (n + 1);
				if (mat[i][j] != max)
					path[i][j] =

							"[" + a0 + "," + b0 + "] -> [" + a1 + "," + b1 + "]";

				else
					path[i][j] = new String();
			}
		}
		for (int k = 0; k < dim; k++) {
			for (int i = 0; i < dim; i++) {
				for (int j = 0; j < dim; j++) {

					if (mat[i][k] != max && mat[k][j] != max) {
						if (mat[i][j] > mat[i][k] + mat[k][j]) {
							mat[i][j] = mat[i][k] + mat[k][j];
							path[i][j] = path[i][k] + ">>>" + path[k][j];
						}
					}
				}
			}
		}
		return path;
	}
	public static void main(String[] args) {
		int [][] mat = initRibs(1, 2);
		String [][] ans = ConnectPossibleVertex(mat, 2);
		for(int i = 0; i < ans.length; i++) {
			for(int j = 0; j < ans[0].length; j++) {
				System.out.println(ans[i][j]);
			}
		}
	}
}