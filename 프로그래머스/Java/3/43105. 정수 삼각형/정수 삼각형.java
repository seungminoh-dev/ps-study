import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = -1;
        // 최대 depth 500 = 만정도? element
        // 1부터 시작 -> dsdsdsdsdsdsd
        // 1층 (최적) 2층 (최적) 3층 (최적) ... 즉 층별로 최적화가 되기 때문에 O(n)으로 돌면 된다
        int floor = triangle.length;
        int allCount = (1+floor)*floor/2;
        int[] value = new int[allCount+1];
        int startFlag = allCount - floor +1;
        int count = 1;
        int nFloor = 1;
        int floorCount = 1;
        for(int[] ele : triangle)
            for(int temp : ele)
                value[count++]=temp;
        int[] sum = new int[value.length];
        sum[1] = value[1];
        for(int i=1;i<startFlag;i++){
            sum[i+nFloor] = Math.max(sum[i+nFloor],sum[i]+value[i+nFloor]);
            sum[i+nFloor+1]=Math.max(sum[i+nFloor+1],sum[i]+value[i+nFloor+1]);
            if(floorCount==nFloor){
                floorCount=1;
                nFloor++;
                continue;
            }
            floorCount++;
        }
        for(int i=startFlag;i<sum.length;i++){
            answer = Math.max(answer,sum[i]);
        }
        return answer;
    }
}