package prog3_tp4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class SolucionTaller {
	private static final int FAMILIAS = 5000;
	private static final int DIAS = 100;
	private static final int CAPACIDAD = 340;
	private ArrayList<Dia> cronograma;
	private int totalBono;

	public SolucionTaller(ArrayList<Familia> f) {
		this.cronograma = this.ubicarFamilias(f);
		this.totalBono = this.calculoBonos(cronograma);
	}

	public ArrayList<Dia> copiaCronograma() {
		return (ArrayList<Dia>) cronograma.clone();
	}

	public int getTotalBono() {
		return totalBono;
	}

	// Ubica cada familia en lo posible segun sus dias propuestos y segun la
	// capacidad de tal dia
	public ArrayList<Dia> ubicarFamilias(ArrayList<Familia> f) {
		ArrayList<Dia> dias = new ArrayList<Dia>(DIAS);
		for (int i = 0; i < DIAS; i++) {
			dias.add(new Dia(i + 1));
		}
		Collections.sort(f);
		Iterator<Familia> it = f.iterator();
		while (it.hasNext() && !solucion(dias)) {
			Familia c = it.next();
			int indiceDia = c.preferenciaEn(0) - 1;
			Dia d = dias.get(indiceDia);
			if (agregable(d, c)) {
				c.setIndLejania(0);
				d.addFam(c);
				it.remove();
			}
		}
		dias = ubRestoFamilias(f, dias);
		return dias;
	}

	// Ubica a las familias que no pudieron ubicarse en su dia favorito
	public ArrayList<Dia> ubRestoFamilias(ArrayList<Familia> f, ArrayList<Dia> dias) {
		Iterator<Familia> it = f.iterator();
		while (it.hasNext() && !solucion(dias)) {
			Familia c = it.next();
			for (int i = 1; i < 8; i++) {
				int indiceDia = c.preferenciaEn(i) - 1;
				Dia d = dias.get(indiceDia);
				if (agregable(d, c)) {
					c.setIndLejania(c.indiceDePreferencia(c.diaPreferido()) - i);
					d.addFam(c);
					it.remove();
					break;
				}
			}
		}
		return dias;
	}

	// Comprueba si todas las familias fueron añadidas
	public boolean solucion(ArrayList<Dia> d) {
		int count = 0;
		for (Dia dia : d) {
			count += dia.familias();
		}
		return count == FAMILIAS;
	}

	// Chequea si una familia candidata puede ser añadida a ese dia
	public boolean agregable(Dia d, Familia f) {
		return d.asistentes() + f.miembros() <= CAPACIDAD;
	}

	// Calculo total de bonos
	public int calculoBonos(ArrayList<Dia> d) {
		int sol = 0;
		for (Dia dia : d) {
			sol += dia.totalBonos();
		}
		return sol;
	}

	// Devuelve total de bono en String
	public String printBonos() {
		return "T. Bonos: $" + totalBono;
	}

	// Devuelve num de familias que pudieron ser asignadas
	public String assigned() {
		int count = 0;
		for (Dia dia : cronograma) {
			count += dia.familias();
		}
		return "" + count + " familias asignadas.";
	}

	// Devuelve los asistentes por dia
	public String assistants() {
		String ast = "";
		int i = 1;
		for (Dia dia : cronograma) {
			ast += "D " + (i++) + ": " + dia.asistentes() + " ast, " + dia.familias() + " fams\n";
		}
		return ast;
	}

	public String lejania() {
		String s = "";
		for (Dia dia : cronograma) {
			s += dia.iL();
		}
		return s;
	}
}
