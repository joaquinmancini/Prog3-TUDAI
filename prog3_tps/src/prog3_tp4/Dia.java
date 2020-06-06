package prog3_tp4;

import java.util.ArrayList;
import java.util.Iterator;

public class Dia {
	private int id;
	private ArrayList<Familia> familias;

	public Dia(int id) {
		this.id = id;
		this.familias = new ArrayList<Familia>();
	}

	// Añade una familia a la coleccion familias
	public boolean addFam(Familia f) {
		return familias.add(f);
	}

	// Elimina una familia a la coleccion familias
	public boolean remFam(Familia f) {
		return familias.remove(f);
	}

	// Retorna la cantidad de familias asignadas al dia
	public int familias() {
		return familias.size();
	}

	// Retorna iterador para recorrer el arreglo de familias
	public Iterator<Familia> getFam() {
		return familias.iterator();
	}

	// Retorna la cantidad de personas que concurriran ese dia
	public int asistentes() {
		int as = 0;
		for (Familia familia : familias) {
			as += familia.miembros();
		}
		return as;
	}

	// Caltulo del total de bonos del dia
	public int totalBonos() {
		int sol = 0;
		for (Familia familia : familias) {
			if (familia.diaPreferido() != this.id) {
				sol += 25 + (10 * familia.miembros()) + (5 * familia.indiceDePreferencia(this.id));
			}
		}
		return sol;
	}
}
