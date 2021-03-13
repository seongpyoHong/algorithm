import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Solution {
	static class Pair implements Comparable<Pair>{
		int x;
		int y;
		public Pair(int x, int y) {
			this.x = x;
			this.y = y;
		}
		@Override
		public int compareTo(Pair o) {
			if (this.x > o.x) {
				return 1;
			} else if (this.x == o.x && this.y > o.x) {
				return 1;
			}
			return -1;
		}
	}
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static int M, N, L, startIdx = 0, cnt;
	static int[] shoot;
	static Pair[] animal;
	public static void main(String[] args) throws IOException {
		String[] input = br.readLine().split(" ");
		M = Integer.parseInt(input[0]);
		N = Integer.parseInt(input[1]);
		L = Integer.parseInt(input[2]);
		
		shoot = new int[M];
		animal = new Pair[N];
		String[] shootLine = br.readLine().split(" ");
		for (int i = 0; i < M; i++) {
			shoot[i] = Integer.parseInt(shootLine[i]);
		}
		
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			animal[i] = new Pair(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
		}
		
		Arrays.sort(shoot);
		Arrays.sort(animal);

		for (int i = 0; i < N; i++) {
			shoot(i);
		}

		System.out.println(cnt);
	}
	
	static void shoot(int idx) {
		int shootIdx = 0;
		while(shootIdx != M-1 && shoot[shootIdx] <= animal[idx].x) {
			shootIdx++;
		}
		
		if (--shootIdx >= 0 && Math.abs(shoot[shootIdx] - animal[idx].x) + animal[idx].y <= L) {
			cnt +=1;
		} else if(++shootIdx < M && Math.abs(shoot[shootIdx] - animal[idx].x) + animal[idx].y <= L) {
			cnt+= 1;
		}
		return;
	}
}