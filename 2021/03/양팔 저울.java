import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
	// Input 받기 위한 Buffered Reader
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	// 추의 개수 : N, 무게를 젤 물체의 개수 : M
	static int N, M;
	// 추의 집합 : src , 물체의 집합 : dest
	static int[] src, dest;
	// 해당 인덱스의 추를 통해 만들 수 있는 무게를 저장
	static boolean[][] cache = new boolean[30][40001];
	public static void main(String[] args) throws NumberFormatException, IOException {
		// 추의 개수
		N = Integer.parseInt(br.readLine());
		// 추 배열 초기화
		src = new int[N];
		// 추 배열 할당
		String[] srcInput = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			src[i] = Integer.parseInt(srcInput[i]);
		}
		
		// 물체의 개수
		M = Integer.parseInt(br.readLine());
		// 물체 배열 초기화
		dest = new int[M];
		// 물체 배열 할당
		String[] destInput = br.readLine().split(" ");
		for (int i = 0; i < M; i++) {
			dest[i] = Integer.parseInt(destInput[i]);
		}
		
		// cache 배열 값 할당
		makeCache();
		
		// 출략
		for (int i = 0; i < M; i++) {
			// 만들 수 있으면
			if (isMakable(dest[i]))
				System.out.print('Y' + " ");
			// 만들 수 없으면
			else
				System.out.print('N' + " ");
		}		
	}

	// 만들 수 있는 무게인지 탐색
	private static boolean isMakable(int target) {
		// 마지막 추까지 고려했을 때 만들 수 있는 경우인지 확인
		return cache[N-1][target];
	}


	private static void makeCache() {
		// 이전 최대값을 기억하기 위한 변수 => 현재 단계에서 최대 무게는 이전 단계에서 만들 수 있는 무게의 최대값 + 현재 자신의 무게
		int prevMax = src[0];
		// 시작 값 초기화 => 0번 idx는 자신을 넣는 경우만 가능
		cache[0][src[0]] = true;
		
		// 1번 idx부터 만들 수 있는 무게를 탐색
		for (int i = 1; i < N; i++) {
			// 현재 추의 무게
			int curSrc = src[i];
			// 현재 추만 넣었을 때 만들 수 있는 무게
			cache[i][curSrc] = true;
			
			// 현재 추를 통해 만들 수 있는 무게의 최대값
			int curMax = 0;
			
			// 이전 단계에서 만들었던 무게를 통해 현재 단계에서 만들 수 있는 무게 탐색 => 모든 무게를 돌 필요가 없고 이전 단계의 최대무게 전까지만 탐색하면 된다.
			for (int j = 1; j <= prevMax; j++) {
				// 이전에 만들 수 없는 무게인 경우 Skip
				if (!cache[i-1][j]) continue;
				// 이전 무게 그대로 사용하는 경우 (현재 무게 사용 X)
				cache[i][j] = true;
				// 이전 무게 + 현재 무게
				cache[i][j + curSrc] = true;
				// 이전 무게 - 현재 무게 => 무게를 측정하려는 물체쪽에 추를 넣는 것과 동일한 경우
				cache[i][Math.abs(j - curSrc)] = true;
				// 최대 무게는 항상 더한 값이기 때문에 더한 값으로 갱신
				curMax = j + curSrc;
			}
			
			// 이전 최대값 갱신
			prevMax = curMax;
		}
	}
}