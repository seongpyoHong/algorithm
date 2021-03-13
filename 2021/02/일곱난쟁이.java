import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution2309 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] people = new int[9];
	static int goal, except1, except2;

	public static void main(String[] args) throws NumberFormatException, IOException {
		int sum = 0;
		for (int i = 0; i < 9; i++) {
			people[i] = Integer.parseInt(br.readLine());
			sum += people[i];
		}
		goal = sum - 100;

		for (int i = 0; i < people.length; i++) {
			for (int j = 0; j < people.length; j++) {
				if (i == j)
					continue;
				if (people[i] + people[j] == goal) {
					printSeven(i, j);
					return;
				}
			}
		}
	}

	static void printSeven(int x, int y) {
		int[] temp = new int[7];
		int idx = 0;
		for (int i = 0; i < people.length; i++) {
			if (i == x || i == y)
				continue;
			temp[idx++] = people[i];
		}

		Arrays.sort(temp);
		for (int i = 0; i < temp.length; i++) {
			System.out.println(temp[i]);
		}
	}
}