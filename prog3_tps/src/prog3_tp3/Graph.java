package prog3_tp3;

import java.util.Iterator;

public interface Graph<T> {

	// Agrega vertice
	public void addVert(Integer vertId);

	// Borra un vertice
	public void deleteVert(Integer vertId);

	// Agrega un arco con una etiqueta, que conecta el vertId1 con el vertId2
	public void addArc(Integer vertId1, Integer vertId2, T tag);

	// Borra el arco que conecta el vertId1 con el vertId2
	public void deleteArc(Integer vertId1, Integer vertId2);

	// Chequea si existe un vertice
	public boolean containsVert(Integer vertId);

	// Chequea si existe un arco entre el dos vertices
	public boolean existArc(Integer vertId1, Integer vertId2);

	// Devuelve el arco que conecta el verticeId1 con el verticeId2
	public Arc<T> getArc(Integer vertId1, Integer vertId2);

	// Devuelve la cantidad todal de vertices en el grafo
	public Integer cantVerts();

	// Devuelve la cantidad total de arcos en el grafo
	public Integer cantArcs();

	// Devuelve un iterador para recorrer todos los vertices almacenados en el grafo
	public Iterator<Integer> getVerts();

	// Devuelve un iterador para recorrer todos los vertices adyacentes a vertId
	public Iterator<Integer> getAdys(Integer vertId);

	// Devuelve un iterador para recorrer todos los arcos del grafo
	public Iterator<Arc<T>> getArcs();

	// Devuelve un iteradpr para recorrer todos los arcos que salen de vertId1
	public Iterator<Arc<T>> getArcs(Integer vertId);
}
