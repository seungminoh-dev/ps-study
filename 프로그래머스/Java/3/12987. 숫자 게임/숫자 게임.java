import java.util.*;

class Solution {
    public int solution(int[] A, int[] B) {
        int answer = 0;
        PriorityQueue<Integer> pq1 = new PriorityQueue<Integer>(Comparator.reverseOrder());
        PriorityQueue<Integer> pq2 = new PriorityQueue<Integer>(Comparator.reverseOrder());
        for(int i=0;i<A.length;i++){
            pq1.offer(A[i]);
            pq2.offer(B[i]);
        }
        
        //B에서 pop되면 answer++
        int target = -1;
        while(!pq1.isEmpty() && !pq2.isEmpty()){
            target = pq1.remove();
            if (pq2.peek()>target){
                pq2.remove();
                answer++;
            }
        }
        
        return answer;
    }
}