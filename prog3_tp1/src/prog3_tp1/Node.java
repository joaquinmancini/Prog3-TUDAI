package prog3_tp1;

public class Node implements Comparable<Node>{
	private Integer info;
	private Node next;

	public Node() {
		info = null;
		next = null;
	}

	public Node(Integer i, Node n) {
		setInfo(i);
		setNext(n);
	}

	public void setInfo(Integer i) {
		info = i;
	}

	public void setNext(Node n) {
		next = n;
	}

	public Integer getInfo() {
		return info;
	}

	public Node getNext() {
		return next;
	}

	@Override
	public boolean equals(Object o) {
		if (o == null)
			return false;
		if (this == o)
			return true;
		if (!(o instanceof Node))
			return false;
		Node n = (Node) o;
		return this.getInfo() == n.getInfo();
	}

	@Override
	public int compareTo(Node o) {
		return this.getInfo().compareTo(o.getInfo());
	}
}
