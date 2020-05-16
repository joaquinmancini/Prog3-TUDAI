package prog3_tp3;

import java.util.*;

public class Simu {

	private static HashMap<Integer, Task<Integer>> tasks = new HashMap<Integer, Task<Integer>>();
	private static int time;
	private final static Integer BLANCO = 0;
	private final static Integer AMARILLO = 1;
	private final static Integer NEGRO = 2;

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

	// Primera llamada a DFS, verifica tambien si algun vertice no fue visitado
	public static void DFS(DirectedGraph<Integer> dg) {
//		paintWhite(dg);
		time = 0;
//		Iterator<Integer> vIt = dg.getVerts();
//		while (vIt.hasNext()) {
//			int v = vIt.next();
//			if (tasks.get(v).getColor() == BLANCO) {
				DFS(dg, 0);
//			}
//		}
	}

	// DFS recursivo, analiza todos los vertices adyacentes a cierto vertice
	public static void DFS(DirectedGraph<Integer> dg, Integer v) {
//		tasks.get(v).setColor(AMARILLO);
		time++;
		tasks.get(v).setD(time);
		Iterator<Integer> vyIt = dg.getAdys(v);
		while (vyIt.hasNext()) {
			int a = vyIt.next();
//			if (tasks.get(a).getColor() == BLANCO) {
				DFS(dg, a);
//			}
			System.out.println("tarea"+a);
		}
		time++;
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
	public static void paintWhite(DirectedGraph<Integer> dg) {
		Iterator<Integer> vIt = dg.getVerts();
		while (vIt.hasNext()) {
			Integer v = vIt.next();
			tasks.get(v).setColor(BLANCO);
		}
	}

	public static void paintTask(Integer t, int c) {
		tasks.get(t).setColor(c);
	}

}
