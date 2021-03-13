import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1992 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static String[][] map;
	static int N;

	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		map = new String[N][N];
		for (int i = 0; i < N; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < line.length; j++) {
				map[i][j] = String.valueOf(line[j]);
			}
		}
		System.out.println(getQuadTree(0, 0, N));
	}

	static String getQuadTree(int x, int y, int size) {

		if (size == 1) {
			return map[x][y];
		}
		String leftUp = getQuadTree(x, y, size / 2);
		String rightUp = getQuadTree(x, y + size / 2, size / 2);
		String leftDown = getQuadTree(x + size / 2, y, size / 2);
		String rightDown = getQuadTree(x + size / 2, y + size / 2, size / 2);

		StringBuilder sb = new StringBuilder();
		if (!leftUp.equals("1") && !leftUp.equals("0")) {
			sb.append("(" + leftUp + rightUp + leftDown + rightDown + ")");
		} else if (!leftUp.equals(rightUp) || !leftUp.equals(leftDown) || !leftUp.equals(rightDown)) {
			sb.append("(" + leftUp + rightUp + leftDown + rightDown + ")");
		} else {
			sb.append(leftUp);
		}
		return sb.toString();
	}
}