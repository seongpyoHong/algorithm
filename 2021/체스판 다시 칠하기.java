import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static char[][] input;

	public static void main(String[] args) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine()); // "2 3"을 한줄로 입력받기
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		input = new char[N][M];

		for (int i = 0; i < N; i++) {
			String line = br.readLine();
			for (int j = 0; j < M; j++) {
				input[i][j] = line.charAt(j);
			}
		}

		int min = Integer.MAX_VALUE;
		for (int x = 0; x <= N - 8; x++) {
			for (int y = 0; y <= M - 8; y++) {
				min = Integer.min(min, getCount(x, y, 'W'));
				min = Integer.min(min, getCount(x, y, 'B'));
			}
		}
		System.out.println(min);
	}

	private static int getCount(int x, int y, char start) {
		char prev = start;
		int cnt = (input[x][y] == start) ? 0 : 1;

		for (int i = x; i < x + 8; i++) {
			for (int j = y; j < y + 8; j++) {
				
				if (i == x && j == y) continue;
				if (j == y) {
					prev = ( prev == 'W') ? 'B' : 'W';
				}
				
				if (prev == input[i][j]) {
					cnt++;
				}
				prev = ( prev == 'W') ? 'B' : 'W';
			}
		}
		return cnt;
	}
}