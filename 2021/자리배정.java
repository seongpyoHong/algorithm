import java.util.Scanner;

public class Solution10157 {
	static Scanner sc = new Scanner(System.in);
	static int C, R, K;
	static int[][] map;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) {
		C = sc.nextInt();
		R = sc.nextInt();
		K = sc.nextInt();

		map = new int[C][R];
		int dIdx = 0;
		int seatNum = 1;
		int x = 0;
		int y = 0;
		while (true) {
			if (seatNum++ == K) {
				System.out.println((x + 1) + " " + (y + 1));
				return;
			}
			if (map[x][y] != 0) {
				System.out.println(0);
				return;
			}
			
			map[x][y] = seatNum;
			if (!isValid(x, y, dIdx))
				dIdx = (dIdx + 1) % 4;
			
			x += dx[dIdx];
			y += dy[dIdx];
		}

	}

	private static boolean isValid(int x, int y, int dIdx) {
		int nextX = x + dx[dIdx];
		int nextY = y + dy[dIdx];
		if (nextX < 0 || nextY < 0 || nextX >= C || nextY >= R || map[nextX][nextY] != 0)
			return false;
		return true;
	}
}