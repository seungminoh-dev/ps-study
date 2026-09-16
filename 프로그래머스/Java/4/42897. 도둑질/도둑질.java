class Solution {
    public int solution(int[] money) {
        int answer = 0;
        // i번째 집을 털기로 결정했을 때 i번째 집까지의 최댓값은 Max(money[i-3],money[i-2])+money[i]
        // 첫번째 집 선택 시 -> 마지막 집 선택 불가 max(money[len-2],money[len-1])
        // 첫번째 집 선택 안하면 -> max(money[len],money[len-1])
        if (money.length==3){
            int max = -1;
            for(int ele : money){
                if (max<ele)
                    max=ele;
            }
            return max;
        }
        int[] sumFS = new int[money.length];
        int[] sumFNS = new int[money.length];
        sumFS[0] = money[0];
        sumFS[1] = 0;
        sumFS[2] = money[0]+money[2];
        
        sumFNS[0] = 0;
        sumFNS[1] = money[1];
        sumFNS[2] = money[2];
        
        for(int i=3;i<money.length;i++){
            sumFS[i] = Math.max(sumFS[i-3],sumFS[i-2])+money[i];
            sumFNS[i] = Math.max(sumFNS[i-3],sumFNS[i-2])+money[i];
        }
        
        int n = money.length;
        return Math.max(Math.max(sumFS[n-2],sumFS[n-3]),Math.max(sumFNS[n-1],sumFNS[n-2]));
    }
}