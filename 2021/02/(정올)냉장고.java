import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution  {
	static class Pair implements Comparable<Pair>{
		int start;
		int end;
		public Pair(int start, int end) {
			this.start = start;
			this.end = end;
		}
		@Override
		public int compareTo(Pair o) {
			if (this.start > o.start) 
				return 1;
			else if (this.start <= o.start)
				return -1;
			else 
				return 0;
		}
		
	}
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		Pair[] chemicals = new Pair[N];
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			chemicals[i] = new Pair(Integer.parseInt(input[0]), Integer.parseInt(input[1]));
		}
		
		Arrays.sort(chemicals);
		
		int start = chemicals[0].start;
		int end = chemicals[0].end;
		int cnt = 1;
		for (Pair c : chemicals) {
			if (start <= c.start && c.start <= end) {
				start = Math.max(start, c.start);
				end = Math.min(end, c.end);
			} else {
				cnt += 1;
				start = c.start;
				end = c.end;				
			}
		}
		
		System.out.println(cnt);
	}
}
