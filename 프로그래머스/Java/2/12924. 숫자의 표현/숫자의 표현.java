class Solution {
    public int solution(int n) {
        int answer = 0;
        // target: 시작n 끝k -> (n+k)*(k-n+1)/2 하고 Early Stopping 하면됨
        for(int i=1;i<n;i++)
            for(int k=i+1;k<n;k++){
                int sum = (i+k)*(k-i+1)/2;
                if (sum>n)
                    break;
                if (sum==n){
                    answer++;
                    continue;
                }
            }
        return answer+1;
    }
}