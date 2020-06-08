package Graph_Related_algorithms;

import Graph_Related_algorithms.Path_boolean_matrix;

public class Check_Connectivity {
	/**
	 * @param mat the Boolean Matrix 
	 * @return if the graph is connected - return true, else - return false
	 */
	public static boolean isConnectedeGraph(boolean mat[][]) {
		// ConnectPossibleVertex(boolean mat[][],int m)
		int dim = mat.length;
		for (int k = 0; k < dim; k++) {
			for (int i = 0; i < dim; i++) {
				for (int j = 0; j < dim; j++) {
					if (mat[i][j] == false) {
						mat[i][j] = mat[i][k] && mat[k][j];
					}
				}
			}
		}
		for (int i = 0; i < dim; i++) {
			for (int j = 0; j < dim; j++) {
				if (mat[i][j] == false)
					return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		boolean[][] mat = Path_boolean_matrix.initRibs(1, 2);
		Path_boolean_matrix.ConnectPossibleVertex(mat, 2);
		System.out.println("the graph is connected ? \t" + isConnectedeGraph(mat) + "\n\n");
		Path_boolean_matrix.printMat(mat, 1, 2);
	}
}
