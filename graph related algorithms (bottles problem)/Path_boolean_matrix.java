package Graph_Related_algorithms;

import java.util.Arrays;

public class Path_boolean_matrix {
	/**
	* @param mat the Boolean Matrix we get after FW
	* @param m the size of one Bottle
	* @return String Matrix with all tracks
	*/
	public static String[][] ConnectPossibleVertex(boolean mat[][], int m) {
		int dim = mat.length;
		int a0 = 0, b0 = 0, a1 = 0, b1 = 0;
		String path[][] = new String[dim][dim];
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				a0 = i / (m + 1);
				b0 = i % (m + 1);
				a1 = j / (m + 1);
				b1 = j % (m + 1);
				if (mat[i][j] == true)
					path[i][j] =

							"[" + a0 + "," + b0 + "]-D->[" + a1 + "," + b1 + "]";

				else
					path[i][j] = new String();
			}
		}
		for (int k = 0; k < dim; k++) {
			for (int i = 0; i < dim; i++) {
				for (int j = 0; j < dim; j++) {
					if (mat[i][j] == false) {
						mat[i][j] = mat[i][k] && mat[k][j];
						if (mat[i][j] == true) {
							path[i][j] = path[i][k] + " >-i-> " + path[k][j];

						}
					}
				}
			}
		}
		return path;
	}
	
	public static int getIndex(int i, int j, int n) {
		return (n + 1) * i + j;
	}
	
	// changed to boolean
	public static boolean[][] initRibs(int n, int m) {
		System.out.println("The maximum value of the first bottle(n) = " + n);
		System.out.println("The maximum value of the second bottle(m) = " + m);
		System.out.println("********************[" + n + "," + m + "]********************");
		System.out.println("*********************************************\n");
		// number of vertices in the graph
		int dim = (n + 1) * (m + 1);
		boolean mat[][] = new boolean [dim][dim];
		int ind1, ind2;
		// we run to i\j <= n\m to adjust it to
		// the size of the matrix
		for (int i = 0; i <= n; i++) {
			for (int j = 0; j <= m; j++) {
				ind1 = getIndex(i, j, m);
				mat[ind1][getIndex(0, j, m)] = true; // Rib 1
				mat[ind1][getIndex(n, j, m)] = true; // Rib 2
				mat[ind1][getIndex(i, 0, m)] = true; // Rib 3
				mat[ind1][getIndex(i, m, m)] = true; // Rib 4

				ind2 = getIndex(Math.min(i + j, n), i + j - Math.min(i + j, n), m);
				mat[ind1][ind2] = true; // Rib 5

				ind2 = getIndex(i + j - Math.min(i + j, m), Math.min(i + j, m), m);
				mat[ind1][ind2] = true; // Rib 6
			}
		}
		// the path from vertex to itself is - 0
		for (int t = 0; t < dim; t++)
			mat[t][t] = false;
		return mat;
	}
	
	// print matrix changed to boolean
		public static void printMat(boolean mat[][], int n, int m) {
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
		boolean[][] mat = initRibs(1, 2);
		String[][] mat2 = ConnectPossibleVertex(mat,2); 
        for (String[] row : mat2)  
            System.out.println(Arrays.toString(row)); 
		printMat(mat, 1, 2);
		
	}
}
