import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		int[][] triangle = new int[N][];
		int[][] cache = new int[N][];
		for (int i = 0; i < N; i++) {
			cache[i] = new int[i + 1];
			triangle[i] = new int[i + 1];
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < triangle[i].length; j++) {
				triangle[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		cache[0][0] = triangle[0][0];
		for (int i = 1; i < N; i++) {
			cache[i][0] = cache[i-1][0] + triangle[i][0];
			for (int j = 1; j < i; j++) {
				cache[i][j] = Math.max(cache[i-1][j] + triangle[i][j], cache[i-1][j-1] + triangle[i][j]);
			}
			cache[i][i] = cache[i-1][i-1] + triangle[i][i];
		}
		
		System.out.println(getMax(cache[N-1]));
	}
	
	static int getMax(int[] arr) {
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < arr.length; i++) {
			if (max >=  arr[i]) continue;
			max = arr[i];
		}
		
		return max;
	}
}