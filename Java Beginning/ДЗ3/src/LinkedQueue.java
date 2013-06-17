public class LinkedQueue implements Queue {
	private class Node {
		Node next;
		int val;

		Node(Node next, int val) {
			this.next = next;
			this.val = val;
		}
	}

	private Node head = null, tail = null;
	private int size = 0;
	

	public void push(int element) {
		size++;
		if (tail == null) {
			tail = head = new Node(null, element);
			return;
		}
		Node tmp = new Node(null, element);
		tail.next = tmp;
		tail = tmp;
	}

	public int pop() {
		if (isEmpty()) {
			return 0;
		}
		int element = head.val;
		head = head.next;
		if (head == null) {
			tail = null;
		}
		size--;
		return element;
	}

	public int peek() {
		if (isEmpty()) {
			return 0;
		}
		return head.val;
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
}
