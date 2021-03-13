import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution2836 {
	static class Pair implements Comparable<Pair> {
		int start;
		int end;

		public Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}

		@Override
		public int compareTo(Pair o) {
			if (this.start < o.start) {
				return 1;
			} else if (this.start == o.start && this.end < o.end) {
				return 1;
			}
			return -1;
		}
	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M;
	static ArrayList<Pair> people = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		N = Integer.parseInt(input[0]);
		M = Integer.parseInt(input[1]);
		long distance = M;

		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			if (Integer.parseInt(line[0]) > Integer.parseInt(line[1])) {
				people.add(new Pair(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
			}
		}
		
		if (people.size() == 0) {
			System.out.println(distance);
			return;
		}
		
		Collections.sort(people);

		int backStart = people.get(0).start;
		int backEnd = people.get(0).end;
		for (int i = 1; i < people.size(); i++) {
			int curStart = people.get(i).start;
			int curEnd = people.get(i).end;

			if (curStart <= curEnd)
				continue;
			if (curStart >= backEnd) {
				backEnd = Math.min(backEnd, curEnd);
			} else {
				distance += (2 * (backStart - backEnd));
				backStart = curStart;
				backEnd = curEnd;
			}
		}

		distance += (2 * (backStart - backEnd));

		System.out.println(distance);
	}
}
