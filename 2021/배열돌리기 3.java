import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution16935 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] map;
	static int N, M, R;

	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		R = Integer.parseInt(input[2]);

		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}

		String[] selects = br.readLine().split(" ");
		for (int i = 0; i < selects.length; i++) {
			switch (Integer.parseInt(selects[i])) {
			case 1:
				operator1();
				break;
			case 2:
				operator2();
				break;
			case 3:
				operator3();
				break;
			case 4:
				operator4();
				break;
			case 5:
				operator5();
				break;
			case 6:
				operator6();
				break;
			default:
				break;
			}
		}
		printArr(map);
	}

	private static void printArr(int[][] arr) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[0].length; j++) {
				sb.append(arr[i][j] + " ");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}

	static void operator1() {
		int row = map.length;
		int col = map[0].length;
		int[][] tmp = new int[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				tmp[i][j] = map[row - i - 1][j];
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map[i][j] = tmp[i][j];
			}
		}
	}

	static void operator2() {
		int row = map.length;
		int col = map[0].length;
		int[][] tmp = new int[row][col];

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				tmp[i][j] = map[i][col - j - 1];
			}
		}

		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				map[i][j] = tmp[i][j];
			}
		}
	}

	static void operator3() {
		int row = map[0].length;
		int col = map.length;
		int[][] tmp = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				tmp[i][j] = map[col - j - 1][i];
			}
		}
		map = deepCopy(tmp);
	}

	static void operator4() {
		int row = map[0].length;
		int col = map.length;
		int[][] tmp = new int[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				tmp[i][j] = map[j][row - 1 - i];
			}
		}
		map = deepCopy(tmp);
	}

	static void operator5() {
		int xMid = map.length / 2;
		int yMid = map[0].length / 2;
		int[][] temp = new int[map.length][map[0].length];
		// 4 => 1
		for (int i = 0; i < xMid; i++) {
			for (int j = 0; j < yMid; j++) {
				temp[i][j] = map[xMid + i][j];
			}
		}
		// 1=> 2
		for (int i = 0; i < xMid; i++) {
			for (int j = yMid; j < yMid * 2; j++) {
				temp[i][j] = map[i][j - yMid];
			}
		}

		// 2 => 3
		for (int i = xMid; i < xMid * 2; i++) {
			for (int j = yMid; j < yMid * 2; j++) {
				temp[i][j] = map[i - xMid][j];
			}
		}

		// 3=> 4
		for (int i = xMid; i < xMid * 2; i++) {
			for (int j = 0; j < yMid; j++) {
				temp[i][j] = map[i][j + yMid];
			}
		}
		map = deepCopy(temp);

	}

	static void operator6() {
		int xMid = map.length / 2;
		int yMid = map[0].length / 2;
		int[][] temp = new int[map.length][map[0].length];
		// 2 => 1
		for (int i = 0; i < xMid; i++) {
			for (int j = 0; j < yMid; j++) {
				temp[i][j] = map[i][j + yMid];
			}
		}
		// 3 => 2
		for (int i = 0; i < xMid; i++) {
			for (int j = yMid; j < yMid * 2; j++) {
				temp[i][j] = map[i + xMid][j];
			}
		}

		// 4 => 3
		for (int i = xMid; i < xMid * 2; i++) {
			for (int j = yMid; j < yMid * 2; j++) {
				temp[i][j] = map[i][j - yMid];
			}
		}

		// 1 => 4
		for (int i = xMid; i < xMid * 2; i++) {
			for (int j = 0; j < yMid; j++) {
				temp[i][j] = map[i - xMid][j];
			}
		}
		map = deepCopy(temp);

	}

	private static int[][] deepCopy(int[][] original2) {
		if (original2 == null)
			return null;
		int[][] result = new int[original2.length][original2[0].length];

		for (int i = 0; i < original2.length; i++) {
			System.arraycopy(original2[i], 0, result[i], 0, original2[0].length);
		}

		return result;
	}
}