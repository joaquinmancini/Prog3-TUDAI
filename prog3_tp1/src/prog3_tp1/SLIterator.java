package prog3_tp1;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SLIterator implements Iterator<Node> {

	private Node current;

	public SLIterator(Node head) {
		current = head;
	}

	@Override
	public boolean hasNext() {
		return current != null;
	}

	@Override
	public Node next() {
		if (current == null) {
			throw new NoSuchElementException();
		}
		Node tmp = current;
		current = current.getNext();
		return tmp;
	}
	
	public Integer get() {
		return current.getInfo();
	}

}
