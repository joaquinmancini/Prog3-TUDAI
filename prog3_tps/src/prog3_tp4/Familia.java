package prog3_tp4;

import java.util.Arrays;

public class Familia implements Comparable<Familia> {
	private int id;
	private int miembros;
	private int[] diasPreferidos;
	private int indLejania;

	public Familia(int id, int miembros, int... diasPreferidos) {
		this.id = id;
		this.miembros = miembros;
		this.diasPreferidos = diasPreferidos;
	}

	/* Id de la familia */
	public int getId() {
		return id;
	}

	/* Retorna la cantidad de miembros de la familia. */
	public int miembros() {
		return miembros;
	}

	// Retorna cuan lejos del dia preferido fue asignado
	public int getIndLejania() {
		return indLejania;
	}

	public void setIndLejania(int indLejania) {
		this.indLejania = indLejania;
	}

	/*
	 * Dado un indice entre 0 y 7, retorna el d�a preferido por la familia para ese
	 * indice.
	 */
	public int preferenciaEn(int indice) {
		return this.diasPreferidos[indice];
	}

	/* Retorna el d�a preferido de la familia */
	public int diaPreferido() {
		return preferenciaEn(0);
	}

	/*
	 * Dado un dia pasado por parametro, indica el orden de ese dia en el top 5 de
	 * preferencias. Si el dia no forma parte de las preferencias del usuario, se
	 * retorna -1.
	 */
	public int indiceDePreferencia(int dia) {
		for (int indice = 0; indice < diasPreferidos.length; indice++)
			if (dia == diasPreferidos[indice])
				return indice;
		return -1;
	}

	@Override
	public String toString() {
		return "Familia: id=" + id + ", miembros=" + miembros + ", preferencias=" + Arrays.toString(diasPreferidos);
	}

	@Override
	public int compareTo(Familia f) {
		return Integer.compare(this.miembros, f.miembros);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (obj == this)
			return true;
		if (!(obj instanceof Familia))
			return false;
		Familia o = (Familia) obj;
		return o.id == this.id;
	}

	public void diasP() {
		System.out.println("Familia " + this.id + " (" + this.miembros + "m) dist " + this.indLejania);
		for (int i : this.diasPreferidos) {
			System.out.print(i + "|");
		}
		System.out.println();
	}
}
