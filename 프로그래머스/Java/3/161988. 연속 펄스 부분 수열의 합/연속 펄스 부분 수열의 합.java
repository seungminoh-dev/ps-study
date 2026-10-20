import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        //한번 더하면 한번 빼야된다는 거임
        //최대 원소 개수는 50만개, 각 원소는 -10만에서 10만
        //DP로 풀릴듯?
        //"내가 -1로 끝날떄 최대" , "내가 1로 끝날때 최대"
        //"내가 -1로 끝날때 최대" 배열 a
        //"내가 1로 끝날때 최대" 배열 b
        // a(n) = max(-a(n),b(n-1)-a(n))
        // b(n) = max(b(n),a(n-1)+b(n))
        // 갱신을 -1 1 -1 1 이 순서로 한번 하고
        // 1 -1 1 -1 이 순서로 해야됨
        // 그러면 처음거 채워놓으면 교차로 갱신되네
        
        // -1이 n=0일때부터 갱신
        
        long[] a = new long[sequence.length];
        long[] b = new long[sequence.length];
        
        a[0] = -sequence[0];
        b[0] = sequence[0];
        
        long answer = a[0]>b[0] ? a[0] : b[0];
        
        for(int i=1;i<sequence.length;i++){
            a[i] = Math.max(-sequence[i],b[i-1]-sequence[i]);
            b[i] = Math.max(sequence[i],a[i-1]+sequence[i]);
            answer = a[i] > answer ? a[i] : answer;
            answer = b[i] > answer ? b[i] :answer;
        }
        
        return answer;
    }
}