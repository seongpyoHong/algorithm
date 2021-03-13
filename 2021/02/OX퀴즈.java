import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution8958 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		int[] result = new int[N];
		for (int i = 0; i < N; i++) {
			result[i] = getScore();
		}
		
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
	
	static int getScore() throws IOException {
		char[] line = br.readLine().toCharArray();
		int total = 0;
		int weight = 0;
		for (int i = 0; i < line.length; i++) {
			if (line[i] == 'O') {
				total += (weight + 1);
				weight ++;
			} else {
				weight = 0;
			}
		}
		return total;
	}
}