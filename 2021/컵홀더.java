import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		char[] line = br.readLine().toCharArray();
		ArrayList<String> seat = new ArrayList<>();

		boolean isPreviousL = false;
		for (int i = 0; i < N; i++) {
			String current = String.valueOf(line[i]);
			if (!isPreviousL && current.equals("L")) {
				isPreviousL = true;
			} else if (isPreviousL && current.equals("L")) {
				isPreviousL = false;
				seat.add(current);
				continue;
			}

			seat.add("*");
			seat.add(current);
		}
		seat.add("*");
		
		int cnt = 0;
		for (int i = 0; i < seat.size(); i++) {
			if (seat.get(i).equals("*") || seat.get(i).equals("-"))
				continue;

			if (seat.get(i - 1).equals("*")) {
				seat.set(i - 1, "-");
			} else if (seat.get(i + 1).equals("*")) {
				seat.set(i + 1, "-");
			} else {
				cnt++;
			}
		}

		System.out.println(N - cnt);
	}

}
