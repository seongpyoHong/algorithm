import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution10163 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] map = new int[101][101];
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]);
			int y = Integer.parseInt(line[1]);
			int width = Integer.parseInt(line[2]);
			int height = Integer.parseInt(line[3]);

			for (int j = x; j < x + width; j++) {
				for (int k = y; k < y + height; k++) {
					map[j][k] = i + 1;
				}
			}
		}

		int[] ret = new int[N];
		for (int i = 0; i < 101; i++) {
			for (int j = 0; j < 101; j++) {
				if (map[i][j] == 0) continue;
				ret[map[i][j] - 1] += 1;
			}
		}
		
		for (int i = 0; i < ret.length; i++) {
			System.out.println(ret[i]);
		}
	}
}