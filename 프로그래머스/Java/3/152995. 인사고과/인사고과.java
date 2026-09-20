import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = -1;
        PriorityQueue<Point> pq = new PriorityQueue<>((a,b)->b.sum-a.sum);
        pq.offer(new Point(scores[0],true));
        for(int i=1;i<scores.length;i++){
            int[] score = scores[i];
            pq.offer(new Point(score));
        }
        int count = 1;
        int nowRank = 1;
        Point temp = pq.remove();
        int beforeSum = temp.sum;
        Set<Point> mSet = new HashSet<>();
        mSet.add(temp);
        if(temp.isWanho) return nowRank;
        
        while(!pq.isEmpty()){
            temp = pq.remove();
            count++;
            int result = nowRank;
            for(Point check : mSet){
                if(check.a>temp.a && check.b>temp.b){
                    count--;
                    result = -1;
                    break;
                }
            }
            if(result!=-1){
                if(beforeSum != temp.sum){
                    nowRank = count;
                    beforeSum = temp.sum;
                    result = nowRank;
                }else{
                    result = nowRank;
                }
                mSet.add(temp);
            }
            if(temp.isWanho) return result;
        }
        return answer;
    }
    static class Point{
        int a;
        int b;
        int sum;
        boolean isWanho=false;
        Point(int[] score){
            a = score[0];
            b = score[1];
            sum = a+b;
        }
         Point(int[] score,boolean flag){
            a = score[0];
            b = score[1];
            sum = a+b;
            isWanho = flag;
        }
    }
}