class Solution {
    public long solution(int n, int[] times) {
        long answer = 0;
        // n명 입국 심사 대기 심사대마다 차이
        // 심사관 10만명, 10억, 사람도 10억
        // 한명씩 처리하면 시간초과임
        // log나 루트로 처리해야됨
        // 가중치를 두고 배열을 나누기?
        // 가중치는 LCD로 계산해야되는데 어떻게 함
        long min = 0;
        long max = 0;
        for(int time : times){
            if (time>max)
                max = time;
        }
        max = max * n;
        
        while(min<=max){
            long people = 0;
            long mid = (min+max)/2;
            for(int time : times){
                people += mid/time;
                if(people>n)
                    break;
            }
            // people >=n 줄이기
            if (people>=n)
                max = mid-1;
            else
                min = mid+1;
            
        }
        return (min+max+1)/2;
    }

}