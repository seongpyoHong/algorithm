import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class SolutionHW {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
	static class Tower {
		int value;
		int idx;
		public Tower(int value, int idx) {
			this.value = value;
			this.idx = idx;
		}
		
		int getIdx() {
			return this.idx;
		}
		
		int getValue() {
			return this.value;
		}
	}
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());

		int[] result = new int[N];
		Stack<Tower> src = new Stack<>();
		Stack<Tower> ready = new Stack<>();
		
		String[] line = br.readLine().split(" ");
		for (int i = 0; i < line.length; i++) {
			int input = Integer.parseInt(line[i]);
			src.push(new Tower(input, i+1));
		}
		
		while(!src.isEmpty()) {
			Tower top = src.pop();
			int topIdx = top.getIdx();
			while(!ready.isEmpty()) {
				if (top.getValue() < ready.peek().getValue()) break;
				int idx = ready.pop().getIdx();
				result[idx-1] =  topIdx;
			}
			ready.push(top);
		}

		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}
}