package Graph_Related_algorithms;


public class Vertex implements Comparable<Vertex> {

	int name;
	int prev;
	Edge edge[];
	double dist;
	boolean visit;
	public static final Double Nsof = Double.POSITIVE_INFINITY;
	public static final int Nil = -1;

// visit = false;
	public Vertex(int name) {
		this.name = name;
		dist = Nsof;
		visit = false;
		prev = -1;
	}

	public int compareTo(Vertex v) {
		if (dist > v.dist)
			return 1;
		if (dist < v.dist)
			return Nil;
		return 0;
	}
}