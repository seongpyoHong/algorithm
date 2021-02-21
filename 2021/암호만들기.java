import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution1759 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static char[] alphabet;
	static int L, C, vowel, consonant;
	static StringBuilder sb = new StringBuilder();
	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		L = Integer.parseInt(input[0]);
		C = Integer.parseInt(input[1]);
		
		alphabet = new char[C];
		String[] line = br.readLine().split(" ");
		for (int i = 0; i < line.length; i++) {
			alphabet[i] = line[i].charAt(0);
		}
		
		Arrays.sort(alphabet);
		combination(0, 0, 0, 0);
		System.out.println(sb.toString());
	}
	
	private static void combination(int idx, int vowelCnt, int consonantCnt, int flag) {	
		if (vowelCnt + consonantCnt == L && vowelCnt >= 1 && consonantCnt >= 2) {
			getPassword(flag);
			return;
		}
		if (idx == C) return;
		
		if (isVowel(alphabet[idx])) {
			combination(idx + 1, vowelCnt+1, consonantCnt, flag|1<<idx);
		} else {
			combination(idx + 1, vowelCnt, consonantCnt + 1, flag|1<<idx);			
		}
		combination(idx + 1, vowelCnt, consonantCnt, flag);
	}

	private static void getPassword(int flag) {
		for (int i = 0; i < C; i++) {
			if ((flag&1<<i) == 0) continue;
			sb.append(String.valueOf(alphabet[i]));
		}
		sb.append("\n");
	}

	static boolean isVowel(char a) {
		if (a == 'a' || a == 'e' || a== 'i' || a== 'o' || a== 'u')
			return true;
		return false;
	}
}