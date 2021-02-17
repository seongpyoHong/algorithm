import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;

public class Solution15686 {
	static class Pair {
		int x = 0;
		int y = 0;

		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}

	}

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static ArrayList<Pair> home = new ArrayList<>(), shop = new ArrayList<>();
	static int[] flag;

	public static void main(String[] args) throws NumberFormatException, IOException {
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int M = Integer.parseInt(line[1]);
		for (int i = 0; i < N; i++) {
			String[] input = br.readLine().split(" ");
			for (int j = 0; j < input.length; j++) {
				if (input[j].equals("1"))
					home.add(new Pair(i, j));
				else if (input[j].equals("2"))
					shop.add(new Pair(i, j));
			}
		}

		flag = new int[shop.size()];
		int cnt = M;
		int idx = flag.length;
		while (cnt-- > 0)
			flag[--idx] = 1;
	
		int ret = Integer.MAX_VALUE;
		do {
			ret = Math.min(ret, getDistance());
		} while (nextPermutation());

		System.out.println(ret);
	}

	private static int getDistance() {
		int totalDist = 0;
		for (int i = 0; i < home.size(); i++) {
			int min = Integer.MAX_VALUE;
			Pair curHome = home.get(i);
			for (int j = 0; j < flag.length; j++) {
				if (flag[j] == 0)
					continue;
				Pair current = shop.get(j);
				min = Math.min(min, Math.abs(current.x - curHome.x) + Math.abs(current.y - curHome.y));
			}
			totalDist += min;
		}
		return totalDist;
	}

	static boolean nextPermutation() {
		int srcIdx = flag.length;
		while (--srcIdx > 0) {
			if (flag[srcIdx] > flag[srcIdx - 1])
				break;
		}
		srcIdx--;
		if (srcIdx == -1)
			return false;

		int destIdx = flag.length;
		while (--destIdx >= 0) {
			if (flag[destIdx] < flag[srcIdx])
				continue;
			swap(srcIdx, destIdx);
		}

		destIdx = flag.length;
		while (++srcIdx <= --destIdx) {
			swap(srcIdx, destIdx);
		}

		return true;
	}

	static void swap(int src, int dest) {
		int temp = flag[src];
		flag[src] = flag[dest];
		flag[dest] = temp;
	}
}