import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] arr;
	static int[] perms;
	static boolean[] used;
	static int M, N;
	public static void main(String[] args) throws IOException {
		StringTokenizer sc = new StringTokenizer(br.readLine());
		N = Integer.parseInt(sc.nextToken());
		M = Integer.parseInt(sc.nextToken());
		arr = new int[N];
		perms = new int[M];
		used = new boolean[N];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = i+1;
		}
		permutation(0);
	}
	
	private static void permutation(int order) {
		if (order == M) {
			for (int i = 0; i < M; i++) {
				System.out.print(perms[i] + " ");
			}
			System.out.println();
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (used[i]) continue;
			used[i] = true;
			perms[order] = arr[i];
			permutation(order+1);
			used[i] = false;
		}
	}
}