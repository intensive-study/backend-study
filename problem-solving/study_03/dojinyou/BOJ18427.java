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
    final int MOD = 10007;

    int[][] studentsBlocks = new int[N+1][M];
    int[][] studentsCounts = new int[N+1][H+1];

    for(int i=1;i<=N;i++){
      st = new StringTokenizer(br.readLine());
      for(int j=0;j<M;j++) {
        if (!st.hasMoreTokens()) break;
        studentsBlocks[i][j] = Integer.parseInt(st.nextToken());
      }
    }
    studentsCounts[0][0]=1;

    for(int studentId=1;studentId<=N;studentId++){
      for(int height=0;height<=H;height++){
        studentsCounts[studentId][height] += studentsCounts[studentId-1][height];
        for(int studentsBlockHeight:studentsBlocks[studentId]) {
          if(studentsBlockHeight == 0 || studentsBlockHeight > height) break;
          studentsCounts[studentId][height]+=studentsCounts[studentId-1][height-studentsBlockHeight];
        }
        studentsCounts[studentId][height] %= MOD;

//        System.out.println("studentID : "+studentId+" height : "+height);
//        for(int i=1;i<=N;i++){
//          for(int j=0;j<=H;j++){
//            System.out.print(studentsCounts[i][j]);
//          }
//          System.out.println();
//        }
//        System.out.println("----------------------");
      }

    }
    System.out.println(studentsCounts[N][H]);


  }
  public static void main(String[] args) throws Exception{
    new BOJ18427().solution();
  }

}