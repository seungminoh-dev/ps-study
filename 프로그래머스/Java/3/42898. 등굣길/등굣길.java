class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        // 오른쪽과 아래쪽으로만 갈 수 있다 -> 왼쪽이나 위에서만 올 수 있다
        // (1,1) -> 나름의 힌트일듯? 왼쪽과 위만 0으로 감싸면 DP로 처리하면 됨
        // 왼쪽 + 위의 값 -> 내 값으로 갱신(가짓수)
        // 첫열 갱신
        // 둘째열 갱신 왼쪽 -> 오른쪽 이러면 왼쪽값 ok 위의 값 ok
        // 최대 만개 좌표 -> 10개 puddies 검사 -> 물 검사 10만번 진행됨
        // 시작 좌표는 1로 설정
        int modified = 1000000007;
        int[] cost = new int[m*n];
        int[] transPuddles = new int[puddles.length];
        for(int i=0;i<puddles.length;i++){
            int[] point = puddles[i];
            transPuddles[i] = transXY(point,m);
        }
        //전부 일단 1로 채움
        for(int i=0;i<cost.length;i++){
            cost[i]=1;
        }
        //웅덩이는 0으로 채움
        for(int puddle : transPuddles){
            cost[puddle] = 0;
        }
        //첫줄은 웅덩이 있으면 옆으로 전파해야함
        int fillFlag = 1;
        for(int i=0;i<m;i++){
            if (cost[i]==0)
                fillFlag = 0;
            cost[i]=fillFlag;
        }
        fillFlag = 1;
        for(int i=0;i<n;i++){
            if (cost[i*m]==0)
                fillFlag = 0;
            cost[i*m]=fillFlag;
        }
        //가로첫줄,세로첫줄은 갱신 안함, 나머지는 왼쪽 + 위의 합으로 갱신하고 1,000,000,007로 나눠서 갱신하기
        for(int i=0;i<cost.length;i++){
            //가로 첫줄
            if(i<m)
                continue;
            //세로 첫줄
            if(i%m==0)
                continue;
            //웅덩이면 Skip
            if (cost[i]==0)
                continue;
            //갱신
            cost[i] = (cost[i-1]+ cost[i-m])%modified;
        }
        int[] target = new int[2];
        target[0]=m;
        target[1]=n;
        return cost[transXY(target,m)];
    }
    public int transXY(int[] point,int m){
        // x y y-1 * m + x-1
        return (point[1]-1) * m + point[0]-1;
    }
}