import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2999 {
	static BufferedReader br= new BufferedReader(new InputStreamReader(System.in)); 
	public static void main(String[] args) throws IOException {
		String input = br.readLine();
		int R = getR(input.length());
		int C = input.length() / R;
		if (R == 1) {
			System.out.println(input);
			return;
		}
		
		char[][] map = new char[R][C];
		int idx = 0;
		for (int i = 0; i < C; i++) {
			for (int j = 0; j < R; j++) {
				map[j][i] = input.charAt(idx++);
			}
		}
		
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C ;j++) {
				sb.append(map[i][j]);
			}
		}
		System.out.println(sb.toString());
	}
	
	static int getR(int N) {
		int start = 0;
		int R = 1;
		while(true) {
			start += 1;
			if (N % start != 0) continue;
			if ((N / start) < start) break;
			R = start;
		}
		return R;
	}
}