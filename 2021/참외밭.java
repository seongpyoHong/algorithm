import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Solution2477 {
	static class Line {
		int direction;
		int len;

		Line(String[] line) {
			direction = Integer.parseInt(line[0]);
			len = Integer.parseInt(line[1]);
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static LinkedList<Line> lines = new LinkedList<>();

	public static void main(String[] args) throws NumberFormatException, IOException {
		int K = Integer.parseInt(br.readLine());
		for (int i = 0; i < 6; i++) {
			lines.add(new Line(br.readLine().split(" ")));
		}
		while (true) {
			if (lines.get(5).direction == 1 && lines.get(0).direction == 4 && lines.get(1).direction == 2) break;
			lines.add(lines.pop());
		}

		int area = 0;
		if (lines.get(2).direction == 4) {
			area = (lines.get(0).len * lines.get(5).len) + (lines.get(2).len * lines.get(3).len);
		} else if (lines.get(3).direction == 2) {
			area = (lines.get(0).len * lines.get(1).len) + (lines.get(3).len * lines.get(4).len);
		} else if (lines.get(4).direction == 4) {
			area = (lines.get(0).len * lines.get(1).len) + (lines.get(3).len * lines.get(4).len);
		} else {
			area = (lines.get(0).len * lines.get(5).len) + (lines.get(2).len * lines.get(3).len);
		}

		System.out.println(area * K);
	}
}