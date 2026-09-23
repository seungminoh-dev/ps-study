import java.util.*;

class Solution {
    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        //순서쌍 찾기 문제 -> 중복이면 1개의 쌍으로 취급
        //아이디 하나는 길이 8이하고, 전체 개수도 8이하 <- 시간 복잡도는 일단 널널(물론 기준은 모름 최적 알고리즘으로 짜야지)
        //한아이디가 여러개에 들어갈 수 있음 -> 서로 연관이 있는 문제임 -> 확통에서 분할 느낌이라 해야되나?
        //일단 제일 쉬운건 브루투포스, 생각보다 원소갯수 안많아서 브루투포스도 가능하긴하다.
        //다세면 최악 8^8 대충 O(천만) 정도라 되긴할듯? 물론 n^n이라는 최악의 알고리즘이지만
        
        int[][] map = new int[banned_id.length][];
        for(int i=0;i<banned_id.length;i++){
            List<Integer> target = new ArrayList<>();
            
            String temp = banned_id[i];
            char[] tempArray = temp.toCharArray();
            for(int j=0;j<user_id.length;j++){
                char[] targetArray = user_id[j].toCharArray();
                if(tempArray.length!=targetArray.length)
                    continue;
                else{
                    boolean find = true;
                    for(int k=0;k<tempArray.length;k++){
                        if(tempArray[k]=='*')
                            continue;
                        else
                            if(tempArray[k]!=targetArray[k]){
                                find = false;
                                break;
                            }
                    }
                    if (find) target.add(j);
                }
            }
            int[] finalArray = new int[target.size()];
            for(int l=0;l<finalArray.length;l++){
                finalArray[l] = target.get(l);
            }
            map[i] = finalArray;
        }
        
        int[] nextInd = new int[map.length];
        Set<String> jungbok = new HashSet<>();
        //For Main
        while(true){
            if (check(nextInd,map,jungbok))
                answer++;
            nextInd[0]++;
            for(int i=0;i<nextInd.length;i++){
                if (nextInd[i]==map[i].length){
                    if(i==nextInd.length-1)
                        return answer;
                    nextInd[i]=0;
                    nextInd[i+1]++;
                }
            }
        }
    }
    boolean check(int[] johab, int[][] mapdata, Set<String> jungbok){
        // johab -> mapdata[0~][johab[0~]]
        Set<Integer> duplicate = new HashSet<>();
        for(int i=0;i<johab.length;i++){
            if (duplicate.contains(mapdata[i][johab[i]])){
                return false;
            }else{
                duplicate.add(mapdata[i][johab[i]]);
            }
        }
        List<Integer> mlist = new LinkedList<>();
        for(Integer me : duplicate){
            mlist.add(me);
        }
        Collections.sort(mlist);
        String mmm = "";
        for(Integer mme : mlist){
            mmm+=String.valueOf(mme);
        }
        if(jungbok.contains(mmm))
            return false;
        else{
            jungbok.add(mmm);
             return true;
        }
           
    }
}