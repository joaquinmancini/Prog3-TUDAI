package prog3_tp3;

import java.util.*;

public class DirectedGraph<T> implements Graph<T> {

	private ArrayList<Vertex<T>> verts;

	public DirectedGraph() {
		this.verts = new ArrayList<Vertex<T>>();
	}

	// Retorna vertice desde el arreglo
	// O(V), donde V es cada vertice del Grafo
	private Vertex<T> getVert(Integer vertId) {
		for (Vertex<T> vertex : verts) {
			if (vertex.getVertId() == vertId) {
				return vertex;
			}
		}
		return null;
	}

	// Agrega vertice
	// O(V+1) u O(V), donde V es cada vertice del Grafo y 1
	// el caso del add()
	public void addVert(Integer vertId) {
		Vertex<T> vaux = new Vertex<T>(vertId);
		if (!verts.contains(vaux)) {
			verts.add(vaux);
		}
	}

	// Borra un vertice
	// O(V+A), donde V es cada vertice del Grafo y A cada arco del grafo
	public void deleteVert(Integer vertId) {
		Vertex<T> vaux = new Vertex<T>(vertId);
		boolean d = verts.remove(vaux);
		if (d) {
			System.out.println("|" + vertId + "| deleted.");
		}
		for (Vertex<T> vertex : verts) {
			vertex.deleteArc(null, vertId);
		}
	}

	// Agrega un arco con una etiqueta, que conecta el vertId1 con el vertId2
	// O(V+a), donde V es cada vertice del Grafo y a cada arco de ese vertice
	public void addArc(Integer vertId1, Integer vertId2, T tag) {
		if (containsVert(vertId1) && containsVert(vertId2)) {
			this.getVert(vertId1).addArc(vertId1, vertId2, tag);
		}
	}

	// Borra el arco que conecta el vertId1 con el vertId2
	// O(V+a), donde V es cada vertice del Grafo (chequeando si existen
	// ambos vertices) y a cada arco de ese vertice
	public void deleteArc(Integer vertId1, Integer vertId2) {
		if (containsVert(vertId1) && containsVert(vertId2)) {
			this.getVert(vertId1).deleteArc(vertId1, vertId2);
		}
	}

	// Chequea si existe un vertice
	// O(V), donde V es cada vertice del Grafo
	public boolean containsVert(Integer vertId) {
		Vertex<T> vaux = new Vertex<T>(vertId);
		return verts.contains(vaux);
	}

	// Chequea si existe un arco entre el dos vertices
	// O(V+a), donde V es cada vertice del Grafo y a cada arco de ese
	// vertice
	public boolean existArc(Integer vertId1, Integer vertId2) {
		if (containsVert(vertId1)) {
			return this.getVert(vertId1).hasArc(vertId1, vertId2);
		}
		return false;
	}

	// Devuelve el arco que conecta el verticeId1 con el verticeId2
	// O(V+a), donde V es cada vertice del Grafo y a cada arco de ese
	// vertice
	public Arc<T> getArc(Integer vertId1, Integer vertId2) {
		if (containsVert(vertId1)) {
			return this.getVert(vertId1).getArc(vertId1, vertId2);
		}
		return new Arc<T>(null, null, null);
	}

	// Devuelve la cantidad total de vertices en el grafo
	// O(1), siendo 1 constante por el tipo de consulta a la coleccion
	public Integer cantVerts() {
		return verts.size();
	}

	// Devuelve la cantidad total de arcos en el grafo
	// O(V), donde V es cada vertice del Grafo
	public Integer cantArcs() {
		Integer c = 0;
		for (Vertex<T> vertex : verts) {
			c += vertex.cantArcs();
		}
		return c;
	}

	// Devuelve un iterador para recorrer todos los vertices almacenados en el grafo
	// O(1), donde 1 es constante porque solo se crea un objeto iterador
	public Iterator<Integer> getVerts() {
		return new VertIterator<T>(this.verts.iterator());
	}

	// Devuelve un iterador para recorrer todos los vertices adyacentes a vertId
	// O(V), donde V es cada vertice del Grafo para obtener el iterador de sus ady
	public Iterator<Integer> getAdys(Integer vertId) {
		Vertex<T> vaux = new Vertex<T>(null);
		for (Vertex<T> vertex : verts) {
			if (vertex.getVertId() == vertId) {
				vaux = vertex;
			}
		}
		return new ArcIterator<T>(vaux.getIterator());
	}

	// Devuelve un iterador para recorrer todos los arcos del grafo
	// O(V), donde V es cada vertice del Grafo
	public Iterator<Arc<T>> getArcs() {
		ArrayList<Arc<T>> aux = new ArrayList<Arc<T>>();
		for (Vertex<T> vertex : verts) {
			aux.addAll(vertex.copyArcs());
		}
		return aux.iterator();
	}

	// Devuelve un iterador para recorrer todos los arcos que salen de vertId1
	// O(V), donde V es cada vertice del Grafo y a cada arco de ese
	// vertice
	public Iterator<Arc<T>> getArcs(Integer vertId) {
		Vertex<T> vaux = new Vertex<T>(null);
		for (Vertex<T> vertex : verts) {
			if (vertex.getVertId() == vertId) {
				vaux = vertex;
			}
		}
		return vaux.getIterator();
	}

}
