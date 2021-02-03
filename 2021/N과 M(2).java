import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution3 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] arr;
	static int[] perms;
	static int M, N;
	public static void main(String[] args) throws IOException {
		StringTokenizer sc = new StringTokenizer(br.readLine());
		N = Integer.parseInt(sc.nextToken());
		M = Integer.parseInt(sc.nextToken());
		arr = new int[N];
		perms = new int[M];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i+1;
		}
		permutation(0, 0);
	}
	
	private static void permutation(int order, int start) {
		if (order == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(perms[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = start ; i < arr.length; i++) {
			perms[order] = arr[i];
			permutation(order+1, i + 1);
		}
	}
}