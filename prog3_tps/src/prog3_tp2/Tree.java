package prog3_tp2;

import java.util.*;

public class Tree {
	private TreeNode root;

	public Tree() {
		this.root = null;
	}

	// Complejidad O(log n), en el peor caso debera recorrer una seccion del
	// arbol, izq o der, para ubicar el nuevo valor
	public void insert(Integer value) {
		if (isEmpty()) {
			root = new TreeNode(value);
		} else {
			insert(root, value);
		}
	}

	private void insert(TreeNode root, Integer value) {
		if (root.getValue() > value) {
			if (root.getLeft() == null) {
				TreeNode tmp = new TreeNode(value);
				root.setLeft(tmp);
			} else {
				insert(root.getLeft(), value);
			}
		} else if (root.getValue() < value) {
			if (root.getRight() == null) {
				TreeNode tmp = new TreeNode(value);
				root.setRight(tmp);
			} else {
				insert(root.getRight(), value);
			}
		}
	}

	// Complejidad O(1) donde 1 es el 1er elemento del arbol, debido a que
	// solo tiene que devolver el valor del nodo raiz, si existe
	public Integer getRoot() {
		if (!isEmpty()) {
			return root.getValue();
		}
		return -1;
	}

	// Complejidad O(log n), en el peor de los casos tiene que recorrer una
	// porcion de los elementos del arbol, la menor o la mayor (y asi hacia adentro)
	public boolean hasElem(Integer value) {
		if (isEmpty()) {
			return false;
		}
		return hasElem(root, value);
	}

	private boolean hasElem(TreeNode root, Integer value) {
		if (root.getValue() > value) {
			if (root.getLeft() != null) {
				return hasElem(root.getLeft(), value);
			}
		} else if (root.getValue() < value) {
			if (root.getRight() != null) {
				return hasElem(root.getRight(), value);
			}
		} else {
			return true;
		}
		return false;
	}

	// Complejidad O(1), su peor caso siempre es chequear el primer nodo del arbol
	public boolean isEmpty() {
		return root == null;
	}

	// Complejidad O(n), debe recorrer e imprimir cada elemento del arbol binario
	public void printPreOrder() {
		if (isEmpty()) {
			return;
		} else {
			System.out.println("printPreOrder");
			printPreOrder(this.root);
		}
	}

	private void printPreOrder(TreeNode root) {
		System.out.print(root.getValue() + " \n");
		if (root.getLeft() != null) {
			printPreOrder(root.getLeft());
		} else {
			System.out.println("-");
		}
		if (root.getRight() != null) {
			printPreOrder(root.getRight());
		} else {
			System.out.println("-");
		}
	}

	// Complejidad O(n), debe recorrer e imprimir cada elemento del arbol binario
	public void printPostOrder() {
		if (isEmpty()) {
			return;
		} else {
			System.out.println("printPostOrder");
			printPostOrder(this.root);
		}
	}

	private void printPostOrder(TreeNode root) {
		if (root.getLeft() != null) {
			printPostOrder(root.getLeft());
		} else {
			System.out.println("-");
		}
		if (root.getRight() != null) {
			printPostOrder(root.getRight());
		} else {
			System.out.println("-");
		}
		System.out.print(root.getValue() + " \n");
	}

	// Complejidad O(n), debe recorrer e imprimir cada elemento del arbol binario
	public void printInOrder() {
		if (isEmpty()) {
			return;
		} else {
			System.out.println("printInOrder");
			printInOrder(this.root);
		}
	}

	private void printInOrder(TreeNode root) {
		if (root.getLeft() != null) {
			printInOrder(root.getLeft());
		} else {
			System.out.println("-");
		}
		System.out.print(root.getValue() + " \n");
		if (root.getRight() != null) {
			printInOrder(root.getRight());
		} else {
			System.out.println("-");
		}
	}

	// Complejidad O(log n), su peor caso es buscar en una seccion del arbol
	// el elemento a borrar
	public boolean delete(Integer value) {
		if (isEmpty()) {
			return false;
		} else {
			return delete(root, value) == null;
		}
	}

	private TreeNode delete(TreeNode root, Integer value) {
		if (root.getValue() > value) {
			root.setLeft(delete(root.getLeft(), value));
		} else if (root.getValue() < value) {
			root.setRight(delete(root.getRight(), value));
		} else {
			if (root.getLeft() != null && root.getRight() != null) {
				TreeNode tmp = root;
				TreeNode rsn = rightSmallerNode(tmp.getRight());
				root.setValue(rsn.getValue());
				root.setRight(delete(root.getRight(), rsn.getValue()));
			} else if (root.getLeft() != null) {
				root = root.getLeft();
			} else if (root.getRight() != null) {
				root = root.getRight();
			} else {
				root = null;
			}
		}
		return root;
	}

