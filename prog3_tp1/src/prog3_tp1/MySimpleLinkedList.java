package prog3_tp1;

import java.util.*;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MySimpleLinkedList implements Iterable<Node> {
	protected Node first;
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

	public void insertSort(Integer i) {
		Node tmp = new Node(i, null);
		if (isEmpty()) {
			first = tmp;
		} else {
			Node curr, prev = null;
			SLIterator it1 = this.iterator();
			SLIterator it2 = this.iterator();
			boolean added = false;
			while (it1.hasNext() && !added) {
				curr = it1.next();
				Integer res = tmp.compareTo(curr);
				if (res < 0) {
					if (prev != null) {
						prev.setNext(tmp);
					} else {
						first = tmp;
					}
					tmp.setNext(curr);
					added = true;
				} else if (res > 0) {
					if (!it1.hasNext()) {
						curr.setNext(tmp);
						added = true;
					}
				} else {
					added = true;
				}

				prev = it2.next();
			}
		}
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

	// devuelve Obj en indice i
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

	// invertir lista
	public MySimpleLinkedList reverse() {
		MySimpleLinkedList aux = new MySimpleLinkedList();
		for (Node n : this) {
			aux.insertFront(n.getInfo());
		}
		return aux;
	}

	// secuencias ascendentes de Size>=2
	public ArrayList<MySimpleLinkedList> subsequencesList() {
		SLIterator it1 = this.iterator();

		MySimpleLinkedList aux = new MySimpleLinkedList();
		ArrayList<MySimpleLinkedList> sol = new ArrayList<MySimpleLinkedList>();

		while (it1.hasNext()) {
			Node cur = it1.next();
			int result = 0;
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

	// lo que esta en a y en b
	public MySimpleLinkedList intersectionList(MySimpleLinkedList list2) {
		MySimpleLinkedList aux = new MySimpleLinkedList();
		SLIterator it1 = this.iterator();

		while (it1.hasNext()) {
			SLIterator it2 = list2.iterator();
			while (it2.hasNext()) {
				Integer res = -1;
				res = it1.get().compareTo(it2.get());
				if (res == 0) {
					aux.insertSort(it1.get());
				}
				it2.next();
			}
			it1.next();
		}
		return aux;
	}

	public MySimpleLinkedList interListOrd(MySimpleLinkedList list2) {
		MySimpleLinkedList aux = new MySimpleLinkedList();
		SLIterator it1 = this.iterator();
		SLIterator it2 = list2.iterator();
		while (it1.hasNext() && it2.hasNext()) {
			if (it1.get() < it2.get()) {
				it1.next();
			} else if (it1.get() > it2.get()) {
				it2.next();
			} else {
				aux.insertFront(it1.get());
				it1.next();
				it2.next();
			}
		}
		return aux.reverse();
	}

	@Override
	public SLIterator iterator() {
		return new SLIterator(first);
	}

}
