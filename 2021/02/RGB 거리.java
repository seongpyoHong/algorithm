import java.util.Scanner;

public class Solution1149 {
	static Scanner sc = new Scanner(System.in);
	static int[][] cache;

	public static void main(String[] args) {
		int N = sc.nextInt();
		int[][] map = new int[N][3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < 3; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		System.out.println(getResult(map, N));
	}

	static int getResult(int[][] map, int N) {
		cache = new int[N][3];
		cache[0][0] = map[0][0];
		cache[0][1] = map[0][1];
		cache[0][2] = map[0][2];
		
		for (int i = 1; i < N; i++) {
			cache[i][0] = Math.min(cache[i-1][1] + map[i][0], cache[i-1][2] + map[i][0]);
			cache[i][1] = Math.min(cache[i-1][0] + map[i][1], cache[i-1][2] + map[i][1]);
			cache[i][2] = Math.min(cache[i-1][1] + map[i][2], cache[i-1][0] + map[i][2]);
		}
		
		return min(cache[N-1]);
	}

	static int min(int[] arr) {
		return Math.min(arr[0], Math.min(arr[1], arr[2]));
	}
}