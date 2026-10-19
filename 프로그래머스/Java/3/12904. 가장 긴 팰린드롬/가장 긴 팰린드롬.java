class Solution
{
    public int solution(String s)
    {
        int answer = 1;
        
        // 2500
        // 정답은 3,5,7 홀수로 간다
        // 3개씩 비교하면 2500번*1250 => 안인거 같은데?
        // 정답이 2,4,6 인 경우도 있음...
        
        char[] allString = s.toCharArray();
        
        
        for(int i=1;i<allString.length-1;i++){
            int depth = 0;
            while(0<=i-depth-1 && i+depth+1<=allString.length-1){
                if(allString[i-depth-1]==allString[i+depth+1])
                    depth++;
                else
                    break;
            }
            answer = answer < depth*2 +1 ? depth*2+1 : answer;
        }
        
        for(int i=0;i<allString.length-1;i++){
            int depth = 0;
            while(0<=i-depth && i+1+depth<allString.length){
                if(allString[i-depth]==allString[i+1+depth])
                    depth++;
                else
                    break;
            }
            answer = answer < depth*2 ? depth*2 : answer;
        }
        
        return answer;
    }
}