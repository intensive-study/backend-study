import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11404 {
  public static void main(String[] args) throws Exception {
    new BOJ11404().solution();
  }

  private void solution() throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    
    final int NUM_OF_CITY = Integer.parseInt(br.readLine());
    
    int[][] adj = new int[NUM_OF_CITY+1][NUM_OF_CITY+1];
    
    for (int i = 0; i < adj.length; i++) {
      Arrays.fill(adj[i], Integer.MAX_VALUE);
      adj[i][i] = 0;
    }
    
    StringTokenizer st;
    int numOfRoute = Integer.parseInt(br.readLine());
    for (int i = 0; i < numOfRoute; i++) {
      st = new StringTokenizer(br.readLine());
      int from = Integer.parseInt(st.nextToken());
      int to = Integer.parseInt(st.nextToken());
      int cost = Integer.parseInt(st.nextToken());
      adj[from][to] = Math.min(adj[from][to],cost);
    }

    for(int mid = 1; mid<= NUM_OF_CITY; mid++){
      for(int from = 1; from <= NUM_OF_CITY; from++){
        if(mid == from) continue;
        for(int to = 1; to <=NUM_OF_CITY; to++){
          if(to == from || mid == to) continue;
          if(adj[from][mid] == Integer.MAX_VALUE || adj[mid][to] == Integer.MAX_VALUE) continue;
          adj[from][to] = Math.min(adj[from][to], adj[from][mid]+adj[mid][to]);
        }
      }
    }
    // 지영님 코드 참고
    // StringBuilder 적용하기
    StringBuilder sb = new StringBuilder();
    sb = new StringBuilder();
        for (int row = 1; row <= NUM_OF_CITY ; row++) {
            for (int col = 1; col <= NUM_OF_CITY ; col++) {
                sb.append(adj[row][col] == Integer.MAX_VALUE? 0: adj[row][col]).append(" ");
            }
            sb.append("\n");
        }

    System.out.println(sb);

    // 기존 출력 코드
    // for (int i = 1; i < adj.length; i++) {
    //   for (int j = 1; j < adj.length; j++) {
    //     int cost = adj[i][j];
    //     if(cost == Integer.MAX_VALUE) cost = 0;
    //     System.out.print(cost);
    //     System.out.print(" ");
    //   }
    //   System.out.println();
    // }
  }
}
