package Graph_Related_algorithms;

import java.util.ArrayList;

public class FireTree {
	ArrayList<Integer> tree[];
	int n;
	int[] degree;
	int radius;
	int diameter;

	public FireTree(ArrayList<Integer> AL[]) {
		tree = AL;
		n = AL.length;
		degree = new int[n];
		radius = 0;
		diameter = 0;
	}

	/**
	 * 
	 * @return the Diameter of the tree
	 */
	public int findDiameter() {
		ArrayList<Integer> leaves = new ArrayList<Integer>();
		for (int i = 0; i < n; i++) {
			degree[i] = tree[i].size();
			if (degree[i] == 1)
				leaves.add(i);
		}
		int leaf = 0;
		int vertex = 0;
		while (n > 2) {
			ArrayList<Integer> futureLeaves = new ArrayList<Integer>();
			for (int i = 0; i < leaves.size(); i++) {
				leaf = leaves.get(i);
				degree[leaf] = 0;
				for (int j = 0; j < tree[leaf].size(); j++) {
					vertex = tree[leaf].get(j);
					if (degree[vertex] > 0) {
						degree[vertex]--;
						if (degree[vertex] == 1)
							futureLeaves.add(vertex);
					}
				}
				n--;
			}
			radius++;
			leaves = futureLeaves;
		}
		if (leaves.size() == 2) {
			radius++;
			diameter = 2 * radius - 1;
		} else
			diameter = 2 * radius;
		System.out
				.println("the radius is: " + radius + ", the diameter is: " + diameter + ", and center is: " + leaves);
		return diameter;
	}
	
	public static void main(String[] args) {
		ArrayList<Integer> al[] = new ArrayList[10];
		for (int i = 0; i < 10; i++) { 
            al[i] = new ArrayList<Integer>(); 
        }
		al[0].add(1); 
        al[0].add(2); 
        al[1].add(5); 
        al[1].add(6);
        al[1].add(0);
        al[2].add(3); 
        al[2].add(4); 
        al[2].add(0);
        al[3].add(7); 
        al[3].add(2);
        al[4].add(9); 
        al[4].add(8); 
        al[4].add(2);
        al[5].add(1);
        al[6].add(1);
        al[7].add(3);
        al[8].add(4);
        al[9].add(4);
        
        FireTree ft = new FireTree(al);
        ft.findDiameter();
        
        /*
                                                     0
                                                    / \
                                                   /   \
                                                  /     \
                                                 /       \ 
                                                /         \
                                               /           \      
                                              1             2
                                             / \           / \
                                            /   \         /   \
                                           /     \       3     4
                                          5       6     /     / \
                                                       /     /   \
                                                      /     9     8
                                                     7
         */
	}
	
	
}
