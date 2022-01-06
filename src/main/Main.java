package main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);

		System.out.println("Nhập dãy số có dạng 'x1,x2,x3,...,xn' enter để kết thúc:");
		String input = scanner.nextLine();
		scanner.close();
		String numInputs[] = input.split(",");

		Map<Integer, Integer> negatives = new TreeMap<Integer, Integer>((Integer n1, Integer n2) -> n1 - n2);
		Map<Integer, Integer> positives = new TreeMap<Integer, Integer>((Integer n1, Integer n2) -> n2 - n1);

		for (String num : numInputs) {
			int temp;
			try {
				temp = Integer.parseInt(num);
			} catch (NumberFormatException e) {
				System.out.println("Sai định dạng đầu vào");
				return;
			}
			if (temp >= 0) {
				positives.put(temp, positives.containsKey(temp) ? positives.get(temp) + 1 : 1);
				continue;
			}
			if (temp < 0) {
				negatives.put(temp, negatives.containsKey(temp) ? negatives.get(temp) + 1 : 1);
			}

		}

		StringBuilder result = new StringBuilder();
		result.append(combineResult(negatives));
		result.append(combineResult(positives));

		try {
			FileWriter writer = new FileWriter("./ketqua.txt");
			writer.write(result.toString());
			writer.close();
			System.out.println("done");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private static String combineResult(Map<Integer, Integer> map) {
		StringBuilder result = new StringBuilder();
		for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
			for (var i = 0; i < entry.getValue(); i++) {
				result.append(entry.getKey()).append(" ");
			}
		}
		return result.toString();
	}

}
