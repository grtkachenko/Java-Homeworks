// invariant: size >= 0
public class ArrayQueue {
	private int head, tail, size;
	private Object[] elements;

	public ArrayQueue() {
		elements = new Object[10];
	}

	// pred:
	// post:
	// sizeNew = sizeOld + 1
	// elements[tailNew - 1] = element
	public void push(Object element) {
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
		Object[] newElements = new Object[newCapacity];

		for (int i = 0; i < size(); i++) {
			newElements[i] = elements[(head + i) % elements.length];
		}
		elements = newElements;
	}


	// pred size > 0
	// post:
	// res = peek
	// res = elements[oldHead]
	public Object pop() {
		if (isEmpty()) {
			return null;
		}
		Object element = elements[head];
		elements[head] = null;
		if (head == elements.length - 1) {
			head = 0;
		} else {
			head++;
		}
		size--;
		return element;
	}

	// pred size > 0
	// post:
	// res = elements[head]
	// newSize = oldSize
	public Object peek() {
		if (isEmpty()) {
			return null;
		}
		return elements[head];
	}

	// pred
	// post res = size
	public int size() {
		return size;
	}

	// pred
	// post res = (size == 0)
	public boolean isEmpty() {
		return size == 0;
	}
}
