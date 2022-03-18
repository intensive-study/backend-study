import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * BOJ06087
 */
public class BOJ11066 {
  public void solution() throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int TOTAL_CASE = Integer.parseInt(st.nextToken());

    for (int i=0;i<TOTAL_CASE;i++) {
      st = new StringTokenizer(br.readLine());
      int totalFiles = Integer.parseInt(st.nextToken());

      int[] fileSizes = new int[totalFiles];
      int[][] dp = new int[totalFiles][totalFiles];
      
      st = new StringTokenizer(br.readLine());
      for (int j =0;j<totalFiles;j++) {
        fileSizes[j] = Integer.parseInt(st.nextToken());
      }

      getDP(dp, fileSizes);      

      System.out.println(dp[0][totalFiles-1]);
    }
  }

  public void getDP(int[][] dp, int[] fileSizes) {
    for (int sumLength = 1; sumLength < fileSizes.length; sumLength++) {
      for (int startIndex=0; startIndex + sumLength < fileSizes.length; startIndex++) {
        int sumFileSizes = 0;
        for (int i = startIndex; i <= startIndex + sumLength; i++) {
          sumFileSizes += fileSizes[i];
        }
        for (int splitIndex = startIndex; splitIndex < startIndex+sumLength; splitIndex++) {
          int splitPointSum = dp[startIndex][splitIndex] + dp[splitIndex+1][startIndex+sumLength] + sumFileSizes;
          if (dp[startIndex][startIndex+sumLength] ==  0 || dp[startIndex][startIndex+sumLength] > splitPointSum) {
            dp[startIndex][startIndex+sumLength] = splitPointSum;
          }
        }
      } 
    }
  }
  public static void main(String[] args) throws Exception {
    new BOJ11066().solution();
  }
}
