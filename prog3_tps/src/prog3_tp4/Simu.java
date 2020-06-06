package prog3_tp4;

import java.util.ArrayList;

public class Simu {

	public static void main(String[] args) {
		CSVReader reader = new CSVReader("./data/familias.csv");
		ArrayList<Familia> familias = reader.read();
		SolucionTaller sT = new SolucionTaller(familias);
		//*IMPRESION DE RESULTADOS*//
		System.out.println(sT.assigned());
		System.out.println(sT.assistants());
		System.out.println(sT.printBonos());
//		System.out.println(sT.lejania());
	}
}