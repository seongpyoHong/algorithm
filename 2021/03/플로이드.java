import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	static final int INF = 10000001, NIL = -1;
	static int N, M;
	static int[][] D, P;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	public static void main(String[] args) throws IOException {
		N = Integer.parseInt(br.readLine());
		M = Integer.parseInt(br.readLine());
		D = new int[N+1][N+1];
		P = new int[N+1][N+1];
		// Initialize
		for (int i = 0; i <= N; i++) {
			for (int j = 0; j <= N; j++) {
				P[i][j] = NIL;
				D[i][j] = INF;
			}
		}

		// process input
		for (int i = 0; i < M; i++) {
			String[] line = br.readLine().split(" ");
			int start = Integer.parseInt(line[0]);
			int dest = Integer.parseInt(line[1]);
			int weight = Integer.parseInt(line[2]);
			if (D[start][dest] > weight) {
				D[start][dest] = weight;
				P[start][dest] = start;				
			}
		}
		
		floydWarshall();
		printArray(D);
	}

	public static void floydWarshall() {
		for (int k = 1; k <= N; k++) {
			for (int i = 1; i <=N; i++) {
				for (int j = 1; j <= N; j++) {
					if (i == j) continue;
					if (D[i][j] <=  D[i][k] + D[k][j]) continue;
					D[i][j] = D[i][k] + D[k][j];
					P[i][j] = k;
				}
			}
		}
	}
	
	static void printArray(int[][] arr) {
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				if (arr[i][j] == INF)
					System.out.print("0 ");
				else
					System.out.print(arr[i][j] + " ");
			}
			System.out.println();
		}
	}
}