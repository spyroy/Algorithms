package Graph_Related_algorithms;

public class Number_Of_Components {
	/**
	 * @param matAfterFW boolean mat 
	 * @return How many components in the graph
	 */
	public static int HowManyComponents(boolean matAfterFW[][]) {
		int count = 0;
		int dim = matAfterFW.length;
		int comps[] = new int[dim];
		for (int i = 0; i < dim; i++) {
			if (comps[i] == 0)
				count++;
			for (int j = i; j < dim; j++) {
				if (comps[j] == 0 && matAfterFW[i][j])
					comps[j] = count;
			}
		}
		String cs[] = new String[count];
		for (int i = 0; i < count; i++) {
			cs[i] = "";
		}
		for (int i = 0; i < dim; i++)
			cs[comps[i] - 1] = cs[comps[i] - 1] + i + "\t";
		for (int i = 0; i < count; i++)
			System.out.println(cs[i].toString() + "\n");
		return count;
	}
	
	public static void main(String[] args) {
		boolean[][] mat = Path_boolean_matrix.initRibs(1, 2);
		String mat1[][] = Path_boolean_matrix.ConnectPossibleVertex(mat, 2);
		System.out.println("how many components ? \t" + HowManyComponents(mat) + "\n\n");
		Path_boolean_matrix.printMat(mat, 1, 2);
	}
}
