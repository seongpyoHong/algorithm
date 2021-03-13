import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2941 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	public static void main(String[] args) throws IOException {
		String line = br.readLine();
		boolean isContinue = false;
		int count = 0;
		
		for (int i = line.length() - 1; i >= 0; i--) {
			if (!isContinue && (line.charAt(i) == '=' || line.charAt(i) == '-' || line.charAt(i) == 'j')) {
				isContinue = true;
				continue;
			}

			if (isContinue) {
				if (line.charAt(i) == '=' || line.charAt(i) == '-' || line.charAt(i) == 'j') {
					count += 1;
					continue;
				}
				if (line.charAt(i + 1) == '=' && line.charAt(i) == 'z') {
					continue;
				}

				if (line.charAt(i + 1) == '=' && (line.charAt(i) == 'c' || line.charAt(i) == 's')) {
					count += 1;
				} else if (line.charAt(i + 1) == 'j' && (line.charAt(i) == 'n' || line.charAt(i) == 'l')) {
					count += 1;
				} else if (line.charAt(i + 1) == '-' && (line.charAt(i) == 'c' || line.charAt(i) == 'd')) {
					count += 1;
				} else if (line.charAt(i + 1) == 'z' && line.charAt(i) == 'd') {
					count += 1;
				} else {
					count += 2;
				}
				isContinue = false;
			} else
				count += 1;
		}
		
		if (isContinue) {
			count += 1;
		}
		System.out.println(count);
	}
}