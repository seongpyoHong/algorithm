import java.util.Scanner;

public class NQueens {
	static Scanner sc = new Scanner(System.in);  
	static int result = 0;
	static int N = 0;
	static int[] row;
	public static void main(String[] args) {
		N = sc.nextInt();
		row = new int[N];
		int depth = 0;
		nqueen(depth);
		System.out.println(result);
	}
	
	private static void nqueen(int depth) {
		if (depth == N) {
			result += 1;
			return;
		}
		
		for (int i = 0; i < N; i++) {
			row[depth] = i;
			if (isValid(depth)) {
				nqueen(depth + 1);	
			}
		}
	}
	
	static boolean isValid(int depth) {	
		for (int i = 0; i < depth; i++) {
			if (row[i] == row[depth]) 
				return false;
			if (Math.abs(row[i] - row[depth]) == Math.abs(i - depth)) 
				return false;
		}
		return true;
	}
}