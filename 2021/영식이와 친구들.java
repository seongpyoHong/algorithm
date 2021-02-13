import java.util.Scanner;

public class Solution1592 {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int N = sc.nextInt();
		int M = sc.nextInt();
		int L = sc.nextInt();

		int[] receiveCnt = new int[N + 1];
		int current = 1;
		int totalCnt = 0;
		while (true) {
			if (++receiveCnt[current] == M)
				break;
			totalCnt++;
			if (receiveCnt[current] % 2 == 0) {
				current = (current - L > 0) ? current - L : current + (N - L);
			} else {
				current = (current + L < N + 1) ? current + L : current + L - (N);
			}
		}
		System.out.println(totalCnt);
	}
}