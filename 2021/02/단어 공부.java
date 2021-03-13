import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


public class Solution1157 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] alphabet = new int['Z' - 'A' + 1];

	public static void main(String[] args) throws IOException {
		char[] input = br.readLine().toUpperCase().toCharArray();
		for (int i = 0; i < input.length; i++) {
			alphabet[input[i] - 'A'] += 1;
		}

		int maxIdx = 0;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < alphabet.length; i++) {
			if (alphabet[i] == 0 || max >= alphabet[i])
				continue;
			max = alphabet[i];
			maxIdx = i;
		}

		Arrays.sort(alphabet);

		if (alphabet.length != 1 && alphabet[alphabet.length - 1] == alphabet[alphabet.length - 2])
			System.out.println("?");
		else
			System.out.println((char) ('A' + maxIdx));
	}
}