	// Complejidad O(log n), su peor caso es buscar en la seccion derecha
	// del nodo dado, el elemento menor
	private TreeNode rightSmallerNode(TreeNode root) {
		if (root.getLeft() == null) {
			return root;
		} else {
			return rightSmallerNode(root.getLeft());
		}
	}

	// Complejidad O(n), tiene que recorrer todos los nodos hasta el final para
	// saber cual rama es la mas larga y a partir de eso retornar el alto del arbol
	public int getHeigth() {
		if (isEmpty()) {
			return 0;
		} else {
			return getHeigth(root);
		}
	}

	private int getHeigth(TreeNode root) {
		if (root.getLeft() == null && root.getRight() == null) {
			return 0;
		}
		int heigthLeft = 0, heigthRight = 0;
		if (root.getLeft() != null) {
			heigthLeft = getHeigth(root.getLeft());
		}
		if (root.getRight() != null) {
			heigthRight = getHeigth(root.getRight());
		}
		int heigth = Math.max(heigthLeft, heigthRight) + 1;
		return heigth;
	}

	// Complejidad O(n), porque debe verificar cada nodo para saber cual bifurcacion
	// es la mas larga
	public List<Integer> getLongestBranch() {
		if (isEmpty()) {
			return new ArrayList<Integer>();
		} else {
			return getLongestBranch(root);
		}
	}

	private List<Integer> getLongestBranch(TreeNode root) {
		if (root.getLeft() == null && root.getRight() == null) {
			ArrayList<Integer> aux = new ArrayList<Integer>();
			aux.add(root.getValue());
			return aux;
		} else {
			ArrayList<Integer> leftBranch = new ArrayList<Integer>();
			ArrayList<Integer> rightBranch = new ArrayList<Integer>();
			if (root.getLeft() != null) {
				leftBranch.addAll(getLongestBranch(root.getLeft()));
			}
			if (root.getRight() != null) {
				rightBranch.addAll(getLongestBranch(root.getRight()));
			}
			if (leftBranch.size() > rightBranch.size()) {
				leftBranch.add(root.getValue());
				return leftBranch;
			} else {
				rightBranch.add(root.getValue());
				return rightBranch;
			}
		}
	}

	// Complejidad O(log n), debido a que tiene que buscar el lado mas derecho del
	// arbol
	public Integer getMaxElem() {
		if (isEmpty()) {
			return -1;
		} else {
			return getMaxElem(root);
		}
	}

	private Integer getMaxElem(TreeNode root) {
		if (root.getRight() != null) {
			return getMaxElem(root.getRight());
		} else {
			return root.getValue();
		}
	}

	// Complejidad O(n), ya que tiene que llegar a cada una de las hojas del arbol,
	// (al final de cada rama)
	public List<Integer> getBorder() {
		if (isEmpty()) {
			return new ArrayList<Integer>();
		} else {
			return getBorder(root);
		}
	}

	private List<Integer> getBorder(TreeNode root) {
		if (root.getLeft() == null && root.getRight() == null) {
			ArrayList<Integer> aux = new ArrayList<Integer>();
			aux.add(root.getValue());
			return aux;
		} else {
			ArrayList<Integer> border = new ArrayList<Integer>();
			if (root.getLeft() != null) {
				border.addAll(getBorder(root.getLeft()));
			}
			if (root.getRight() != null) {
				border.addAll(getBorder(root.getRight()));
			}
			return border;
		}
	}

	// Complejidad O(n), en el peor caso debe buscar hasta el ultimo nivel del
	// arbol los elementos del nivel x
	public List<Integer> getElemAtLevel(int value) {
		if (isEmpty()) {
			return new ArrayList<Integer>();
		} else {
			return getElemAtLevel(root, value);
		}
	}

	private List<Integer> getElemAtLevel(TreeNode root, int value) {
		if (value == 0) {
			ArrayList<Integer> aux = new ArrayList<Integer>();
			aux.add(root.getValue());
			return aux;
		} else {
			ArrayList<Integer> sum = new ArrayList<Integer>();
			if (root.getLeft() != null) {
				sum.addAll(getElemAtLevel(root.getLeft(), value - 1));
			}
			if (root.getRight() != null) {
				sum.addAll(getElemAtLevel(root.getRight(), value - 1));
			}
			return sum;
		}
	}
}
