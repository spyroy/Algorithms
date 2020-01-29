package calcCars;

/**
 * this class is represent a node in the LinkedListCycle class, each node has a
 * pointer to the next node, a pointer to the previous node, head and data
 * 
 * @author spyro
 *
 */
public class Node {
	char data;
	Node prev, next;

	public Node(char data, Node prev, Node next) {
		this.data = data;
		this.next = next;
		this.prev = prev;
	}

	public String toString() {
		return "" + this.data;
	}

	public void setData(char c) {
		this.data = c;
	}

	public char getData() {
		return this.data;
	}

	public Node getNext() {
		return this.next;
	}

	public Node getPrev() {
		return this.prev;
	}
}
