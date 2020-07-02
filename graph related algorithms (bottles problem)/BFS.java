package Graph_Related_algorithms;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class BFS {

	public static final int white = 0;
	public static final int gray = 1;
	public static final int black = 2;
	public static final int NIL = -1;
	int color[];
	int dist[];
	int pred[];
	LinkedList<Integer>[] list;
	int size;
	int start;

	/**
	 * Constructor
	 * 
	 * @param numOfV the number of Vertex
	 * @param list   the LinkedList of all edges
	 */
	public BFS(int numOfV, LinkedList<Integer> list[]) {
		size = numOfV;
		color = new int[size];
		this.start = 0;
		this.list = list;
		dist = new int[size];
		pred = new int[size];
	}

	/**
	 * Function finds all the tracks from st Vertex
	 * 
	 * @param st the Start Vertex
	 */
	public void B_F_S(int st) {
		for (int i = 0; i < size; i++) {
			dist[i] = NIL;
			pred[i] = NIL;
		}
		start = st;
		int v ;
		int u;
		dist[start] = 0;
		color[start] = gray;
		Queue<Integer> Que = new ArrayBlockingQueue<Integer>(100);
		Que.add(start);

		while (!Que.isEmpty()) {
			v = Que.poll();
			while (!list[v].isEmpty()) {
				u = list[v].removeFirst();
				if(color[u] == white) {
					color[u] = gray;
					dist[u] = dist[v] +1;
					pred[u] = v;
					Que.add(u);
				}
			}
			color[v] = black;
			System.out.println("the Que now is: " + Que);
			printArrays(dist, pred, color);
		}
	}

	/**
	 *
	 * @return a graph as we learned in class
	 */
	public static LinkedList<Integer>[] InPut_0to7() {
		int size = 8;
		LinkedList<Integer> list[] = new LinkedList[size];
		for (int i = 0; i < size; i++) {
			list[i] = new LinkedList<Integer>();
		}
		list[0].add(1);
		list[0].add(4);
		list[1].add(0);
		list[1].add(5);
		list[2].add(3);
		list[2].add(5);
		list[2].add(6);
		list[3].add(2);
		list[3].add(6);
		list[3].add(7);
		list[4].add(0);
		list[5].add(1);
		list[5].add(2);
		list[5].add(6);
		list[6].add(5);
		list[6].add(2);
		list[6].add(3);
		list[6].add(7);
		list[7].add(3);
		list[7].add(6);
		return list;
	}

	/**
	* Helped function to print array data
	*/
	public void PrintArrayLL() {
	System.out.println("************the Array LinkedList of Graph is: ************");

	for (int i=0; i<size; i++) {
	System.out.println("vertex "+i+" -> "+list[i].toString());
	}
	System.out.println("******************************************************\n");
	}

	/**
	 * Helped function vertex data printing
	 * 
	 * @param dist  the Distance from the start
	 * @param pred  the "father" of the vertex
	 * @param color the color of the vertex
	 */
	public static void printArrays(int dist[], int pred[], int color[]) {
		for (int i = 0; i < dist.length; i++) {
			String temp = "";
			if (color[i] == 0)
				temp = "White";
			if (color[i] == 1)
				temp = "Gray";
			if (color[i] == 2)
				temp = "Black";
			System.out.println("Vertex " + i + "\tDist=" + dist[i] + "\t\tPred=" + pred[i] + "\t\tThe color: " + temp);

		}
		System.out.println("-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-");
	}
	
	public static void main(String[] args) {
		LinkedList<Integer> list[] = InPut_0to7();
		BFS b = new BFS(8,list);
		b.B_F_S(0);
		/*
		=========== Graph 0 to 7 ===========
		(0)--------(1)        (2)---------(3)
		 |          | 		  / |         /| 
		 | 			|        /  |        / | 
		 | 			|       /   |       /  | 
		 | 			|      /    |      /   | 
		 | 			|     /     |     /    | 
		 | 			|    /      |    /     | 
		 | 			|   /       |   /      | 
		 | 			|  /        |  /       | 
		 | 			| /         | /        | 
		(4) 		(5)--------(6)--------(7)
		=====================================
		*/
	}

}
