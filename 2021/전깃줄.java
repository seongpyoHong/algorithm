import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution2565 {
	static class Pair implements Comparable<Pair> {
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Pair o) {
			if (this.x > o.x)
				return 1;
			else
				return -1;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] cache = new int[501];
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		Pair[] input = new Pair[N];
		
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			input[i] = new Pair(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		}
		
		Arrays.sort(input);
		
		for (Pair p : input) {
			int current = p.y;
			int max = Integer.MIN_VALUE;
			for (int i = current - 1; i >= 0; i--) {
				if (max < cache[i])
					max = cache[i];
			}
			
			cache[current] = max + 1; 
		}
		
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < cache.length; i++) {
			if (max < cache[i])
				max = cache[i];
		}
		
		System.out.println(N - max);
	}
}