package prog3_tp5;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class BackTracking {
	private final static int DAYS = 10;
	private HashMap<Integer, Dia> calendar;
	private HashMap<Integer, Dia> calendarSol;
	private int bonos = 999999;
	private long states = 0L;

	public BackTracking() {
		this.calendar = this.fillCalendar();
	}

	// Getter bonos
	public int getBonos() {
		return bonos;
	}

	// Getter estados finales
	public long getStates() {
		return states;
	}

	// Llena el calendario con la cantidad de dias de apertura
	private HashMap<Integer, Dia> fillCalendar() {
		HashMap<Integer, Dia> aux = new HashMap<Integer, Dia>();
		for (int i = 1; i < DAYS + 1; i++) {
			aux.put(i, new Dia(i));
		}
		return aux;
	}

	// Inicio de BT
	public HashMap<Integer, Dia> getCalendar(ArrayList<Familia> f) {
		backtrakingR(0, f);
		return calendarSol;
	}

	// Recursividad de BT
	private void backtrakingR(int famIndex, ArrayList<Familia> f) {
		if (famIndex == f.size()) {
			states++;
//			if (states % 50 == 0) {
//				System.out.println(states);
//			}
//			if (!this.exceedsCap()) {
			int tBono = this.calcBono(0);
			if (tBono < this.bonos) {
				this.bonos = tBono;
				this.calendarSol = (HashMap<Integer, Dia>) calendar.clone();
//					System.out.println("bono es " + this.bonos);
			}
//			}
		} else {
			Familia familia = f.get(famIndex);
			for (int dayIndex = 0; dayIndex < 3; dayIndex++) {
				int day = familia.preferenciaEn(dayIndex);
				if (this.calendar.get(day).fitsFamily(familia) && betterBono(familia, day)) {
					this.calendar.get(day).addFamily(familia);
					famIndex++;
					this.backtrakingR(famIndex, f);
					famIndex--;
					this.calendar.get(day).remFamily(famIndex);
				}
			}

		}
	}

	// Si alguno de los dias de la solucion excede su capacidad
	public boolean exceedsCap() {
		Iterator<Dia> dIt = this.dayIterator(0);
		while (dIt.hasNext()) {
			if (dIt.next().capExceeded()) {
				return true;
			}
		}
		return false;
	}

	// Calculo de bono en instancia de agregacion de familia
	public boolean betterBono(Familia f, int dia) {
		int bonoFam = 0;
		if (f.indiceDePreferencia(dia) != 0) {
			dia = 25 + 10 * f.miembros() + 5 * f.indiceDePreferencia(dia);
		}
		return this.calcBono(0) + bonoFam < bonos;
	}

	// Calculo de bonos de la solucion
	public int calcBono(int op) {
		int bono = 0;
		Iterator<Dia> dIt = this.dayIterator(op);
		while (dIt.hasNext()) {
			bono += dIt.next().bonoD();
		}
		return bono;
	}

	// Iterador de los dias agendados
	private Iterator<Dia> dayIterator(int op) {
		if (op == 1) {
			return this.calendarSol.values().iterator();
		}
		return this.calendar.values().iterator();
	}
}
