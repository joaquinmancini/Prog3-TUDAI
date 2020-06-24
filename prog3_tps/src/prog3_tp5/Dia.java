package prog3_tp5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Dia {
	private static final int CAP = 30;
	private int id;
	private HashMap<Integer, Familia> crowd;

	public Dia(int id) {
		this.id = id;
		this.crowd = new HashMap<Integer, Familia>();
	}

	// Retorna identificador de dia
	public int getId() {
		return this.id;
	}

	// Añade familia
	public void addFamily(Familia f) {
		this.crowd.put(f.getId(), f);
	}

	// Intenta remover familia
	public void remFamily(int id) {
		crowd.remove(id);
	}

	// Retorna cantidad de asistentes
	public int audience() {
		int a = 0;
		Iterator<Familia> fIt = this.familyIterator();
		Familia f;
		while (fIt.hasNext()) {
			f = fIt.next();
			a += f.miembros();
		}
		return a;
	}

	// Retorna si la familia entra en el dia
	public boolean fitsFamily(Familia f) {
		return (this.audience() + f.miembros() <= CAP);
	}

	// Retorna si la capacidad fue superada
	public boolean capExceeded() {
		return this.audience() > CAP;
	}

	// Iterador de las familias en el dia
	public Iterator<Familia> familyIterator() {
		return crowd.values().iterator();
	}
}
