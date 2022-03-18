import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865 {
  public void solution() throws Exception {
    // 입력을 받기 위한 인스턴스 생성
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 상수값 저장
    final int N = Integer.parseInt(st.nextToken());
    final int K = Integer.parseInt(st.nextToken());

    // 물건과 무게별 최대 가치 기록을 위한 array 생성
    int[][] dp = new int[N+1][K+1];

    // 물건은 하나씩 받으면서 최대 가치 구하기
    for(int i = 1; i<N+1;i++) {
      st = new StringTokenizer(br.readLine());
      int w = Integer.parseInt(st.nextToken());
      int v = Integer.parseInt(st.nextToken());

      // 가능 무게별 최대값 구하기
      for(int j =1;j<K+1;j++) {
        if (j < w) {
          // 해당 물건을 무게 때문에 넣지 못할 경우
          dp[i][j] = dp[i-1][j]; 
        } else { 
          // 물건 들어갈 수 있을 경우, 가치 비교
          dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-w]+v);
        }
      }
    }
    // N개의 물품을 고려했을 때, K 무게에서의 최대 가치
    System.out.print(dp[N][K]);
  }
  public static void main(String[] args) throws Exception{
    new BOJ12865().solution();
  }
}