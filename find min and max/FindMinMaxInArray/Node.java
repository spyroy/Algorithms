package FindMinMaxInArray;

import java.util.Stack;

public class Node {
	int num;
	Stack<Integer> st;
	
	public Node(int num) {
		this.num = num;
		st = new Stack<Integer>();
	}
}
