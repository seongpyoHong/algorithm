import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class Solution3985 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static class Pair {
		int num;
		int start;
		int end;

		public Pair(int num, int start, int end) {
			this.num = num;
			this.start = start;
			this.end = end;
		}
	}

	public static void main(String[] args) throws NumberFormatException, IOException {
		int L = Integer.parseInt(br.readLine());
		int N = Integer.parseInt(br.readLine());
		boolean[] cakes = new boolean[L];
		int[] result = new int[N];
		Queue<Pair> people = new LinkedList<>();

		int max = Integer.MIN_VALUE;
		int expected = 1;
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			int start = Integer.parseInt(line[0]) - 1;
			int end = Integer.parseInt(line[1]) - 1;
			if (max < (end - start)) {
				expected = i + 1;
				max = (end - start);
			}
				
			people.add(new Pair(i, start, end));
		}
		
		while(!people.isEmpty()) {
			Pair current = people.poll();
			for (int i = current.start; i <= current.end; i++) {
				if (cakes[i]) continue;
				result[current.num] += 1;
				cakes[i] = true;
			}
		}
		
		max = Integer.MIN_VALUE;
		int real = 1;
		for (int i = 0; i < result.length; i++) {
			if (max< result[i]) {
				real = i+1;
				max = result[i];
			}
		}
		
		System.out.println(expected + "\n" + real);
	}
}