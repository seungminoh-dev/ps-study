import java.util.*;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        // HashMap에 TargetKey등록 -> Target조회 O(1)
        Map<String,Integer> productMap = new HashMap<>();
        for(int i=0;i<want.length;i++){
            productMap.put(want[i],-number[i]);
        }
        
        // 현재 Window 범위 기억
        int windowStart = 0;
        int windowEnd = 9;
        
        // Window 10개씩 상태 관리
        for(int i=windowStart;i<=windowEnd;i++){
            if(productMap.containsKey(discount[i])){
                productMap.put(discount[i],productMap.get(discount[i])+1);
            }
        }
        
        // Loop 돌며 상태 검사
        boolean checkStatus = true;
        int answer=0;
        
        for(int i=windowEnd;i<discount.length;i++){
            checkStatus = true;
            
            //상태검사
            for(String key:productMap.keySet()){
                if(productMap.get(key)<0){
                    checkStatus=false;
                    break;
                }
            }
            if(checkStatus) answer++;
            
            //다음 상태 반영
            if(windowEnd<discount.length-1){
                if(productMap.containsKey(discount[windowEnd+1])){
                    productMap.put(discount[windowEnd+1],productMap.get(discount[windowEnd+1])+1);
                }
                if(productMap.containsKey(discount[windowStart])){
                    productMap.put(discount[windowStart],productMap.get(discount[windowStart])-1);
                }
                windowEnd++;
                windowStart++;
            }
            
        }
        
        return answer;
    }
}