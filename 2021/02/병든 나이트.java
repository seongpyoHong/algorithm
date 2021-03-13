import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution1783 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int M = Integer.parseInt(input[1]);
		int ret = 1;
		if (N == 1)
			ret = 1;
		else if (N == 2) {
			while(M > 2 && ret < 4) {
				M -= 2;
				ret += 1;
			}
		} else {
			if (M <=  6) {
				ret = Math.min(4, M);
			} else {
				ret = 4 + (M - 6);
			}
		}		
		System.out.println(ret);
	}
}