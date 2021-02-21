import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Solution2304 {
	static class Wall implements Comparable<Wall> {
		int x;
		int height;
		public Wall(int x, int height) {
			this.x = x;
			this.height = height;
		}
		@Override
		public int compareTo(Wall o) {
			if (this.x > o.x)
				return 1; 
			return -1;
		}
		
	}
	static BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
	static Wall[] walls;
	static int ret = 0;
	public static void main(String[] args) throws NumberFormatException, IOException {
		int N = Integer.parseInt(br.readLine());
		walls = new Wall[N];
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			walls[i] = new Wall(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		}
		
		Arrays.sort(walls);
		int i = 0;
		while(i != N - 1) {
			ret += walls[i].height;
			i = getNext(i);
		}
		ret += walls[N - 1].height;
		System.out.println(ret);
	}
	
	static int getNext(int curIdx) {
		int max = Integer.MIN_VALUE;
		int maxIdx = -1;
		Wall current = walls[curIdx];
		for (int i = curIdx + 1; i < walls.length; i++) {
			if (current.height <= walls[i].height) {
				ret += ((walls[i].x - (current.x + 1)) * current.height);
				return i;
			}
			if (max < walls[i].height) {
				max = walls[i].height;
				maxIdx = i;
			}
		}
		maxIdx = (maxIdx == -1) ? walls.length - 1 : maxIdx;
		ret += ((walls[maxIdx].x - (current.x + 1)) * walls[maxIdx].height);
		return maxIdx;
	}
}