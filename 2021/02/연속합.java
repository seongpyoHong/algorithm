import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1912 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		int[] cache = new int[N];
		String[] line = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			if (i == 0) {
				cache[i] = Integer.parseInt(line[i]);
			} else {
				cache[i] = (cache[i - 1] + Integer.parseInt(line[i]) < Integer.parseInt(line[i]))
						? Integer.parseInt(line[i])
						: cache[i - 1] + Integer.parseInt(line[i]);
			}
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			if (max < cache[i])
				max = cache[i];
		}
		System.out.println(max);
	}
}