import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution9251 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int[][] cache;

	public static void main(String[] args) throws IOException {
		String row = br.readLine();
		String col = br.readLine();
		cache = new int [row.length() + 1][col.length() + 1];

		for (int i = 1; i < row.length() + 1; i++) {
			for (int j = 1; j < col.length() + 1; j++) {
				if (row.charAt(i - 1) == col.charAt(j - 1)) {
					cache[i][j] = cache[i-1][j-1] + 1;
				} else {
					cache[i][j] = Integer.max(cache[i-1][j], cache[i][j-1]);
				}
			}
		}
		System.out.println(cache[row.length()][col.length()]);
	}
}