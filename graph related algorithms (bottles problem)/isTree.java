package Graph_Related_algorithms;

public class isTree {
	int[] degrees;
	public isTree(int[] degrees) {
		this.degrees = degrees;
	}
	public boolean isItTree() {
		int sum = 0;
		for(int i = 0; i < degrees.length; i++) {
			if(degrees[i] == 0) {
				return false;
			}
			sum += degrees[i];
		}
		if(sum != 2*degrees.length -2) {
			return false;
		}
		isGraph g = new isGraph(degrees);
		return g.isItGraph();
	}

}
