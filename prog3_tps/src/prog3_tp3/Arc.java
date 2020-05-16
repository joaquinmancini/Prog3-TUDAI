package prog3_tp3;

public class Arc<T> {

	private Integer vertOrg;
	private Integer vertDest;
	private T tag;

	public Arc(Integer vertOrg, Integer vertDest, T tag) {
		this.vertOrg = vertOrg;
		this.vertDest = vertDest;
		this.tag = tag;
	}

	// Retorna vertice origen
	public Integer getVertOrg() {
		return vertOrg;
	}

	// Retorna vertice destino
	public Integer getVertDest() {
		return vertDest;
	}

	// Retorna etiqueta
	public T getTag() {
		return tag;
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Arc<?>))
			return false;
		Arc<?> o = (Arc<?>) obj;
		return o.getVertOrg() == this.getVertOrg() && o.getVertDest() == this.getVertDest();
	}
}
