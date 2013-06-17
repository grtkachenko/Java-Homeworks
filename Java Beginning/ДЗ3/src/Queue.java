//invar size >= 0
public interface Queue {
	// pred:
	// post:
	// sizeNew = sizeOld + 1
	// elements[tailNew - 1] = element
	void push(int element);

	// pred size > 0
	// post:
	// res = peek
	// res = elements[oldHead]
	int pop();

	// pred size > 0
	// post:
	// res = elements[head]
	// newSize = oldSize
	int peek();

	// pred
	// post res = size
	int size();

	// pred
	// post res = (size == 0)
	boolean isEmpty();
}
