package prog3_tp1;

import java.util.ArrayList;
import java.util.Iterator;

public class Simu {

	public static void main(String[] args) {
		MySimpleLinkedList list1 = new MySimpleLinkedList();
		list1.insertFront(14);
		list1.insertFront(24);
		list1.insertFront(27);
		list1.insertFront(28);
		list1.insertFront(29);
		list1.insertFront(30);
		list1.insertFront(43);
		list1.insertFront(43);
		list1.insertFront(45);
		list1.insertFront(49);

		MySimpleLinkedList list2 = new MySimpleLinkedList();
		list2.insertFront(28);
		list2.insertFront(30);
		list2.insertFront(43);

		System.out.println("Lista Completa");
		list1 = list1.reverse();
		list2 = list2.reverse();
		for (Node nd : list1) {
			System.out.println(nd.getInfo());
		}

		ArrayList<MySimpleLinkedList> sol = new ArrayList<MySimpleLinkedList>();
		sol = list1.subsequencesList();

		for (MySimpleLinkedList s : sol) {
			System.out.println("Subsecuencia");
			for (Node n : s) {
				System.out.println(n.getInfo());
			}
		}
		System.out.println("intersection");
		MySimpleLinkedList intList = new MySimpleLinkedList();
		intList = list1.intersectionList(list2);
		for (Node n : intList) {
			System.out.println(n.getInfo());
		}

		System.out.println("intersectionOrd");
		MySimpleLinkedList intListOrd = new MySimpleLinkedList();
		intListOrd = list1.interListOrd(list2);
		for (Node n : intListOrd) {
			System.out.println(n.getInfo());
		}
		System.out.println("end");
	}

}
