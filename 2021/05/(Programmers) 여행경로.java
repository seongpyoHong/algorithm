import java.util.*;

// dfs
class Solution {
    int ticketCnt = 0;
    ArrayList<String> ret = new ArrayList<>();
    HashMap<String, ArrayList<Pair> > adj = new HashMap<>();
    
    public String[] solution(String[][] tickets) {
        ticketCnt = tickets.length;
        for(int i = 0; i < tickets.length; i++) {
            String src = tickets[i][0];
            String dest = tickets[i][1];
            
            if (!adj.containsKey(src)) {
                adj.put(src, new ArrayList<>());
            }
            adj.get(src).add(new Pair(dest));
        }
        
        for(String key : adj.keySet()) {
            adj.get(key).sort((e1, e2) -> e1.name.compareTo(e2.name));
        }
      
        ret.add("ICN");
        travel("ICN", 1);
        

        String[] answer = new String[ret.size()];
        for(int i = 0; i < answer.length; i++) {
            answer[i] = ret.get(i);
        }
        
        return answer;
    }
    
    public boolean travel(String city, int visitCnt) {
        if (visitCnt == ticketCnt + 1) {
            return true;
        }

        if (adj.get(city) == null) return false;
        
        for(Pair next : adj.get(city)) {
            if (next.visited) continue;
            next.visited = true;
            ret.add(next.name);
            
            if (travel(next.name, visitCnt + 1)) return true;
            ret.remove(visitCnt);
            next.visited = false;
        }
        return false;
    }
    
    static class Pair {
        String name;
        Boolean visited;
        
        public Pair(String name) {
            this.name = name;
            visited = false;
        }
    }
}