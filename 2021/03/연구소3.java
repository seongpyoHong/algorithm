import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Solution17142 {
	static class Pair {
		int x;
		int y;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] map;
	static int N, M, blankCnt, minTime = Integer.MAX_VALUE;
	static ArrayList<Pair> virus = new ArrayList<>();
	static int[] select, dx = { 1, -1, 0, 0 }, dy = { 0, 0, 1, -1 };

	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		map = new int[N][N];

		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(line[j]);
				if (map[i][j] == 2)
					virus.add(new Pair(i, j));
				else if (map[i][j] == 0)
					blankCnt++;
			}
		}

		select = new int[virus.size()];
		int idx = virus.size();
		int cnt = M;
		while (--cnt >= 0)
			select[--idx] = 1;

		do {
			int curTime = spreadVirus();
			if (curTime < 0) continue;
			minTime = Integer.min(minTime, curTime);
		} while (nextPermutation());
		
		System.out.println((minTime == Integer.MAX_VALUE) ? -1 : minTime);
	}

	private static int spreadVirus() {
		boolean[][] visited = new boolean[N][N];
		Queue<Pair> q = new LinkedList<>();
		int cnt = 0;

		for (int i = 0; i < select.length; i++) {
			if (select[i] == 0) continue;
			Pair cur = virus.get(i);
			visited[cur.x][cur.y] = true;
			q.add(cur);
		}
		
		int time = 0;
		while(!q.isEmpty()) {
			if (cnt == blankCnt) return time;
			time++;
			int size = q.size();
			for (int s = 0; s < size; s++) {
				Pair cur = q.poll();
				for (int dIdx = 0; dIdx < 4; dIdx++) {
					int nX = cur.x + dx[dIdx];
					int nY = cur.y + dy[dIdx];
					if (nX < 0 || nY <0 || nX >=N || nY >= N || visited[nX][nY] || map[nX][nY] == 1) continue;
					visited[nX][nY] = true;
					q.add(new Pair(nX, nY));
					if (map[nX][nY] == 0)
						cnt++;
				}
			}
		}

		return -1;
	}

	private static boolean nextPermutation() {
		int srcIdx = virus.size();
		while (--srcIdx > 0)
			if (select[srcIdx] > select[srcIdx - 1])
				break;
		if (--srcIdx == -1)
			return false;

		int destIdx = virus.size();
		while (--destIdx >= 0) {
			if (select[srcIdx] >= select[destIdx])
				continue;
			swap(srcIdx, destIdx);
			break;
		}

		destIdx = virus.size();
		while (++srcIdx <= --destIdx)
			swap(srcIdx, destIdx);
		return true;
	}

	private static void swap(int srcIdx, int destIdx) {
		int temp = select[srcIdx];
		select[srcIdx] = select[destIdx];
		select[destIdx] = temp;
	}
}