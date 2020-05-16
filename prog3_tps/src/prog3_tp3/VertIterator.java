package prog3_tp3;

import java.util.Iterator;

public class VertIterator<T> implements Iterator<Integer> {

	private Iterator<Vertex<T>> it;

	public VertIterator(Iterator<Vertex<T>> vIt) {
		this.it = vIt;
	}

	public boolean hasNext() {
		return it.hasNext();
	}

	public Integer next() {
		Integer id = it.next().getVertId();
		return id;
	}

}
