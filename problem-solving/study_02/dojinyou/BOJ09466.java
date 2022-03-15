import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

/**
 * BOJ09466
 */
public class BOJ09466 {
  public void solution() throws Exception{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());
    final int TOTAL_CASE = Integer.parseInt(st.nextToken());
    for (int i=0;i<TOTAL_CASE;i++) {
      st = new StringTokenizer(br.readLine());
      int totalStudents = Integer.parseInt(st.nextToken());
      int[] selectInfo = new int[totalStudents+1];
      st = new StringTokenizer(br.readLine());
      for (int j =1;j<=totalStudents;j++) {
        selectInfo[j] = Integer.parseInt(st.nextToken());
      }
      boolean[] visited = new boolean[totalStudents+1];
      int result = 0;
      for (int j =0;j<=totalStudents;j++) {
        if (!visited[j]) {
          result += makeTeam(j, visited, selectInfo);
        }
      } 
      System.out.println(result);
    }
  }
public int makeTeam(int studentId, boolean[] visited, int[] selectInfo) {
  HashMap<Integer, Integer> teamMember = new HashMap<>(); 
  teamMember.put(studentId, 0);
  int nextStudentId = selectInfo[studentId];
  visited[studentId] = true;
  int count = 1;
  while(!teamMember.containsKey(nextStudentId)) {
    if (visited[nextStudentId]) {
      return count;
    }
    teamMember.put(nextStudentId, count);
    visited[nextStudentId] = true;
    nextStudentId = selectInfo[nextStudentId];
    count++;
  }
  return teamMember.get(nextStudentId);
}

  public static void main(String[] args) throws Exception {
    new BOJ09466().solution();
  }
}
