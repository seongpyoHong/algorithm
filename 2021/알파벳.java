import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution1987 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int R, C, maxDistance = Integer.MIN_VALUE;
	static String[][] map;
	static boolean[][] check;
	static ArrayList<String> previous;
	static int[] dx = { 0, 1, 0, -1 };
	static int[] dy = { 1, 0, -1, 0 };

	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		map = new String[R][C];
		check = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = String.valueOf(line[j]);
			}
		}

		previous = new ArrayList<String>();
		traversal(0, 0, 1);
		System.out.println(maxDistance);
	}

	private static void traversal(int x, int y, int distance) {
		maxDistance = Math.max(maxDistance, distance);
		check[x][y] = true;
		previous.add(map[x][y]);
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i], nextY = y + dy[i];
			if (!isValid(nextX, nextY))
				continue;
			traversal(nextX, nextY, distance + 1);
		}
		
		previous.remove(map[x][y]);
		check[x][y] = false;
	}

	static boolean isValid(int x, int y) {
		if (x < 0 || y < 0 || x >= R || y >= C || previous.contains(map[x][y]) || check[x][y])
			return false;
		return true;
	}
}