import java.util.Scanner;

public class Solution11053 {
	static Scanner sc = new Scanner(System.in);
	static int[] cache = new int[1001];

	public static void main(String[] args) {
		int N = sc.nextInt();
		int[] input = new int[N];

		for (int i = 0; i < input.length; i++) {
			input[i] = sc.nextInt();
		}

		for (int i = 0; i < input.length; i++) {
			int current = input[i];
			int maxCnt = 0;
			for (int j = current - 1; j > 0; j--) {
				if (cache[j] == 0)
					continue;
				maxCnt = (maxCnt < cache[j]) ? cache[j] : maxCnt;
			}
			cache[current] = maxCnt + 1;
		}

		int maxCnt = 0;
		for (int i = 1; i < 1001; i++) {
			if (maxCnt < cache[i]) {
				maxCnt = cache[i];
			}
		}

		System.out.println(maxCnt);
	}
}