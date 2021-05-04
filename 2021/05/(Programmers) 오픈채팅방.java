import java.util.*;

class Solution {
    HashMap<String, String> mapping = new HashMap<>();
    ArrayList<History> history = new ArrayList<>();
    
    public String[] solution(String[] record) {
        for(int i = 0; i < record.length; i++) {
            String[] input = record[i].split(" ");
            String act = input[0];
            String id = input[1];
            
            if (act.equals("Change")) {
                mapping.put(id, input[2]);
                continue;
            } 
            
            if (act.equals("Enter")){
                mapping.put(id, input[2]);
            }
            history.add(new History(id, act));
        }
        
        String[] answer = new String[history.size()];
        for(int i = 0; i < history.size(); i++) {
            answer[i] = history.get(i).getHistoryString();
        }
        return answer;
    }
    
    class History {
        String id;
        String act;
        
        public History(String id, String act) {
            this.id = id;
            this.act = act;
        }
        
        public String getHistoryString() {
            StringBuilder sb = new StringBuilder(mapping.get(id));
            if (act.equals("Enter")) 
                sb.append("님이 들어왔습니다.");
            else 
                sb.append("님이 나갔습니다.");
            return sb.toString();
        }
    }
}