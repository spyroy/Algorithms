package Graph_Related_algorithms;

import Graph_Related_algorithms.Path_boolean_matrix;

public class Is_Directed_Graph {
	/**
	 * @param mat the Boolean Matrix
	 * @return true if the Graph is not Directed,else return false
	 */

	public static boolean isDirectedGraph(boolean mat[][]) {
		int n = mat.length;
		int m = mat[0].length;
		for (int i = 0; i < n; i++) {
			for (int j = i + 1; j < m; j++) {
				if (mat[i][j] != mat[j][i])
					return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) {
		boolean[][] mat = Path_boolean_matrix.initRibs(1, 2);
		Path_boolean_matrix.ConnectPossibleVertex(mat, 2);
		System.out.println("the graph is directed ? \t" + isDirectedGraph(mat) + "\n\n");
		Path_boolean_matrix.printMat(mat, 1, 2);
	}
}
