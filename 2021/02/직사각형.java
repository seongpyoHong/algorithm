import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2527 {
	static class Rectangle {
		int x;
		int y;
		int p;
		int q;

		public Rectangle(int x, int y, int p, int q) {
			this.x = x;
			this.y = y;
			this.p = p;
			this.q = q;
		}

	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 4; i++) {
			sb.append(getResult() + "\n");
		}
		System.out.println(sb.toString());
	}

	private static String getResult() throws IOException {
		String[] line = br.readLine().split(" ");
		Rectangle r1 = new Rectangle(Integer.parseInt(line[0]), Integer.parseInt(line[1]), Integer.parseInt(line[2]),
				Integer.parseInt(line[3]));
		Rectangle r2 = new Rectangle(Integer.parseInt(line[4]), Integer.parseInt(line[5]), Integer.parseInt(line[6]),
				Integer.parseInt(line[7]));
		if (r2.p < r1.x || r1.p < r2.x || r2.q < r1.y || r1.q < r2.y) return "d";
		else if ((r1.x == r2.p && r1.y == r2.q) || (r1.x == r2.p && r1.q == r2.y) || (r1.p == r2.x && r1.q == r2.y) || (r1.p == r2.x && r1.y == r2.q))
			return "c";
		else if (r1.x == r2.p || r1.y == r2.q || r1.p == r2.x || r1.q == r2.y)
			return "b";
		return "a";
	}
}