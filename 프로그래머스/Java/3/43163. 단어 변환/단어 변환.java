import java.util.*;

class Solution {
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        // 이게 사실 graph임
        // 1이 2,3이랑 연결 2가 뭐랑 연결
        // Cycle이 있는 그래프
        // BFS로 조지면 최소 단계 나옴
        int[] cost = new int[words.length];
        Queue<Integer> q = new ArrayDeque<>();
        //넣을때 cost 업데이트 해야함
        int[] connect = getConNode(begin,words);
        for(int ele : connect){
            q.offer(ele);
            cost[ele]=1;
        }
        while(!q.isEmpty()){
            int temp = q.remove();
            // 업데이트
            connect = getConNode(words[temp],words);
            for(int ele : connect){
                if (cost[ele]==0){
                    q.offer(ele);
                    cost[ele]=cost[temp]+1;
                }
            }
        }
        
        int targetN = -1;
        for(int i=0;i<words.length;i++){
            if (target.equals(words[i]))
                targetN = i;
        }
        
        if (targetN==-1)
            return 0;
        else 
            return cost[targetN];
    }
    public int[] getConNode(String now, String[] words){
        List<Integer> temp = new ArrayList<>();
        char[] a = now.toCharArray();
        for(int i=0;i<words.length;i++){
            int diff = 0;
            char[] b = words[i].toCharArray();
            for(int j=0;j<a.length;j++){
                if(a[j]!=b[j])
                    diff++;
            }
            if (diff==1)
                temp.add(i);
        }
        int[] conNode = new int[temp.size()];
        for(int i=0;i<temp.size();i++){
            conNode[i] = temp.get(i);
        }
        return conNode;
    }
}