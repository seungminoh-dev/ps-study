class Solution {
    public int solution(int n, int[] money) {
        
        //연립일차방정식 풀기문제
        //n은 10만 이하
        // 화폐 종류는 100종류 이하
        // 경우의수라 DP어렵고
        // 그래프 탐색도 아니고
        
        int modif = 1000000007;
        
        int[] count = new int[n+1];
        
        for(int gang: money){
            count[gang] +=1;
            for(int i=gang;i<=n;i++ ){
                count[i] += (count[i-gang])%modif;
            }    
        }
        
        return count[n];
    }
}