import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * BOJ18427
 */

class BOJ18427 {

  public static void solution() throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // Final variable 
    final int N = Integer.parseInt(st.nextToken());
    final int M = Integer.parseInt(st.nextToken());
    final int H = Integer.parseInt(st.nextToken());

    int[][] studentsBlocks = new int[N][M];
    int[] counts = new int[H+1];
    counts[0] =1;

    for(int i=0;i<N;i++){
      st = new StringTokenizer(br.readLine());
      for(int j=0;j<M;j++) {
        if (!st.hasMoreTokens()) break;
        studentsBlocks[i][j] = Integer.parseInt(st.nextToken());
      }
    }

    for(int[] studentBlocks:studentsBlocks) {
      ArrayList<int[]> dataList = new ArrayList<int[]>(H*N);
      for(int i =0;i<H+1;i++) {
        for (int block:studentBlocks) {
          if (block == 0 ) break;
          if (counts[i] !=0 && i+block <= H) {
            int[] temp = new int[2];
            temp[0] = i+block;
            temp[1] = counts[i]
            dataList.add(temp);
          }
        }
      }
      for(int[] data : dataList) {
        int h = data[0];
        int count = data[1];
        counts[h] = (counts[h]+count)%10007;
      }
    }

    System.out.println(counts[H]);


  }
  public static void main(String[] args) throws Exception{
    new BOJ18427().solution();
  }
}