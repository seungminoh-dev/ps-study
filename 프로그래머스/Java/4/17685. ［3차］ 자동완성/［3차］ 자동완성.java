import java.util.*;

class Solution {
    public int solution(String[] words) {
        int answer = 0;
        Node[] head = new Node[26];
        // word 꺼내기
        for(int i=0;i<words.length;i++){
            int wordLen = words[i].length();
            //첫번째거 할당
            if(head[words[i].charAt(0)-'a']==null){
                head[words[i].charAt(0)-'a'] = new Node();
            }else{
                head[words[i].charAt(0)-'a'].count++;
            }
            Node now = head[words[i].charAt(0)-'a'];
            for(int j=1;j<wordLen;j++){
                char target = words[i].charAt(j);
                if(now.next[target-'a']==null){
                    now.next[target-'a'] = new Node();
                }else{
                    now.next[target-'a'].count++;
                }
                now = now.next[target-'a'];
            } 
        }
        // word 조회
        for(int i=0;i<words.length;i++){
            int wordLen = words[i].length();
            Node now = head[words[i].charAt(0)-'a'];
            if(now.count<=1){
                answer+=1;
                continue;
            }
            boolean fastFind = false;
            for(int j=1;j<wordLen;j++){
                now = now.next[words[i].charAt(j)-'a'];
                if(now.count<=1){
                    answer+=(j+1);
                    fastFind=true;
                    break;
                }
            }
            if (!fastFind) answer+=wordLen;
            
        }
        return answer;
    }
    
    class Node{
        Node[] next = new Node[26];
        int count = 1;
    }
}