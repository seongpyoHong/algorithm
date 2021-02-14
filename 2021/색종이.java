import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static int[][] map = new int[101][101];
	public static void main(String[] args) {
		int N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			fillMap(sc.nextInt(), sc.nextInt());
		}
		
		int cnt = 0;
		for (int i = 1; i < 101; i++) {
			for (int j = 1; j < 101; j++) {
				if (map[i][j] == 0) continue;
				cnt++;
			}
		}
		System.out.println(cnt);
	}
	
	static void fillMap(int x, int y) {
		for (int i = x; i < x + 10; i++) {
			for (int j = y; j < y + 10; j++) {
				map[i][j] = 1;
			}
		}
	}
}