package prog3_tp4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class CSVReader {
	private String path;

	public CSVReader(String path) {
		this.path = path;
	}

	public ArrayList<Familia> read() {
		ArrayList<Familia> families = new ArrayList<Familia>();

		ArrayList<String> lines = this.readContent();
		for (String line : lines)
			families.add(this.parseLine(line));
		return families;
	}

	private Familia parseLine(String line) {
		String[] splitted = line.split(",");

		int id = Integer.valueOf(splitted[0]);
		int members = Integer.valueOf(splitted[splitted.length - 1]);

		int[] preferredDays = new int[8];
		for (int index = 1; index < splitted.length - 1; index++) {
			preferredDays[index - 1] = Integer.valueOf(splitted[index]);
		}

		return new Familia(id, members, preferredDays);
	}

	private ArrayList<String> readContent() {
		ArrayList<String> lines = new ArrayList<String>();

		File file = new File(this.path);
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			fileReader = new FileReader(file);
			bufferedReader = new BufferedReader(fileReader);
			String line = bufferedReader.readLine(); // Salteo la primera linea
			while ((line = bufferedReader.readLine()) != null) {
				line = line.trim();
				lines.add(line);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (bufferedReader != null)
				try {
					bufferedReader.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
		}

		return lines;
	}
}
