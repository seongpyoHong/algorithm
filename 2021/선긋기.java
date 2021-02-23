import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution1 {
	static class Pair implements Comparable<Pair>{
		int start;
		int end;
		public Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Pair o) {
			if (this.start > o.start) {
				return 1;
			} else if (this.start == o.start && this.end > o.end) {
				return 1;
			}
			return -1;
		}
		
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N;
	static Pair[] points;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		points = new Pair[N];
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			points[i] = new Pair(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		}
		
		Arrays.sort(points);
		
		int curIdx = 0;
		int distance = 0;
		while(curIdx < N) {
			int start = points[curIdx].start;
			int end = points[curIdx].end;
			while(++curIdx < N) {
				if (points[curIdx].start <= end) 
					end = Math.max(end, points[curIdx].end);
				else 
					break;
			}
			distance += (end - start);
		}
		
		System.out.println(distance);
	}
}