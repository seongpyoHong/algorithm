import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2578 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] map = new int[5][5];
	static boolean[][] visited = new boolean[5][5];

	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 5; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < 5; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		int ret = 0;
		int cnt = 0;
		for (int i = 0; i < 5; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < 5; j++) {
				cnt += 1;
				int[] point = findPoint(Integer.parseInt(line[j]));
				visited[point[0]][point[1]] = true;
				if (ret == 0 && getBingo(point[0], point[1]) >= 3) {
					ret = cnt;
				}
			}
		}
		System.out.println(ret);
	}

	static int[] findPoint(int target) {
		int[] ret = new int[2];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (map[i][j] == target) {
					ret[0] = i;
					ret[1] = j;
					return ret;
				}
			}
		}
		return ret;
	}

	static int getBingo(int x, int y) {
		int totalCnt = 0;
		for (int i = 0; i < 5; i++) {
			int xCnt = 0;
			int yCnt = 0;
			for (int j = 0; j < 5; j++) {
				if (visited[i][j]) {
					xCnt += 1;
				}
				if (visited[j][i]) {
					yCnt += 1;
				}
			}
			if (xCnt == 5)
				totalCnt += 1;
			if (yCnt == 5)
				totalCnt += 1;
		}

		int leftCnt = 0;
		int rightCnt = 0;
		for (int i = 0; i < 5; i++) {
			if (visited[i][i])
				leftCnt += 1;
			if (visited[i][4-i])
				rightCnt += 1;
		}
		if (leftCnt == 5)
			totalCnt += 1;
		if (rightCnt == 5)
			totalCnt += 1;
		return totalCnt;
	}
}