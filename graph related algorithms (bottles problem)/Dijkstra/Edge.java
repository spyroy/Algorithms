package Graph_Related_algorithms;

/** the Class representing edge between Bone vertex to another vertex */
public class Edge {
	int vertex;
	double weight;

	/**
	 * @param v the name of the another vertex
	 * @param w the Weight of the edge
	 */
	public Edge(int v, double w) {
		vertex = v;
		weight = w;
	}
}