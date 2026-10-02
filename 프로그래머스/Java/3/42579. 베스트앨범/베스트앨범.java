import java.util.*;

class Solution {
    
    public record Music(int index, int play){}
    public record Genre(String key, int play){}
    
    public int[] solution(String[] genres, int[] plays) {
        int[] answer = {};
        // 개수 최대 만개
        // 종류 100개 미만
        // 장르에 속한 곡이 하나면 하나만 수록해라
        
        // 장르 숫자를 카운트 하는 Map
        // 장르별로 PriorityQueue
        
        Map<String,Integer> playCount = new HashMap<>();
        Map<String,PriorityQueue<Music>> album = new HashMap<>();
        
        for(int i=0;i<genres.length;i++){
            playCount.put(genres[i],playCount.getOrDefault(genres[i],0)+plays[i]);
            if(album.containsKey(genres[i])){

            }else{
                album.put(genres[i],new PriorityQueue<Music>((a,b)-> a.play()!=b.play() ? b.play()-a.play() : a.index()-b.index()));
                
            }
            album.get(genres[i]).offer(new Music(i,plays[i]));
        }
        
        PriorityQueue<Genre> gQ = new PriorityQueue<>((a,b)->b.play()-a.play());
        
        for(var ele : playCount.entrySet()){
            gQ.offer(new Genre(ele.getKey(),ele.getValue()));
        }
        
        List<Integer> answerList = new ArrayList<>();
        
        while(!gQ.isEmpty()){
            String nowGenre = gQ.remove().key();
            PriorityQueue<Music> tempQ = album.get(nowGenre);
            answerList.add(tempQ.remove().index());
            if(!tempQ.isEmpty())
                answerList.add(tempQ.remove().index());
        }
        
        answer = new int[answerList.size()];
        for(int i=0;i<answerList.size();i++){
            answer[i]=answerList.get(i);
        }
        return answer;
    }
}