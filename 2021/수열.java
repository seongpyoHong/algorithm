import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2599 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, K;
	static int[] cache;
	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		K = Integer.parseInt(input[1]);
		
		cache = new int[N];
		String[] line = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			cache[i] = Integer.parseInt(line[i]);
		}
		
		
		int sum = 0;
		for (int i = 0; i < K; i++) {
			sum += cache[i];
		}
		int max = sum;
		
		for (int i = 1; i < line.length - K + 1; i++) {
			sum += cache[i + K - 1] - cache[i-1];
			if (max < sum) max = sum;
		}
		
		System.out.println(max);
	}
}