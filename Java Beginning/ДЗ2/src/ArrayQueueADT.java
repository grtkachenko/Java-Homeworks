// invariant: size >= 0
public class ArrayQueueADT {
	private int head, tail, size;
	private Object[] elements;

	public ArrayQueueADT() {
		elements = new Object[10];
	}

	// pred:
	// post:
	// sizeNew = sizeOld + 1
	// elements[tailNew - 1] = element
	public static void push(ArrayQueueADT q, Object element) {
		ensureCapacity(q, size(q));
		q.elements[q.tail] = element;
		if (q.tail == q.elements.length - 1) {
			q.tail = 0;
		} else {
			q.tail++;
		}
		q.size++;
	}

	private static void ensureCapacity(ArrayQueueADT q, int capacity) {
		if (q.elements.length >= capacity) {
			return;
		}
		int newCapacity = Math.max(q.elements.length * 2, capacity);
		Object[] newElements = new Object[newCapacity];

		for (int i = 0; i < size(q); i++) {
			newElements[i] = q.elements[(q.head + i) % q.elements.length];
		}
		q.elements = newElements;
	}

	// pred size > 0
	// post:
	// res = peek
	// res = elements[oldHead]

	public static Object pop(ArrayQueueADT q) {
		if (isEmpty(q)) {
			return null;
		}
		Object element = q.elements[q.head];
		q.elements[q.head] = null;
		if (q.head == q.elements.length - 1) {
			q.head = 0;
		} else {
			q.head++;
		}
		q.size--;
		return element;
	}

	// pred size > 0
	// post:
	// res = elements[head]
	// newSize = oldSize
	public static Object peek(ArrayQueueADT q) {
		if (isEmpty(q)) {
			return null;
		}
		return q.elements[q.head];
	}

	// pred
	// post res = size
	public static int size(ArrayQueueADT q) {
		return q.size;
	}

	// pred
	// post res = (size == 0)
	
	public static boolean isEmpty(ArrayQueueADT q) {
		return q.size == 0;
	}
}
