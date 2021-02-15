import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution2605 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		String[] input = br.readLine().split(" ");
		ArrayList<Integer> line = new ArrayList<>();
		for (int i = 0; i < input.length; i++) {
			line.add(Integer.parseInt(input[i]), i + 1);
		}

		for (int i = line.size() - 1; i >= 0; i--) {
			System.out.print(line.get(i) + " ");
		}
	}
}