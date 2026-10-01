import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = {};
        // n은 최대 만개, s는 최대 1억개
        // 느낌은 Greedy긴함
        if (n>s)
            return new int[]{-1};
        if (n<s){
            answer = new int[n];
            int count = n-s % n ;
            for(int i=0;i<n;i++){
                answer[i]=s/n + (i >= count ? 1 : 0);

            }
        }
        return answer;
    }
}