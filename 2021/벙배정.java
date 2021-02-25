import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution13300 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[][] room = new int[7][2];
	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		int N = Integer.parseInt(input[0]);
		int K = Integer.parseInt(input[1]);
		int ret = 0;
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			int gender = Integer.parseInt(line[0]);
			int grade = Integer.parseInt(line[1]);
			
			if (room[grade][gender] == K) {
				ret += 1;
				room[grade][gender] = 0;
			} else if (room[grade][gender] == 0)
				ret += 1;
			
			room[grade][gender] += 1;
		}
		
		System.out.println(ret);
	}
}