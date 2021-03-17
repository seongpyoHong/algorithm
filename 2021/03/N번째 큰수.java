import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {
	static PriorityQueue<Integer> pq = new PriorityQueue<Integer>();
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				pq.add(Integer.parseInt(line[j]));
			}
			
			while(pq.size() > N) {
				pq.poll();
			}
		}
		
		System.out.println(pq.poll());
	}
}