public class ArrayQueue implements Queue{
	private int head, tail, size;
	private int[] elements;

	public ArrayQueue() {
		elements = new int[10];
	}

	public void push(int element) {
		ensureCapacity(size());
		elements[tail] = element;
		if (tail == elements.length - 1) {
			tail = 0;
		} else {
			tail++;
		}
		size++;
	}

	private void ensureCapacity(int capacity) {
		if (elements.length >= capacity) {
			return;
		}
		int newCapacity = Math.max(elements.length * 2, capacity);
		int[] newElements = new int[newCapacity];

		for (int i = 0; i < size(); i++) {
			newElements[i] = elements[(head + i) % elements.length];
		}
		elements = newElements;
	}

	public int pop() {
		if (isEmpty()) {
			return 0;
		}
		int element = elements[head];
		elements[head] = 0;
		if (head == elements.length - 1) {
			head = 0;
		} else {
			head++;
		}
		size--;
		return element;
	}

	public int peek() {
		if (isEmpty()) {
			return 0;
		}
		return elements[head];
	}

	public int size() {
		return size;
	}

	public boolean isEmpty() {
		return size == 0;
	}
}
