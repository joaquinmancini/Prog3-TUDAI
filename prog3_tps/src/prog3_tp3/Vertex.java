package prog3_tp3;

import java.util.ArrayList;
import java.util.Iterator;

public class Vertex<T> {
	private Integer vertId;
	private ArrayList<Arc<T>> arcs;

	public Vertex(Integer vertId) {
		this.vertId = vertId;
		this.arcs = new ArrayList<Arc<T>>();
	}

	// Retorna Id de vertice
	public Integer getVertId() {
		return vertId;
	}

	// Retorna Arco dado un Id
	public Arc<T> getArc(Integer vertId1, Integer vertId2) {
		Arc<T> aux = new Arc<T>(vertId1, vertId2, null);
		for (Arc<T> arc : arcs) {
			if (arc.equals(aux)) {
				return arc;
			}
		}
		return null;
	}

	// Añade arco a coleccion en vertice
	public void addArc(Integer vertId1, Integer vertId2, T tag) {
		Arc<T> aux = new Arc<T>(vertId1, vertId2, tag);
		if (!this.hasArc(vertId1, vertId2)) {
			this.arcs.add(aux);
		}
	}

	// Borra arco de la coleccion en vertice
	public void deleteArc(Integer vertId1, Integer vertId2) {
		Arc<T> aux = new Arc<T>(vertId1, vertId2, null);
		if (vertId1 == null) {
			for (Arc<T> arc : arcs) {
				if (arc.getVertDest() == vertId2) {
					aux = arc;
				}
			}
		}
		this.arcs.remove(aux);
	}

	// Retorna si existe o no arco en coleccion en vertice
	public boolean hasArc(Integer vertId1, Integer vertId2) {
		Arc<T> aux = new Arc<T>(vertId1, vertId2, null);
		return arcs.contains(aux);
	}

	// Retorna cantidad de arcos de la coleccion en vertice
	public Integer cantArcs() {
		return arcs.size();
	}

	// Retorna una copia de la coleccion para mantener segura la misma
	public ArrayList<Arc<T>> copyArcs() {
		return (ArrayList<Arc<T>>) arcs.clone();
	}

	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Vertex<?>))
			return false;
		Vertex<?> o = (Vertex<?>) obj;
		return o.getVertId() == this.getVertId();
	}

	public Iterator<Arc<T>> getIterator() {
		return this.arcs.iterator();
	}

}
