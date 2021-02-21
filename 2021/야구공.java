import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution17281 {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int innings, maxScore = Integer.MIN_VALUE, totalScore, nextPlayer;
	static int[][] player;
	static int[] selection = { 1, 2, 3, 4, 5, 6, 7, 8 };

	public static void main(String[] args) throws NumberFormatException, IOException {
		innings = Integer.parseInt(br.readLine());
		player = new int[innings][9];
		
		for (int i = 0; i < innings; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < line.length; j++) {
				player[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		do {
			totalScore = 0;
			nextPlayer = 0;
			playGame();
		} while (nextPermutation());

		System.out.println(maxScore);
	}

	private static void playGame() {
		int[] sortedPlayer = new int[9];
		sortedPlayer[3] = 0;
		for (int i = 0; i < selection.length; i++) {
			if (i < 3)
				sortedPlayer[i] = selection[i];
			else if (i >= 3)
				sortedPlayer[i + 1] = selection[i];
		}
		
		for (int i = 0; i < innings; i++) {
			int out = 3;
			int runner = 0;
			while (out > 0) {
				int curPlayer = sortedPlayer[nextPlayer];
				int base = 0;
				if (player[i][curPlayer] == 1) {
					base = 1;
				} else if (player[i][curPlayer] == 2) {
					base = 2;
				} else if (player[i][curPlayer] == 3) {
					base = 3;
				} else if (player[i][curPlayer] == 4) {
					base = 4;
				} else
					out--;

				runner = processBase(runner, base);
				nextPlayer = (nextPlayer + 1) % 9;
			}
		}
		
		maxScore = Math.max(maxScore, totalScore);
	}

	private static int processBase(int runner, int base) {
		if (base > 0)
			runner = runner | 1 << 0;
		while (base-- > 0) {
			if ((runner & 1 << 3) > 0) {
				runner = runner | 0 << 3;
				totalScore += 1;
			}
			runner = runner << 1;
		}
		return runner;
	}

	static boolean nextPermutation() {
		int srcIdx = 8;
		while (--srcIdx > 0) {
			if (selection[srcIdx] > selection[srcIdx - 1])
				break;
		}
		if (--srcIdx < 0)
			return false;

		int destIdx = 8;
		while (--destIdx >= 0) {
			if (selection[srcIdx] >= selection[destIdx])
				continue;
			swap(srcIdx, destIdx);
			break;
		}

		destIdx = 8;
		while (++srcIdx <= --destIdx) {
			swap(srcIdx, destIdx);
		}
		return true;
	}

	static void swap(int src, int dest) {
		int temp = selection[src];
		selection[src] = selection[dest];
		selection[dest] = temp;
	}
}