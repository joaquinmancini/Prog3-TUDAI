package prog3_tp4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class Simu {
	private static final int FAMILIAS = 5000;
	private static final int DIAS = 100;
	private static final int CAPACIDAD = 340;

	public static void main(String[] args) {
		CSVReader reader = new CSVReader("./data/familias.csv");
		ArrayList<Familia> familias = reader.read();
		ubicarFamilias(familias);
	}

	// Ubica cada familia en lo posible segun sus dias propuestos y segun la
	// capacidad de tal dia
	public static ArrayList<Dia> ubicarFamilias(ArrayList<Familia> f) {
		ArrayList<Dia> dias = new ArrayList<Dia>(DIAS);
		for (int i = 0; i < DIAS; i++) {
			dias.add(new Dia(i + 1));
		}
		Collections.sort(f);
		Iterator<Familia> it = f.iterator();
		while (it.hasNext() && !solucion(dias)) {
			Familia c = it.next();
//			for (int i = 0; i < 8; i++) {
			int indiceDia = c.preferenciaEn(0) - 1;
			Dia d = dias.get(indiceDia);
			if (agregable(d, c)) {
//				if (!(CAPACIDAD-(d.asistentes()+c.miembros())<4)||(CAPACIDAD-(d.asistentes()+c.miembros())==0)) {

					d.addFam(c);
					it.remove();
//				}
//				break;
			}
//			}
		}
		dias=ubRestoFamilias(f, dias);
		
		
		printCalendario(dias);
		printFamilias(f);
		printCalcBonos(dias);
		return dias;
	}
	
	
	public static ArrayList<Dia> ubRestoFamilias(ArrayList<Familia> f, ArrayList<Dia> dias){
		Iterator<Familia> it = f.iterator();
		while (it.hasNext() && !solucion(dias)) {
			Familia c = it.next();
			for (int i = 1; i < 8; i++) {
			int indiceDia = c.preferenciaEn(i) - 1;
			Dia d = dias.get(indiceDia);
			if (agregable(d, c)) {
					d.addFam(c);
					it.remove();
				break;
			}
			}
		}
		return dias;
	}
	

	// Devuelve num de familias que oudieron ser asignadas
	public static String assigned(ArrayList<Dia> d) {
		int count = 0;
		for (Dia dia : d) {
			count += dia.familias();
		}
		return "" + count + " familias asignadas.";
	}

	// Devuelve los asistentes por dia
	public static String assistants(ArrayList<Dia> d) {
		String ast = "";
		int i = 1;
		for (Dia dia : d) {
			ast += "D " + (i++) + ": " + dia.asistentes() + " ast, " + dia.familias() + " fams\n";
		}
		return ast;
	}

	// Comprueba si todas las familias fueron añadidas
	public static boolean solucion(ArrayList<Dia> d) {
		int count = 0;
		for (Dia dia : d) {
			count += dia.familias();
		}
		return count == FAMILIAS;
	}

	// Chequea si una familia candidata puede ser añadida a ese dia
	public static boolean agregable(Dia d, Familia f) {
		return d.asistentes() + f.miembros() <= CAPACIDAD;
	}

	// Calculo total de bonos
	public static int calculoBonos(ArrayList<Dia> d) {
		int sol = 0;
		for (Dia dia : d) {
			sol += dia.totalBonos();
		}
		return sol;
	}

	/*
	 * Prints de Total Bonos, Calendario de dias con asistentes por dia/familias por
	 * dia y de todas las familias postuladas
	 */
	public static void printCalcBonos(ArrayList<Dia> d) {
		System.out.println("T. Bonos: " + calculoBonos(d));
	}

	public static void printCalendario(ArrayList<Dia> d) {
		System.out.println(assigned(d));
		System.out.println(assistants(d));
	}

	public static void printFamilias(ArrayList<Familia> f) {
		for (Familia fam : f) {
			System.out.println(fam.getId() + ", " + fam.miembros() + " miembros.");
		}
	}
}
