import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution2798 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int N, M, maxSum = Integer.MIN_VALUE;
	static int[] cards;

	public static void main(String[] args) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		cards = new int[N];
		String[] input = br.readLine().split(" ");
		for (int i = 0; i < input.length; i++) {
			cards[i] = Integer.parseInt(input[i]);
		}
		getSum(0, 0, 0);
		System.out.println(maxSum);
	}

	static void getSum(int idx, int sum, int cnt) {
		if (sum > M) return;
		
		if (idx == N || cnt == 3) {
			if (cnt == 3 && maxSum < sum) {
				maxSum = sum;
			}
			return;

		}
		getSum(idx + 1, sum + cards[idx], cnt + 1);
		getSum(idx + 1, sum, cnt);
	}
}