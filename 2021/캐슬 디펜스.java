import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution17135 {
	static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] originMap;
	static ArrayList<Pair> archer = new ArrayList<>();
	static int[] flag;
	static int N, M, D;

	public static void main(String[] args) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		D = Integer.parseInt(line[2]);
		originMap = new int[N][M];
		// initialize enemies
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				originMap[i][j] = Integer.parseInt(input[j]);
			}
		}

		// initialize archer
		for (int i = 0; i < M; i++) {
			archer.add(new Pair(N, i));
		}

		flag = new int[M];
		int idx = M, cnt = 3, max = Integer.MIN_VALUE;
		while (cnt-- > 0)
			flag[--idx] = 1;
		do {
			max = Math.max(max, getKilledCount());
		} while (nextPermutation());

		System.out.println(max);
	}

	private static int getKilledCount() {
		int cnt = 0;
		int[][] map = deepCopy(originMap);
		for (int round = 0; round < N; round++) {
			ArrayList<Pair> killed = new ArrayList<>();
			for (int i = 0; i < M; i++) {
				if (flag[i] == 0)
					continue;
				Pair curArcher = archer.get(i);
				int killX = N;
				int killY = M;
				int minDistance = Integer.MAX_VALUE;
				for (int j = N - 1; j >= 0; j--) {
					for (int k = 0; k < M; k++) {
						int curDist = Math.abs(j - curArcher.x) + Math.abs(k - curArcher.y);
						if (map[j][k] == 0 || curDist > D || minDistance < curDist)
							continue;
						if (minDistance == curDist && killY < k)
							continue;
						minDistance = curDist;
						killX = j;
						killY = k;
					}
				}
				if (killX != N)
					killed.add(new Pair(killX, killY));
			}
			for (Pair e : killed) {
				if (map[e.x][e.y] == 0)
					continue;
				map[e.x][e.y] = 0;
				cnt++;
			}
			move(map, round);
		}
		return cnt;
	}

	private static void move(int[][] map, int round) {
		for (int i = N - 1; i > round; i--) {
			for (int j = 0; j < M; j++) {
				map[i][j] = map[i - 1][j];
			}
		}

		for (int i = round; i >= 0; i--) {
			for (int j = 0; j < M; j++) {
				map[i][j] = 0;
			}
		}
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

	static boolean nextPermutation() {
		int srcIdx = flag.length;
		while (--srcIdx > 0) {
			if (flag[srcIdx] > flag[srcIdx - 1])
				break;
		}
		if (--srcIdx == -1)
			return false;

		int destIdx = flag.length;
		while (--destIdx >= 0) {
			if (flag[destIdx] < flag[srcIdx])
				continue;
			swap(srcIdx, destIdx);
		}

		destIdx = flag.length;
		while (++srcIdx <= --destIdx) {
			swap(srcIdx, destIdx);
		}
		return true;
	}

	static void swap(int src, int dest) {
		int temp = flag[src];
		flag[src] = flag[dest];
		flag[dest] = temp;
	}
}