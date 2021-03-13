import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static HashSet<Integer> cache = new HashSet<>();

	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		int A = Integer.parseInt(input[0]);
		int P = Integer.parseInt(input[1]);

		cache.add(A);
		boolean isDup = false;
		while (true) {
			int next = 0;
			while (A > 0) {
				next += Math.pow(A % 10, P);
				A /= 10;
			}

			if (cache.contains(next)) {
				if (!isDup)
					isDup = true;
				cache.remove(next);
			} else if (!cache.contains(next)) {
				if (isDup) break;
				cache.add(next);
			}
			A = next;
		}

		System.out.println(cache.size());
	}
}
