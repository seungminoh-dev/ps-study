import java.util.*;

class Solution {
    public int solution(String[] strs, String t) {
        int answer = 0;

        // 단어 최대 100개, 중복X
        // 단어 하나 길이 최대 5
        // 완성해야 하는 문자열 길이 최대 2만
        // 알파벳 소문자로만 구성되어 있음
        
        // 선택지 100개 
        
        // 노드를 무한 방문 가능 -> 그래프쪽은 아닐듯..
        // 길이가 5라는게 힌트?
        // DP 배열? target t를 길이까지 만들 때, 최소 단어수로 정의
        // DP[i]->DP[i+1]~DP[i+5]를 갱신 가능한 구조
        // 100개씩 *5번 조회 = 500개 , 전체 길이 2만 -> 500개 * 2만 = 1000만 (아슬아슬? 아마 될거같긴한데)
        // 500 -> O(1)로 줄일수 있네 Set으로 (무한대니까)
        
        Set<String> inCheck = new HashSet<>();
        for(String str : strs){
            inCheck.add(str);
        }
        
        // Cost map 생성 -> target은 t 이고 검사는 inCheck에서 하기
        int[] cost = new int[t.length()];
        // substring(시작위치,길이) 로 접근하고 길이는 최대 5이며, 시작위치+길이<t.length() 이어야 함.
        
        // 초기 세팅하기(0~4까지)
        for(int i=0;i<Math.min(5,t.length());i++)
            cost[i] = inCheck.contains(t.substring(0,i+1)) ? 1 : 0;
        
        // cost[0] 부터 전파
        for(int i=0;i<t.length()-1;i++){
            // i가 지금 위치 -> i부터 1,2,3,4,5 전파 but i+2,3,4,5,6 < t.length()
            // unrechable node
            if (cost[i]==0){
                continue;
            }
            // rechable node
            for(int j=1;j<=5;j++){
                if ((i+j)<t.length()){
                    if (inCheck.contains(t.substring(i+1,i+1+j)))
                        cost[i+j]=Math.min(cost[i+j]==0?30000:cost[i+j],cost[i]+1);
                }else{
                    break;
                }
            } 
        }
        return cost[t.length()-1]==0?-1:cost[t.length()-1];
    }
}