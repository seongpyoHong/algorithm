import java.util.Scanner;

public class Solution {
	private static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) {
		int N = sc.nextInt() - 1;

		if (N == 0) {
			System.out.println("666");
			return;
		}

		int prefix = 0;
		int cnt;
		while (true) {
			cnt = 1;
			if (prefix % 10 == 6)
				cnt++;
			if (prefix % 100 == 66)
				cnt++;

			int limit = (int) Math.pow(10, cnt) + 9;
			if (N / limit == 0)
				break;

			N -= limit;
			prefix++;
		}

		String body = "";
		for (int i = 3; i >= cnt; i--) {
			body += "6";
		}
		
		StringBuilder sb = new StringBuilder();

		if (prefix != 0) {
			sb.append(String.valueOf(prefix));
		}

		if (N < 6) {
			sb.append(N + "666");
		} else {
			int limit = (int) Math.pow(10, cnt) + 6;
			if (N < limit) {
				sb.append(body + getFormatNum(cnt, N - 6));
			} else {
				sb.append((N - (int) Math.pow(10, cnt) + 1) + "666");
			}
		}
		
		System.out.println(sb.toString());
		
	}
	
	private static String getFormatNum(int len, int target) {
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(target));
		for (int i = sb.length(); i < len; i++) {
			sb.insert(0, '0');
		}
		return sb.toString();
	}

}