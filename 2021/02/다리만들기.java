import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Main {
	static class Pair {
		int x;
		int y;

		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		int first() {
			return x;
		}

		int second() {
			return y;
		}
	}

	private static int number = 2;
	private static int[] dx = { 0, 0, -1, 1 };
	private static int[] dy = { 1, -1, 0, 0 };

	public static void main(String[] args) {

		// input
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[][] map = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] = sc.nextInt();
			}
		}

		// divide island
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == 1) {
					fillNumber(map, i, j);
					number++;
				}
			}
		}

		// get Minimum Distance
		int num = 2;
		int min = Integer.MAX_VALUE;
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] == num) {
					min = Integer.min(min, getMinimumDistance(map, i, j));
					num++;
				}
			}
		}
		
		System.out.println(min);
	}

	private static void fillNumber(int[][] map, int x, int y) {
		boolean[][] visited = new boolean[map.length][map.length];
		Queue<Pair> sq = new LinkedList<Pair>();
		sq.add(new Pair(x, y));
		while (!sq.isEmpty()) {
			Pair current = sq.poll();
			map[current.first()][current.second()] = number;
			for (int i = 0; i < 4; i++) {
				int nextX = current.first() + dx[i];
				int nextY = current.second() + dy[i];

				if (nextX < 0 || nextX > map.length - 1 || nextY < 0 || nextY > map.length - 1
						|| map[nextX][nextY] != 1 || visited[nextX][nextY])
					continue;
				sq.add(new Pair(nextX, nextY));
				visited[nextX][nextY] = true;
			}
		}
	}

	private static int getMinimumDistance(int[][] map, int x, int y) {
		LinkedList<Pair> boundary = getBoundary(map, map[x][y]);
		boolean[][] visited = new boolean[map.length][map.length];
		int distance = 1;
		while (!boundary.isEmpty()) {
			int size = boundary.size();
			for (int i = 0; i < size; i++) {
				Pair current = boundary.poll();

				for (int idx = 0; idx < 4; idx++) {
					int nextX = current.first() + dx[idx];
					int nextY = current.second() + dy[idx];

					if (nextX < 0 || nextX > map.length - 1 || nextY < 0 || nextY > map.length - 1
							|| map[nextX][nextY] == map[x][y] || visited[nextX][nextY])
						continue;
					if (map[nextX][nextY] == 0) {
						visited[nextX][nextY] = true;
						boundary.add(new Pair(nextX, nextY));
					} else {
						return distance;
					}
					
				}
			}
			distance++;
		}
		return distance;
	}

	private static LinkedList<Pair> getBoundary(int[][] map, int num) {
		LinkedList<Pair> ret = new LinkedList<>();
		boolean[][] visited = new boolean[map.length][map.length];
		for (int i = 0; i < map.length; i++) {
			for (int j = 0; j < map.length; j++) {
				if (map[i][j] != num)
					continue;
				for (int idx = 0; idx < 4; idx++) {
					int nextX = i + dx[idx];
					int nextY = j + dy[idx];

					if (nextX < 0 || nextX > map.length - 1 || nextY < 0 || nextY > map.length - 1 || visited[nextX][nextY])
						continue;
					if (map[nextX][nextY] == 0) {
						visited[nextX][nextY] = true;
						ret.add(new Pair(nextX, nextY));
					}
				}
			}
		}

		return ret;
	}

}