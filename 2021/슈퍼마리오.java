import java.util.Scanner;

public class Solution2851 {
	static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		int[] input = new int[10];
		for (int i = 0; i < 10; i++) {
			input[i] = sc.nextInt();
		}

		int sum = 0;
		for (int i = 0; i < input.length; i++) {
			sum += input[i];
			if (sum >= 100) {
				sum = (sum - 100 <= 100 - (sum - input[i])) ? sum : sum - input[i];
				System.out.println(sum);
				return;
			}
		}
		System.out.println(sum);
	}
}