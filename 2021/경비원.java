import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution2564 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int curDirection, curDist, N, M;
	static int[][] shop;
	public static void main(String[] args) throws NumberFormatException, IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		int shopCnt = Integer.parseInt(br.readLine());


		shop = new int[shopCnt][2];
		for (int i = 0; i < shopCnt; i++) {
			String[] input = br.readLine().split(" ");
			shop[i][0] = Integer.parseInt(input[0]);
			shop[i][1] = Integer.parseInt(input[1]);
		}
		
		String[] line2 = br.readLine().split(" ");
		curDirection = Integer.parseInt(line2[0]);
		curDist = Integer.parseInt(line2[1]);
		
		int totalDist = 0;
		for (int i = 0; i < shop.length; i++) {
			totalDist += getMinDist(shop[i][0], shop[i][1]);
		}
		System.out.println(totalDist);
	}

	static int getMinDist(int direction, int dist) {
		int ret = 0;
		if (curDirection == 1) {
			if (direction == 1)
				ret = Math.abs(curDist - dist);
			else if (direction == 2)
				ret = Math.min(curDist + dist, N - curDist + N - dist) + M;
			else if (direction == 3)
				ret = curDist + dist;
			else 
				ret = dist + N - curDist;
		} else if (curDirection == 2) {
			if (direction == 1)
				ret = Math.min(curDist + dist, N - curDist + N - dist) + M;
			else if (direction == 2)
				ret = Math.abs(curDist - dist);
			else if (direction == 3)
				ret = M - dist + curDist;
			else 
				ret = N - curDist + M - dist;
		} else if (curDirection == 3) {
			if (direction == 1)
				ret = curDist + dist;
			else if (direction == 2)
				ret = dist + M - curDist;
			else if (direction == 3)
				ret = Math.abs(curDist - dist);
			else 
				ret = Math.min(curDist + dist, M - curDist + M - dist) + N;
		} else {
			if (direction == 1)
				ret = N - dist + curDist;
			else if (direction == 2) 
				ret = M - curDist + N - dist;
			else if (direction == 3)
				ret = Math.min(curDist + dist, M - curDist + M - dist) + N;
			else 
				ret = Math.abs(curDist - dist);
		}
		
		return ret;
	}
}