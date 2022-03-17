import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ11657
 * edge case가 생각보다 많아서 어려웠음.
 * edge case 1 : 같은 경로(from/to가 같음)의 값이 여러 개가 나와서 overwrite 되는 경우
 * edge case 2 : 극한의 값으로 int type의 overflow 혹은 underflow가 발생하는 경우
 */

class BOJ11657 {

  public static void solution() throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // Final variable 
    final int N = Integer.parseInt(st.nextToken()); // num of bus
    final int M = Integer.parseInt(st.nextToken()); // num of route

    // bus route infomation : busRouteInfo[from][to] => time
    // time == null : not exist route
    // 1 <= bus Id <= N
    Integer[][] busRouteInfo = new Integer[N+1][N+1];

    // busRouteInfo initialize
    for(int i = 0;i < M;i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int time = Integer.parseInt(st.nextToken());
      if (busRouteInfo[from][to] == null || busRouteInfo[from][to] > time) {
        busRouteInfo[from][to] = time;
      }
    }

    // 최소 시간 초기화
    Long[] minRouteTimes = new Long[N+1];
    Arrays.fill(minRouteTimes, Long.MAX_VALUE);
    minRouteTimes[1] = (long) 0;
    
    
    boolean hasNegativeCycle = false;
    // N번 반복
    for (int iter = 1;iter<=N;iter++){
      boolean hasUpdated = false;

      // from / to 돌면서 routeTime update
      for (int from = 1;from <= N;from++) {
        for (int to = 1;to <= N;to++) {
          Integer time = busRouteInfo[from][to];

          // route가 존재하지 않을 경우
          if (time == null) continue;

          // 해당 위치까지 못 온 경우
          if (minRouteTimes[from] == Long.MAX_VALUE) continue;
          
          // routeTime이 감소하는 경우만 업데이트
          long routeTime = minRouteTimes[from] + time;
          if (minRouteTimes[to] > routeTime) {
            hasUpdated = true; 
            minRouteTimes[to] = routeTime;
          }
        }
      }
      // N번째 iteration에서 update가 발생하는 경우 음의 가중치 사이클이 존재
      if (iter == N && hasUpdated) {hasNegativeCycle = true;}
    }

    for(int i = 2;i <= N;i++) {
      // 음의 사이클의 존재하는 경우 -1 출력 후 중단
      if (hasNegativeCycle) {
        System.out.println(-1);
        break;
      } 
      long minRouteTime = minRouteTimes[i];
      System.out.println(minRouteTime != Long.MAX_VALUE ? minRouteTime : -1);
    }
  }
  public static void main(String[] args) throws Exception{
    new BOJ11657().solution();
  }
}