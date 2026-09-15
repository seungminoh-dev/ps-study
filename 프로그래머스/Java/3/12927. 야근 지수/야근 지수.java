import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        // 1을 썼을 때 => 1-2a만큼 감소(a가 클수록 최적 행동임이 보장됨)
        // 그럼 O(100만) ? 아슬아슬하게 통과할거 같긴한데
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        for(int ele : works){
            pq.offer(ele);
        }
        for(int i=0;i<n;i++){
            if(pq.isEmpty()) break;
            if(pq.peek()==0) break;
            pq.offer(pq.remove()-1);
        }
        for(int ele : pq){
            answer+=(long)ele * (long) ele;
        }
        return answer;
    }
}