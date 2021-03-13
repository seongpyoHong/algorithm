import java.util.LinkedList;
import java.util.Scanner;

public class Solution1158 {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {

		int N = sc.nextInt();
		int K = sc.nextInt();
		LinkedList<Integer> q = new LinkedList<>();
		for (int i = 1; i <= N; i++) {
			q.add(i);
		}
		
		StringBuilder sb = new StringBuilder("<");
		int cnt = 0;
		while (!q.isEmpty()) {
			int current = q.poll();
			if (++cnt == K) {
				cnt = 0;
				sb.append(current+", ");				
			} else {
				q.add(current);
			}
		}
		
		sb.replace(sb.length()-2, sb.length()-1, ">");
		System.out.println(sb.toString());
	}
}