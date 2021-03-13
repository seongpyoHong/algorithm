import java.util.Scanner;

public class Solution8320 {
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int n = sc.nextInt();
		int cnt = 0;
		for (int i = 1; i <= n ; i++) {
			if (n / i <= i - 1) break;
			cnt += (n / i) - i + 1;
		}

		System.out.println(cnt);
	}
}