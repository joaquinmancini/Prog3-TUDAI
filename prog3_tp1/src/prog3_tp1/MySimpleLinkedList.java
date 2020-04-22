package prog3_tp1;

import java.util.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MySimpleLinkedList implements Iterable<Node> {
	protected Node first, last;
	protected int size;

	public MySimpleLinkedList() {
		first = null;
		size = 0;
	}

	public void insertFront(Integer i) {
		Node tmp = new Node(i, null);
		if (isEmpty()) {
			first = tmp;
		} else {
			tmp.setNext(first);
			first = tmp;
		}
		size++;
	}

	public Integer extractFront() {
		if (first == null) {
			throw new NoSuchElementException();
		} else {
			Integer in = first.getInfo();
			Node tmp = first.getNext();
			first.setNext(null);
			first = tmp;
			size--;
			return in;
		}
	}

	public boolean isEmpty() {
		return first == null;
	}

	public int size() {
		return size;
	}

	public Object get(int index) {
		if (index < size) {
			Node tmp = first;
			for (int i = 0; i < index; i++) {
				tmp = tmp.getNext();
			}
			return tmp.getInfo();
		}
		return null;
	}

	public MySimpleLinkedList reverse() {
		MySimpleLinkedList aux = new MySimpleLinkedList();
		for (Node n : this) {
			aux.insertFront(n.getInfo());
		}
		return aux;
	}

	public ArrayList<MySimpleLinkedList> subsequencesList() {
		SLIterator it1 = this.iterator();

		MySimpleLinkedList aux = new MySimpleLinkedList();
		ArrayList<MySimpleLinkedList> sol = new ArrayList<MySimpleLinkedList>();

		while (it1.hasNext()) {
			Node cur = it1.next();
			int result=0;
			if (it1.hasNext()) {
				result = cur.getInfo().compareTo(it1.get());
			}
			aux.insertFront(cur.getInfo());
			if (result >= 0) {
				if (aux.size() >= 2) {
					sol.add(aux.reverse());
				}
				aux = new MySimpleLinkedList();
			}
		}
		return sol;
	}

	@Override
	public SLIterator iterator() {
		return new SLIterator(first);
	}

}
