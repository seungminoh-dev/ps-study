import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        PriorityQueue<int[]> mQueue = new PriorityQueue<>((a,b)->a[1]-b[1]);
        for(int[] ele : routes){
            mQueue.offer(ele);
        }
        int before = -30001;
        while(!mQueue.isEmpty()){
            int[] temp = mQueue.remove();
            if (before<temp[0]){
                before = temp[1];
                answer++;
            }
        }
        return answer;
    }

}