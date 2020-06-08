package Graph_Related_algorithms;

/**
 * The purpose of the program: return a matrix that represents a connection
 * between vertex
 */
public class Init {
	/**
	 * @param i Value of first bottle
	 * @param j Value of second bottle
	 * @param n The size of the matrix
	 * @return the position in the matrix corresponds to the value of two bottles
	 */
	public static int getIndex(int i, int j, int n) {
		return (n + 1) * i + j;
	}

	/**
	 * this function creates matrix that represents the edges between vertices,
	 * every vertex which represents the two bottles have maximum - 6 possibilities
	 * 
	 * @param n The maximum value of the first bottle
	 * @param m The maximum value of the second bottle
	 * @return Matrix of zero and one, 0 - no connection, 1 - there is a connection
	 */
	public static int[][] initRibs(int n, int m) {
		System.out.println("The maximum value of the first bottle(n) = " + n);
		System.out.println("The maximum value of the second bottle(m) = " + m);
		System.out.println("********************[" + n + "," + m + "]********************");
		System.out.println("*********************************************\n");
		// number of vertices in the graph
		int dim = (n + 1) * (m + 1);
		int mat[][] = new int[dim][dim];
		int ind1, ind2;
		// we run to i\j <= n\m to adjust it to
		// the size of the matrix
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
		// the path from vertex to itself is - 0
		for (int t = 0; t < dim; t++)
			mat[t][t] = 0;
		return mat;
	}

	// print matrix
	public static void printMat(int mat[][], int n, int m) {
		int stop = 0;
		String arr[] = new String[2 * n * m + 2];
		int go = 0;
		while (stop <= n) {
			for (int j = 0; j <= m; j++) {
				arr[go] = "[" + stop + "," + j + "]\t";
				go++;
				System.out.print("\t[" + stop + "," + j + "]");
			}
			stop++;
			if (stop > n)
				break;
			for (int j = 0; j <= m; j++) {
				System.out.print("\t[" + stop + "," + j + "]");
				arr[go] = "[" + stop + "," + j + "]\t";
				go++;
			}
			stop++;
		}
		System.out.println("\n");
		for (int i = 0; i < mat.length; i++) {
			System.out.print(arr[i]);
			for (int j = 0; j < mat[0].length; j++) {
				System.out.print(" " + mat[i][j] + "\t");
			}
			System.out.println("\n");
		}
	}
	
	public static void main(String[] args) {
		int[][] mat = initRibs(1,2);
		printMat(mat,1,2);
	}
}
