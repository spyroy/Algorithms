package Graph_Related_algorithms;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * Huffman algorithm - using two queue - assumption: the given freq array is
 * sorted Complexity: O(n)
 */

class Node {
	char c;
	int freq;
	Node left;
	Node right;

	public Node(char c, int freq) {
		this.c = c;
		this.freq = freq;
		this.left = null;
		this.right = null;
	}
}

public class Huffman {

	public static ArrayBlockingQueue<Node> q1, q2;

	public static Node Huffman(Node[] c) { //
		int n = c.length;
		Node temp = null;
		q1 = new ArrayBlockingQueue<Node>(n);
		q2 = new ArrayBlockingQueue<Node>(n);

		for (int i = 0; i < c.length; i++) {
			q1.add(c[i]);
		}
		boolean flag = true;
		while (flag) {
			Node min_1 = getMin();
			Node min_2 = getMin();
			temp = new Node((char) (n + 'a'), min_1.freq + min_2.freq);
			temp.left = min_1;
			temp.right = min_2;
			q2.add(temp);
			if ((min_2.freq + min_1.freq) == 100) {
				flag = false;
			}
		}
		return temp;

	}

	public static Node getMin() {
		if (q1.isEmpty() && q2.isEmpty())
			return null;
		if (q1.isEmpty())
			return q2.poll(); // poll - see and remove the first element
		if (q2.isEmpty())
			return q1.poll();
		if (q1.peek().freq > q2.peek().freq)
			return q2.poll(); // peek - see the first element
		return q1.poll();
	}

	public static void printTree(Node c) {
		if (c == null) {
			return;
		}
		printTree(c.left);
		System.out.print(c.c + " - > ");
		printTree(c.right);
	}

	public static void buildCode(String code, Node root, Node c) {
		if (root == null) {
			return;
		}
		if (root.c == c.c)
			System.out.println("for " + c.c + " code = " + code);
		buildCode(code + "0", root.left, c);
		buildCode(code + "1", root.right, c);

	}

	public static void main(String[] args) {

		Node a = new Node('a', 4);
		Node b = new Node('b', 7);
		Node c = new Node('c', 10);
		Node d = new Node('d', 18);
		Node e = new Node('e', 25);
		Node f = new Node('f', 36);
		Node[] narr = { a, b, c, d, e, f };
		Node t = Huffman(narr);
		printTree(t);
		System.out.println();
		for (int i = 0; i < narr.length; i++) {
			buildCode("", t, narr[i]);

		}

	}
}