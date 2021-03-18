import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {
	static final int VERTICAL = 0, HORIZONTAL = 1, DIAGONAL = 2;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] map;
	static int N, total;
	static int[][] dx = {{0,0, 1}, {0,1, 1}, {0, 1, 1}}, dy = {{1,0, 1}, {0,0,1}, {1, 0, 1}};
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		movePipe(VERTICAL, 0, 1);
		System.out.println(total);
	}

	private static void movePipe(int type, int x, int y) {
		
		if (x == N - 1 && y == N - 1) {
			total++;
			return;
		}
		for (int i = 0; i < dx[type].length; i++) {
			int dX = dx[type][i];
			int dY = dy[type][i];
			if (dX == 0 && dY == 0) continue;
			
			int nX = x + dX;
			int nY = y + dY;
			if (nX >= N || nY >= N || map[x][nY] == 1 || map[nX][y] == 1 || map[nX][nY] == 1) continue;
			movePipe(i, nX, nY);
		
		}
	}
}