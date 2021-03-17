import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

class Solution {
    static class Condition {
		String key;
		public Condition(String lang, String job, String year, String food) {
			key = String.valueOf(lang.charAt(0)) + String.valueOf(job.charAt(0)) + String.valueOf(year.charAt(0)) + String.valueOf(food.charAt(0));
		}
		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((key == null) ? 0 : key.hashCode());
			return result;
		}
		@Override
		public boolean equals(Object o) {
			String oKey = ((Condition)o).key;
			for (int i = 0; i < oKey.length(); i++) {
				if (oKey.charAt(i) == '-') continue;
				if (oKey.charAt(i) != key.charAt(i)) return false;
			}
			return true;
		}		
	}

	static HashMap<Condition, ArrayList<Integer>> people = new HashMap<>();

	public int[] solution(String[] info, String[] query) {
		//create map
		for (int i = 0; i < info.length; i++) {
			String[] line = info[i].split(" ");
			int score = Integer.parseInt(line[4]);
			Condition cond = new Condition(line[0], line[1], line[2], line[3]);
			if (!people.containsKey(cond))
				people.put(cond, new ArrayList<Integer>());
			people.get(cond).add(score);
		}
		
		// sort map
		for(ArrayList<Integer> scores : people.values()) {
			Collections.sort(scores, Collections.reverseOrder());
		}

		int[] answer = new int[query.length];
		for (int i = 0; i < query.length; i++) {
			answer[i] = findPeople(query[i].split(" and "));
		}
		
        return answer;
	}

	public int findPeople(String[] input) {
		Condition cond = new Condition(input[0], input[1], input[2], input[3].split(" ")[0]);
		int score = Integer.parseInt(input[3].split(" ")[1]);
		int cnt = 0;
		for(Condition key : people.keySet()) {
			if (!key.equals(cond)) continue;
			int next = Collections.binarySearch(people.get(key), score - 1, Collections.reverseOrder());
			next = (next > 0) ? next : Math.abs(next + 1);
			while(--next >= 0) 
				if (people.get(key).get(next) >= score) break;
			
			cnt += next + 1;
		}
        return cnt;
	}
}