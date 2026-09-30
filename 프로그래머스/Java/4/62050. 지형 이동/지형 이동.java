import java.util.*;

class Solution {
    
    public int[] parent = null;
    public int[] size = null;
    public int n = 0;
    
    public int find(int x){
        while(parent[x] != x){
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
    
    public boolean union(int a,int b){
        a = find(a);
        b = find(b);
        if(a==b) return false;
        if(size[a]<size[b]) {int t=a; a=b; b=t;}
        parent[b]=a;
        size[a] += size[b];
        return true;
    }
    
    public record Edge(int start,int end, int cost){}
    
    public int solution(int[][] land, int height) {
        int answer = 0;
        n = land.length;
        parent = new int[n*n];
        for(int i=0;i<parent.length;i++){
            parent[i] = i;
        }
        size = new int[n*n];
        PriorityQueue<Edge> edgeMap = new PriorityQueue<>((a,b)->a.cost-b.cost);
        // Edge 만들기
        for(int i=0;i<n;i++)
            for(int j=0;j<n;j++){
                // i=n-1, j=n-1이면 종료
                if(i==n-1 && j==n-1)
                    break;
                // j=n-1이면 아래만 추가
                if(j==n-1){
                    int start = trans(i,j,n);
                    int end = trans(i+1,j,n);
                    int cost = Math.abs(land[i][j]-land[i+1][j]);
                    cost = cost > height? cost : 0;
                    edgeMap.offer(new Edge(start,end,cost));
                } else if(i==n-1){
                    int start = trans(i,j,n);
                    int end = trans(i,j+1,n);
                    int cost = Math.abs(land[i][j]-land[i][j+1]);
                    cost = cost > height? cost : 0;
                    edgeMap.offer(new Edge(start,end,cost));
                } else {
                    int start = trans(i,j,n);
                    int end = trans(i+1,j,n);
                    int cost = Math.abs(land[i][j]-land[i+1][j]);
                    cost = cost > height? cost : 0;
                    edgeMap.offer(new Edge(start,end,cost));
                    
                    start = trans(i,j,n);
                    end = trans(i,j+1,n);
                    cost = Math.abs(land[i][j]-land[i][j+1]);
                    cost = cost > height? cost : 0;
                    edgeMap.offer(new Edge(start,end,cost));
                }
                
            }
        
        // start cruscal
        while(!edgeMap.isEmpty()){
            Edge temp = edgeMap.remove();
            if (union(temp.start,temp.end)){
                answer+=temp.cost;
            }
        }
        
        return answer;
    }
    
    public int trans(int x,int y,int colNum){
        return x*colNum+y;
    }
    
    public int[] reverseTrans(int num, int colNum){
        int[] temp = new int[2];
        temp[0]=num/colNum;
        temp[1]=num%colNum;
        return temp;
    }
    
}