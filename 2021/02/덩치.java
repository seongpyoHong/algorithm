import java.util.Scanner;

public class Solution{
	private static Scanner sc = new Scanner(System.in);
	static class Human{
		private int weight;
		private int height;
		
		public Human(int weight, int height) {
			this.weight = weight;
			this.height = height;
		}

		public boolean isStrongThan(Human o) {
			if (this.weight > o.weight && this.height > o.height) 
				return true;
			return false;
		}
	}
	public static void main(String[] args) {
		int N = sc.nextInt();
		Human[] people = new Human[N];
		int[] result = new int[N];
		for (int i = 0; i < N; i++) {
			people[i] = new Human(sc.nextInt(), sc.nextInt());
		}
		
		for (int i = 0; i < N; i++) {
			int cnt = 1;
			for (int j = 0; j < N; j++) {
				if (i == j) continue;
				if (people[j].isStrongThan(people[i])) {
					cnt++;
				}
			}
			result[i] = cnt;
		}
		
		for (int i = 0; i < result.length; i++) {
			System.out.print(result[i] + " ");
		}
	}
}