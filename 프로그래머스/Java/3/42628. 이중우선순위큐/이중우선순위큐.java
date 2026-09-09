import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        Map<Integer,Integer> numCount = new HashMap<>();
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        for(String op : operations){
            //삽입인지 확인
            if(op.charAt(0)=='I'){
                Integer num = Integer.valueOf(op.substring(2));
                numCount.put(num,numCount.getOrDefault(num,0)+1);
                minHeap.offer(num);
                maxHeap.offer(num);
            }else if(op.equals("D -1")){
                //최솟값 삭제
                while(!minHeap.isEmpty()){
                    Integer target = minHeap.remove();
                    if(numCount.getOrDefault(target,0)>0){
                        numCount.put(target,numCount.get(target)-1);
                        break;
                    }
                }
            }else{
                //최댓값 삭제
                while(!maxHeap.isEmpty()){
                    Integer target = maxHeap.remove();
                    if(numCount.getOrDefault(target,0)>0){
                        numCount.put(target,numCount.get(target)-1);
                        break;
                    }
                }
            }
        }
        // 저 여기에요
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int tnum:numCount.keySet()){
            int mtarget = numCount.get(tnum);
            if(mtarget>0){
                if(max<tnum)
                    max=tnum;
                if(min>tnum)
                    min=tnum;
            }
        }
        if(max==Integer.MIN_VALUE)
            return new int[]{0,0};
        else
            return new int[]{max,min};
    }
}