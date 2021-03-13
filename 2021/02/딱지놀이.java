import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Solution14696 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[] result;
	static int N;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		result = new char[N];
		for (int i = 0; i < N; i++) {
			String[] a = br.readLine().split(" ");
			String[] b = br.readLine().split(" ");
			result[i] = playGame(a,b);
		}
		
		for (int i = 0; i < result.length; i++) {
			System.out.println(result[i]);
		}
	}
	private static char playGame(String[] a, String[] b) {
		for (int i = 4; i >= 1; i--) {
			int aCnt = 0;
			int bCnt = 0;
			for (int j = 1; j < a.length; j++) {
				if (!a[j].equals(String.valueOf(i))) continue;
				aCnt++;
			}
			
			for (int j = 1; j < b.length; j++) {
				if (!b[j].equals(String.valueOf(i))) continue;
				bCnt++;
			}
			if (aCnt == bCnt) continue;
			else if (aCnt > bCnt) return 'A';
			else return 'B';
		}
		return 'D';
	}
}