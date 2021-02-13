import java.io.InputStreamReader;

import java.io.BufferedReader;
import java.io.IOException;

public class Solution3052 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int[] cache = new int[43];
	public static void main(String[] args) throws NumberFormatException, IOException {
		for (int i = 0; i < 10; i++) {
			cache[Integer.parseInt(br.readLine()) % 42] += 1;
		}
		
		int cnt = 0;
		for (int i = 0; i < cache.length; i++) {
			if (cache[i] == 0) continue;
			cnt += 1;
		}
		
		System.out.println(cnt);
	}
}