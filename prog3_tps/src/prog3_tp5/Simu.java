package prog3_tp5;

import java.util.ArrayList;
import java.util.HashMap;

public class Simu {

	public static void main(String[] args) {
		CSVReader reader = new CSVReader("./data/familias-1.csv");
		ArrayList<Familia> familias = reader.read();

		CSVReader reader2 = new CSVReader("./data/familias-2.csv");
		ArrayList<Familia> familias2 = reader2.read();
		/*-------------------------------------------------------*/
		BackTracking bt1 = new BackTracking();
		HashMap<Integer, Dia> cal = bt1.getCalendar(familias2);
		System.out.println("bono es $" + bt1.getBonos());
		System.out.println("Estados finales " + bt1.getStates());
	}

}
