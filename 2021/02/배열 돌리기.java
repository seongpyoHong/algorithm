import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution16926 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] map;

	public static void main(String[] args) throws IOException {
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int M = Integer.parseInt(line[1]);
		int R = Integer.parseInt(line[2]);
		map = new int[N][M];

		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(input[j]);
			}
		}

		int xMin = 0, yMin = 0, xMax = N-1, yMax = M-1;
		while (xMin < xMax && yMin < yMax) {
			Queue<Integer> q = new LinkedList<>();
			for (int i = yMin; i <= yMax; i++) {
				q.add(map[xMin][i]);
			}
			for (int i = xMin+1; i <= xMax-1; i++) {
				q.add(map[i][yMax]);
			}
			for (int i = yMax; i >= yMin; i--) {
				q.add(map[xMax][i]);
			}
			for (int i = xMax-1; i >= xMin+1; i--) {
				q.add(map[i][yMin]);
			}
			
			int cnt = 0;
			while(cnt++ != R) {
				q.add(q.poll());
			}

			for (int i = yMin; i <= yMax; i++) {
				map[xMin][i] = q.poll();
			}
			for (int i = xMin+1; i <= xMax-1; i++) {
				map[i][yMax] = q.poll();
			}
			for (int i = yMax; i >= yMin; i--) {
				map[xMax][i] = q.poll();
			}
			for (int i = xMax-1; i >= xMin+1; i--) {
				map[i][yMin] = q.poll();
			}
			xMin++;
			yMin++;
			xMax--;
			yMax--;
		}

		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				sb.append(map[i][j] + " " );
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
}