import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution12865 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static class Product {
		int weight = 0;
		int value = 0;

		public Product() {
		}

		public Product(int weight, int value) {
			this.weight = weight;
			this.value = value;
		}

	}

	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		int[][] cache = new int[N + 1][K + 1];
		Product[] products = new Product[N + 1];

		for (int i = 1; i < products.length; i++) {
			String[] line = br.readLine().split(" ");
			products[i] = new Product(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		}

		for (int i = 1; i < products.length; i++) {
			int j = K;
			for (; j >= products[i].weight; j--) {
				cache[i][j] = Math.max(cache[i-1][j - products[i].weight] + products[i].value, cache[i - 1][j]);
			}
			
			for (; j >= 0; j--) {
				cache[i][j] = cache[i-1][j];
			}
		}

		int max = Integer.MIN_VALUE;
		for (int i = 1; i < K + 1; i++) {
			if (max >= cache[N][i])
				continue;
			max = cache[N][i];
		}

		System.out.println(max);
	}
}