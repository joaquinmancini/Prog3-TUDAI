package prog3_tp2;

import java.util.*;

public class Simu {

	public static void main(String[] args) {
		ArrayList<Integer> arr = new ArrayList<Integer>();
		Integer j = 1;
		for (int i = 0; i < 10; i++) {
			arr.add(j);
			j++;
		}

		if (checkSortASC(arr, arr.size())) {
			System.out.println("ordered");
		} else {
			System.out.println("not ordered");
		}
	}

	// EJ1 TP2
	public static boolean checkSortASC(ArrayList<Integer> arr, int index) {
		if (arr == null || arr.size() < 2 || index < 2) {
			return true;
		}
		if (arr.get(index - 1) > arr.get(index - 2)) {
			return checkSortASC(arr, index - 1);
		}
		return false;
	}
}
