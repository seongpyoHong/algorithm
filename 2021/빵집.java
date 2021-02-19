import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution3109 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[][] map;
	static boolean[][] check;
	static int R, C, cnt, max = Integer.MIN_VALUE;
	static boolean isReached;

	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		R = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		map = new char[R][C];
		check = new boolean[R][C];

		for (int i = 0; i < R; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < C; j++) {
				map[i][j] = line[j];
			}
		}
	
		for (int i = 0; i < R; i++) {
			if (isReached) continue;
			isReached = false;
			buildPipe(i, 0);
			if (isReached) {
				isReached = false;
				cnt++;
			}
		}
		System.out.println(cnt);
	}

static void buildPipe(int row, int col) {

	if (col == C - 1) {
		isReached = true;
		return;
	}

	check[row][col] = true;
	if (isvalid(row - 1, col + 1) && !isReached)
		buildPipe(row - 1, col + 1);
	if (isvalid(row, col + 1) && !isReached)
		buildPipe(row, col + 1);
	if (isvalid(row + 1, col + 1) && !isReached)
		buildPipe(row + 1, col + 1);
}

	static boolean isvalid(int row, int col) {
		if (row < 0 || row >= R || map[row][col] == 'x' || check[row][col])
			return false;
		return true;
	}
}