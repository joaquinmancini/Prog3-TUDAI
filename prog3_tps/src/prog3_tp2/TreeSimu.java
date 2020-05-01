package prog3_tp2;

import java.util.*;

public class TreeSimu {
	public static void main(String[] args) {
		Tree t1 = new Tree();
		// Verifica vacio
		System.out.println("Is empty? " + t1.isEmpty());
		t1.insert(5);
		// Verifica vacio
		System.out.println("Is empty? " + t1.isEmpty());
		t1.insert(3);
		t1.insert(8);
		// intento de ingresar un valor existente, no se realiza insercion
		t1.insert(3);
		t1.insert(7);
		t1.insert(6);
		t1.insert(10);
		t1.insert(15);
		t1.insert(13);
		// Chequeo de existencia de elemento
		System.out.println("Is " + 12 + " in the tree? " + t1.hasElem(12));
		// prints
		t1.printPreOrder();
		t1.printInOrder();
		t1.printPostOrder();
		// Raiz
		System.out.println("Root is " + t1.getRoot());
		// borrado
		System.out.println("deletion of 8");
		t1.delete(8);
		t1.printPreOrder();
		// Altura de arbol
		System.out.println("height is " + t1.getHeigth());
		// Rama mas larga
		System.out.println("Longest branch is the following");
		List<Integer> lBranch = t1.getLongestBranch();
		System.out.println(lBranch);
		// Lista de hojas
		System.out.println("Tree leaves are the following");
		List<Integer> border = t1.getBorder();
		System.out.println(border);
		// Lista de elementos en nivel x
		System.out.println("Elements at Lv2 are the following");
		List<Integer> elemsAtX = t1.getElemAtLevel(2);
		System.out.println(elemsAtX);
		// Elemento mas grande
		System.out.println("Max elem is");
		System.out.println(t1.getMaxElem());
		
		System.out.println("--Random tree--");
		Tree t2 = fillTreeRandomly();
		t2.printPreOrder();
	}

	// Funcion que genera arbol al azar
	public static Tree fillTreeRandomly() {
		Tree tAux = new Tree();
		int i = 15;
		while (i > 0) {
			int value = (int) (Math.floor(Math.random() * 40));
			if (!tAux.hasElem(value)) {
				tAux.insert(value);
				i--;
			}
		}
		return tAux;
	}
}
