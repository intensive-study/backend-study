import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * BOJ02110
 */
public class BOJ02110 {
  public void solution() throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int N = Integer.parseInt(st.nextToken());
    final int C = Integer.parseInt(st.nextToken());
    int[] house = new int[N];
    for(int i = 0; i < N; i++) {
      st = new StringTokenizer(br.readLine());
      house[i] = Integer.parseInt(st.nextToken());
    }
    Arrays.sort(house);
    
    if (C == 2) {
      System.out.println(house[N-1] - house[0]);
    } else {
      int[] gaps = new int[N-1];
      int minGap = house[1]-house[0]; 
      for(int i = 0; i < N-1; i++) {
        gaps[i] = house[i+1]-house[i];
        if (minGap > gaps[i]) {
          minGap = gaps[i];
        }
      }
      int start = minGap;
      int end = house[N-1] - house[0];

      int result = 0;
      while (start <= end) {
        int g = (start + end) / 2;
        if (getCount(g, gaps) >= C-1) {
          start = g+1;
          if (result < g) {
            result = g;
          } 
        } else {
          end = g -1;
        }
      }
      System.out.println(result);
    }
  }

  public int getCount(int g, int[] gaps) {
    int count = 0;
    int stack = 0;
    for (int gap: gaps) {
      stack += gap;
      if (stack >= g) {
        count++;
        stack = 0;
      }
    }
    return count;
  }

  public static void main(String[] args) throws Exception {
    new BOJ02110().solution();
  }
}
