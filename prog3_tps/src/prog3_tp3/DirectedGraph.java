package prog3_tp3;

import java.util.*;

public class DirectedGraph<T> implements Graph<T> {

	private ArrayList<Vertex<T>> verts;

	public DirectedGraph() {
		this.verts = new ArrayList<Vertex<T>>();
	}

	// Retorna vertice desde el arreglo
	public Vertex<T> getVert(Integer vertId) {
		Vertex<T> vaux = new Vertex<T>(vertId);
		for (Vertex<T> vertex : verts) {
			if (vertex.equals(vaux)) {
				return vertex;
			}
		}
		return null;
	}

	// Agrega vertice
	public void addVert(Integer vertId) {
		Vertex<T> vaux = new Vertex<T>(vertId);
		if (!verts.contains(vaux)) {
			verts.add(vaux);
		}
	}

	// Borra un vertice
	public void deleteVert(Integer vertId) {
		Vertex<T> vaux = new Vertex<T>(vertId);
		verts.remove(vaux);
		for (Vertex<T> vertex : verts) {
			vertex.deleteArc(null, vertId);
		}
	}

	// Agrega un arco con una etiqueta, que conecta el vertId1 con el vertId2
	public void addArc(Integer vertId1, Integer vertId2, T tag) {
		if (containsVert(vertId1) && containsVert(vertId2)) {
			this.getVert(vertId1).addArc(vertId1, vertId2, tag);
		}
	}

	// Borra el arco que conecta el vertId1 con el vertId2
	public void deleteArc(Integer vertId1, Integer vertId2) {
		if (containsVert(vertId1) && containsVert(vertId2)) {
			this.getVert(vertId1).deleteArc(vertId1, vertId2);
		}
	}

	// Chequea si existe un vertice
	public boolean containsVert(Integer vertId) {
		Vertex<T> vaux = new Vertex<T>(vertId);
		return verts.contains(vaux);
	}

	// Chequea si existe un arco entre el dos vertices
	public boolean existArc(Integer vertId1, Integer vertId2) {
		if (containsVert(vertId1) && containsVert(vertId2)) {
			return this.getVert(vertId1).hasArc(vertId1, vertId2);
		}
		return false;
	}

	// Devuelve el arco que conecta el verticeId1 con el verticeId2
	public Arc<T> getArc(Integer vertId1, Integer vertId2) {
		if (containsVert(vertId1) && containsVert(vertId2)) {
			Arc<T> aux = new Arc<T>(vertId1, vertId2, null);
			return this.getVert(vertId1).getArc(aux);
		}
		return new Arc<T>(null, null, null);
	}

	// Devuelve la cantidad todal de vertices en el grafo
	public Integer cantVerts() {
		return verts.size();
	}

	// Devuelve la cantidad total de arcos en el grafo
	public Integer cantArcs() {
		Integer c = 0;
		for (Vertex<T> vertex : verts) {
			c += vertex.cantArcs();
		}
		return c;
	}

	// Devuelve un iterador para recorrer todos los vertices almacenados en el grafo
	public Iterator<Integer> getVerts() {
		return new VertIterator<T>(this.verts.iterator());
	}

	// Devuelve un iterador para recorrer todos los vertices adyacentes a vertId
	public Iterator<Integer> getAdys(Integer vertId) {
		Vertex<T> vaux = new Vertex<T>(vertId);
		for (Vertex<T> vertex : verts) {
			if (vertex.equals(vaux)) {
				vaux = vertex;
			}
		}
		return new ArcIterator<T>(vaux.getIterator());
	}

	// Devuelve un iterador para recorrer todos los arcos del grafo
	public Iterator<Arc<T>> getArcs() {
		ArrayList<Arc<T>> aux = new ArrayList<Arc<T>>();
		for (Vertex<T> vertex : verts) {
			aux.addAll(vertex.copyArcs());
		}
		return aux.iterator();
	}

	// Devuelve un iteradpr para recorrer todos los arcos que salen de vertId1
	public Iterator<Arc<T>> getArcs(Integer vertId) {
		Vertex<T> vaux = new Vertex<T>(vertId);
		for (Vertex<T> vertex : verts) {
			if (vertex.equals(vaux)) {
				vaux = vertex;
			}
		}
		return vaux.getIterator();
	}

}
