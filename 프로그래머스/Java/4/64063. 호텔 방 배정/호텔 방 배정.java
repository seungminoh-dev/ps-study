import java.util.*;

class Solution {
    public long[] solution(long k, long[] room_number) {
        long[] answer = {};
        // 방이 총 k개(1~k)
        // 메모리를 1조개 만들면 당연히 안되겠지?
        // room_number은 20만개 -> 즉 사람은 20만명 밖에 안됨!
        // 예를 들어 [1,1,1,1,..] 20만개면 1+2+..+20만번 탐색 -> 20만 * 10만 = 200억
        // 그럼 넣을 방을 logn으로 가지고 있을 수가 있나?
        // HashMap에 중복시 다음 넣을 수 있는 위치를 가지고 있는다면?
        // 그래도 여러번 타야되는 건 같고
        // 그럼 20만개에 전처리를 하면? 예를 들어 넣은 숫자와 같은 숫자는 +1를 하는거임
        // 그래도 10만 * 20만인데? 흠
        // 일단 정확도 테스트만 통과 역시 예상한대로임

        // 어차피 루프틑 n번 돌아야 되고 여기서 logn으로 처리하는게 관건임
        // 넣는 위치를 logn으로 판정할 수 있나? 그래프 이론이 답인가
        answer = new long[room_number.length];
        
        Map<Long,Long> mGraph = new HashMap<>();
        
        for(int i=0;i<room_number.length;i++){
            long target = room_number[i];
            //finalDestination을 찾아야됨
            List<Long> root = new ArrayList<>();
            long finalDestination = target;
            root.add(target);
            while(mGraph.containsKey(finalDestination)){
                root.add(finalDestination);
                finalDestination = mGraph.get(finalDestination);
            }
            answer[i] = finalDestination;
            root.add(finalDestination);
            mGraph.put(finalDestination,finalDestination+1);
            //이후 finalDestination을 Next Node를 갱신해야 함
            long nextDestination = finalDestination+1;
            while(mGraph.containsKey(nextDestination)){
                nextDestination = mGraph.get(nextDestination);
            }
            for(Long ele : root){
                mGraph.put(ele,nextDestination);
            }
            
        }
        
        return answer;
    }
}