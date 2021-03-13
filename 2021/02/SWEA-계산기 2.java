import java.util.Scanner;

public class NQueens {
	static Scanner sc = new Scanner(System.in);  
	static int result = 0;
	static int N = 0;
	static boolean[][] check;
	static int[] dx = {0, 0, -1, 1, 1 ,1 , -1, -1};
	static int[] dy = {-1, 1, 0 ,0, -1, 1 , 1, -1};
	public static void main(String[] args) {
		N = sc.nextInt();
		for (int i = 0; i < N; i++) {
			check = new boolean[N][N];
			check[0][i] = true;
			nqueen(0,i,1);
		}
		System.out.println(result);
	}
	
	private static void nqueen(int x, int y, int cnt) {
		if (cnt == N ) {
			result += 1;
			return;
		}
		
		for (int j = 0; j < check[0].length; j++) {
			if (j == y || !isValid(x+1, j)) continue;
			check[x+1][j] = true;
			nqueen(x+1, j, cnt + 1);
			check[x+1][j] = false;
		}
	}
	
	static boolean isValid(int x, int y) {	
		for (int i = 0; i < 8; i++) {
			int currentX = x;
			int currentY = y;
			while(true) {
				currentX += dx[i];
				currentY += dy[i];
				if (currentX < 0 || currentX >= N || currentY < 0 || currentY >= N) break;
				if (check[currentX][currentY]) {
					return false;
				}
			}
		}
		return true;
	}
}