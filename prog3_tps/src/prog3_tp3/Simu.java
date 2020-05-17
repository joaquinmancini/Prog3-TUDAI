package prog3_tp3;

import java.util.*;

public class Simu {

	private static HashMap<Integer, Task<Integer>> tasks = new HashMap<Integer, Task<Integer>>();
	private static LinkedList<Integer> sol = new LinkedList<Integer>();
	private static int maxTime;

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
		addTask(dg1, 11, 25);
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
		DFS(dg1);
		System.out.println(sol);
		System.out.println(tasks);

	}

	// metodo de entrada a DFS, verifica vertices no fueron visitados
	public static void DFS(DirectedGraph<Integer> dg) {
		LinkedList<Integer> aux = new LinkedList<Integer>();
		Integer first = 0;
		Integer cS = 0;
		sol.clear();
		maxTime = 0;
		DFS_visit(dg, aux, first, cS);
		sol.push(first);
	}

	// DFS recursivo, analiza todos los vertices adyacentes a cierto vertice
	public static void DFS_visit(DirectedGraph<Integer> dg, LinkedList<Integer> p, Integer v, Integer s) {
		Iterator<Arc<Integer>> ayIt = dg.getArcs(v);
		if (!ayIt.hasNext()) {
			if (s > maxTime) {
				maxTime = s;
				sol.clear();
				sol.addAll(p);
			}
		}
		while (ayIt.hasNext()) {
			Arc<Integer> a = ayIt.next();
			p.add(a.getVertDest());
			s += a.getTag() + tasks.get(v).getDuration();
			DFS_visit(dg, p, a.getVertDest(), s);
			p.removeLast();
			s -= a.getTag() + tasks.get(v).getDuration();
		}
	}

	// Añade vertices a grafo y tareas a hashmap, para separar el problema de la
	// implementacion de grafo
	public static void addTask(DirectedGraph<Integer> dg, Integer id, Integer task) {
		dg.addVert(id);
		tasks.put(id, new Task<Integer>(task));
	}

	// Une vertices, o "tareas", mediante arcos
	public static void joinTasks(DirectedGraph<Integer> dg, Integer id1, Integer id2, Integer dur) {
		dg.addArc(id1, id2, dur);
	}

}
