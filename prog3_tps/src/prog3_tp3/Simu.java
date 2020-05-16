package prog3_tp3;

import java.util.*;

public class Simu {

	private static HashMap<Integer, Task<Integer>> tasks = new HashMap<Integer, Task<Integer>>();
	private static LinkedList<Integer> sol = new LinkedList<Integer>();
	private static int time;
	private final static Integer NOTVISITED = 0;
	private final static Integer VISITED = 1;

	public static void main(String[] args) {
		DirectedGraph<Integer> dg1 = new DirectedGraph<Integer>();

		addTask(dg1, 0, 0);
		addTask(dg1, 1, 4);
		addTask(dg1, 2, 18);
		addTask(dg1, 3, 4);
		addTask(dg1, 4, 13);
		addTask(dg1, 5, 22);
		addTask(dg1, 6, 18);
		addTask(dg1, 7, 12);
		addTask(dg1, 8, 3);
		addTask(dg1, 9, 2);
		addTask(dg1, 10, 3);
		addTask(dg1, 11, 1);
		addTask(dg1, 12, 5);
		joinTasks(dg1, 0, 1, 0);
		joinTasks(dg1, 0, 2, 0);
		joinTasks(dg1, 1, 3, 3);
		joinTasks(dg1, 2, 5, 1);
		joinTasks(dg1, 2, 7, 18);
		joinTasks(dg1, 3, 4, 5);
		joinTasks(dg1, 3, 6, 8);
		joinTasks(dg1, 5, 6, 2);
		joinTasks(dg1, 7, 8, 7);
		joinTasks(dg1, 4, 11, 3);
		joinTasks(dg1, 6, 12, 2);
		joinTasks(dg1, 6, 10, 6);
		joinTasks(dg1, 8, 9, 4);
		joinTasks(dg1, 11, 12, 9);
		joinTasks(dg1, 9, 10, 1);
		Iterator<Integer> vIt = dg1.getVerts();
		while (vIt.hasNext()) {
			System.out.println(vIt.next());
		}
		Iterator<Arc<Integer>> aIt = dg1.getArcs();
		while (aIt.hasNext()) {
			Arc<Integer> a = aIt.next();
			System.out.println(a.getVertOrg() + " -> " + a.getVertDest());
		}

		DFS(dg1);

	}

	// metodo de entrada a DFS, verifica vertices no fueron visitados
	public static void DFS(DirectedGraph<Integer> dg) {
		// Setea todos a no visitados
		setNotVisited(dg);
		time = 0;
		Iterator<Integer> vIt = dg.getVerts();
		// recorre cada vertice
		while (vIt.hasNext()) {
			int v = vIt.next();
			// si no a sido visitado entra a DFS_visit
			if (tasks.get(v).getState() == NOTVISITED) {
				DFS_visit(dg, v);
			}
		}
	}

	// DFS recursivo, analiza todos los vertices adyacentes a cierto vertice
	public static void DFS_visit(DirectedGraph<Integer> dg, Integer v) {
		// Setea vertice a visitado
		tasks.get(v).setState(VISITED);
		time++;
		// Setea tiempo de entrada a vertice
		tasks.get(v).setD(1);
		Iterator<Arc<Integer>> ayIt = dg.getArcs(v);
		// recorre los adyacentes a ese vertice
		while (ayIt.hasNext()) {
			Arc<Integer> a = ayIt.next();
			// si no a sido visitado entra a DFS_visit
			if (tasks.get(a.getVertDest()).getState() == NOTVISITED) {
				DFS_visit(dg, a.getVertDest());
			}
		}
		time++;
		// Setea tiempo de salida de vertice
		tasks.get(v).setF(time);
	}

	// Añade vertices a grafo y tareas a hashmap, para separar la logica
	public static void addTask(DirectedGraph<Integer> dg, Integer id, Integer task) {
		dg.addVert(id);
		tasks.put(id, new Task<Integer>(task));
	}

	// Une vertices, o "tareas", mediante arcos
	public static void joinTasks(DirectedGraph<Integer> dg, Integer id1, Integer id2, Integer dur) {
		dg.addArc(id1, id2, dur);
	}

	// Setea todas las tareas en BLANCO (vertices no visitados)
	public static void setNotVisited(DirectedGraph<Integer> dg) {
		Iterator<Integer> vIt = dg.getVerts();
		while (vIt.hasNext()) {
			Integer v = vIt.next();
			tasks.get(v).setState(NOTVISITED);
		}
	}

	public static void setState(Integer t, int s) {
		tasks.get(t).setState(s);
	}

}
