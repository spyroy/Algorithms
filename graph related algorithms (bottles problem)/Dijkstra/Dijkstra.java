package Graph_Related_algorithms;

import java.util.PriorityQueue;

public class Dijkstra {
	/** @return Array of Vertex */
	public static Vertex[] initGraph() {

		Vertex v0 = new Vertex(0);
		Vertex v1 = new Vertex(1);
		Vertex v2 = new Vertex(2);
		Vertex v3 = new Vertex(3);
		Vertex v4 = new Vertex(4);

		v0.edge = new Edge[] { new Edge(1, 10), new Edge(4, 5) };
		v1.edge = new Edge[] { new Edge(2, 1), new Edge(4, 2) };
		v2.edge = new Edge[] { new Edge(3, 6) };
		v3.edge = new Edge[] { new Edge(2, 4)};
		v4.edge = new Edge[] { new Edge(3, 2), new Edge(2, 9), new Edge(1, 3) };

		Vertex vs[] = { v0, v1, v2, v3, v4 };
		return vs;
	}

	/**
	 * @param ver the Array of the Vertex
	 * @param num the start Vertex
	 */
	public static void Dijkstra(Vertex ver[], int num) {
		PriorityQueue<Vertex> q = new PriorityQueue<Vertex>();
		Vertex start = ver[num];
		q.add(start);
		start.dist = 0;
		while (!q.isEmpty()) {
			Vertex u = q.poll();
			System.out.println("and the poll is: " + u.name);
			PrintDij(ver);
			for (int i = 0; i < u.edge.length; i++) {
				Edge e = u.edge[i];
				Vertex v = ver[e.vertex];
				if (!v.visit) {
					if (v.dist > u.dist + e.weight) {
						v.dist = u.dist + e.weight;
						v.prev = ver[u.name].name;
						q.remove(v);
						q.add(v);
					}
				}
			}
			u.visit = true;
		}

	}

	/**
	 * static fun' to print the vertex
	 * 
	 * @param v the Array of vertex
	 */
	public static void PrintDij(Vertex v[]) {
		for (int i = 0; i < v.length; i++) {
			System.out.println("ver " + i + ", dist: " + v[i].dist + ", prev: " + v[i].prev + ", visit: " + v[i].visit);
		}
		System.out.println("*********************************\n");
	}

	/**
	 * @param v     the Array of vertex after Dijkstra
	 * @param start the Start vertex
	 * @param end   the vertex we want to find his track
	 * @return String of track
	 */
	public static String getTrack(Vertex v[], int start, int end) {
		String ans = "";
		if (v[end].dist == Double.POSITIVE_INFINITY)
			return "there is no track!";
		while (v[end].dist != 0) {
			ans = end + " --> " + ans;
			end = v[end].prev;
		}
		ans = start + " --> " + ans;
		return ans;
	}

	public static void main(String[] args) {
		Vertex [] v = initGraph();
		Dijkstra(v, 0);
		System.out.println("=================================\nthe track between 0 to 3:");
		System.out.println(getTrack(v, 0, 3));
	}
	/*
	                  (1)
	         [1]-----------*[2]
	        * | *          *| *
	       /  | |         / | |
	 (10) /   | |        /  | | 
	     /    | |       /   | | 
	    /     | |      /(9) | |
	 [0]  (2) | |(3)  /     | |
	    \     | |    /   (6)| |(4)
	     \    | |   /       | |
	  (5) \   | |  /        | |
	       \  * | /         * |
	        * [4]----------*[3]
	                  (2)
	  
	   
	 */
}
