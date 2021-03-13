import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution11399 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		String[] line = br.readLine().split(" ");
		int[] input = new int[N];
		for (int i = 0; i < N; i++) {
			input[i] = Integer.parseInt(line[i]);
		}
		
		Arrays.sort(input);
		
		int sum = 0;
		int stack = 0;
		for (int i = 0; i < input.length; i++) {
			stack += input[i];
			sum += stack;
		}
		System.out.println(sum);
	}
}