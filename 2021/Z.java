import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1074 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int cnt = -1, N, R, C, ret;

	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		R = Integer.parseInt(input[1]);
		C = Integer.parseInt(input[2]);
		visit(N, 0, 0);
		System.out.println(ret);
	}

	static void visit(int pow, int startX, int startY) {
		int size = (int) Math.pow(2, pow);
		if (pow == 1) {
			for (int x = startX; x < startX + 2; x++) {
				for (int y = startY; y < startY + 2; y++) {
					cnt++;
					if (x != R || y != C)
						continue;
					ret = cnt;
				}
			}
			return;
		} else  if (startX > R || startX + size <= R || startY > C || startY + size <= C){
			cnt += (size * size);
			return;
		}

		size = size / 2;
		visit(pow - 1, startX, startY);
		visit(pow - 1, startX, startY + size);
		visit(pow - 1, startX + size, startY);
		visit(pow - 1, startX + size, startY + size);
	}
}