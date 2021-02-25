import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2116 {
	static class Dice {
		int[] pair = {5,3,4,1,2,0};
		int[] num = new int[6];
		Dice(String[] line) {
			num[0] = Integer.parseInt(line[0]);
			num[1] = Integer.parseInt(line[1]);
			num[2] = Integer.parseInt(line[2]);
			num[3] = Integer.parseInt(line[3]);
			num[4] = Integer.parseInt(line[4]);
			num[5] = Integer.parseInt(line[5]);
		}
		
		int getMax(int underIdx) {
			int max = Integer.MIN_VALUE;
			for (int i = 0; i < 6; i++) {
				if (i == pair[underIdx] || i == underIdx) continue;
				max = Math.max(max, num[i]);
			}
			return max;
		}
		
		int getTop(int underIdx) {
			return num[pair[underIdx]];
		}
		
		int getUnderIdx(int target) {
			int idx = 0;
			for (int i = 0; i < 6; i++) {
				if (num[i] == target)
					idx = i;
			}
			
			return idx;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int max = Integer.MIN_VALUE, N;
	static Dice[] dices;
	public static void main(String[] args) throws NumberFormatException, IOException {
		N = Integer.parseInt(br.readLine());
		dices = new Dice[N];
		for (int i = 0; i < N; i++) {
			dices[i] = new Dice(br.readLine().split(" "));
		}
		
		for (int i = 0; i < 6; i++) {
			int underIdx = i;
			int curMax = dices[0].getMax(underIdx);
			int top = dices[0].getTop(underIdx);
			for (int j = 1; j < N; j++) {
				underIdx = dices[j].getUnderIdx(top);
				curMax += dices[j].getMax(underIdx);
				top = dices[j].getTop(underIdx);
			}
			
			max = Math.max(max, curMax);
		}
		
		System.out.println(max);
	}

}