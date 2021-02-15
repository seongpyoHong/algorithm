import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Solution2961 {
	static class Pair {
		int x;
		int y;
		Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static Pair[] input;
	static boolean[] check;
	static long min = 10000000001L;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		input = new Pair[N];
		check = new boolean[N];
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			input[i] = new Pair(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		}
		
		getSubset(0);
		System.out.println(min);
	}
	
	static void getSubset(int idx) {
		if (idx == N) {
			min = Math.min(min, getDiff());
			return;
		}
		check[idx] = true;
		getSubset(idx + 1);
		check[idx] = false;
		getSubset(idx + 1);
	}
	
	static long getDiff() {
		long totalX = 1;
		long totalY = 0;
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			if (!check[i]) continue;
			totalX *= (long)input[i].x;
			totalY += (long)input[i].y;
			cnt += 1;
		}
		if (cnt == 0) return 10000000001L;
		return Math.abs(totalX - totalY);
	}
}