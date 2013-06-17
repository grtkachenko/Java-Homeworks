// invariant: size >= 0
public class ArrayQueueSingleton {
	private static int head, tail, size;
	private static Object[] elements = new Object[10];
	// pred:
	// post:
	// sizeNew = sizeOld + 1
	// elements[tailNew - 1] = element - condition of the realization
	public static void push(Object element) {
		ensureCapacity(size());
		elements[tail] = element;
		if (tail == elements.length - 1) {
			tail = 0;
		} else {
			tail++;
		}
		size++;
	}

	private static void ensureCapacity(int capacity) {
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
	// res = elements[oldHead] - condition of the realization
                                   
	public static Object pop() {
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
	public static Object peek() {
		if (isEmpty()) {
			return null;
		}
		return elements[head];
	}

	// pred
	// post res = size
	public static int size() {
		return size;
	}

	// pred
	// post res = (size == 0)
	public static boolean isEmpty() {
		return size == 0;
	}
}
