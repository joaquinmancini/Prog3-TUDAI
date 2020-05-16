package prog3_tp3;

import java.util.Iterator;

public class ArcIterator<T> implements Iterator<Integer> {
	
	private Iterator<Arc<T>> it;

	public ArcIterator(Iterator<Arc<T>> aIt) {
		this.it = aIt;
	}

	public boolean hasNext() {
		return it.hasNext();
	}

	public Integer next() {
		Integer id = it.next().getVertDest();
		return id;
	}
}
