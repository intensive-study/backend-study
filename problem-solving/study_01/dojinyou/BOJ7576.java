import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;

public class BOJ7576 {
  class Tomato {
    int x;
    int y;
    int state;
    int day;

    Tomato(int x, int y, int state) {
      this.x = x;
      this.y = y;
      this.state = state;
      this.day = state;
    }

    public void ripe(Tomato other) {
      this.state = 1;
      this.day = other.day+1;
    }
  }
  public void solution() throws Exception {
    // 입력을 받기 위한 인스턴스 생성
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StringTokenizer st = new StringTokenizer(br.readLine());

    // 상수값 저장
    int M = Integer.parseInt(st.nextToken());
    int N = Integer.parseInt(st.nextToken());

    int finished = 0;
    int result = 0;
    Tomato[][] box = new Tomato[M][N];

    Queue<Tomato> q = new LinkedList<>();

    for(int i=0;i<N;i++) {
      st = new StringTokenizer(br.readLine());
      for(int j=0;j<M;j++) {
        box[j][i] = new Tomato(j,i,Integer.parseInt(st.nextToken()));
        if (box[j][i].state == 1) {
          q.add(box[j][i]);
        }
        if (box[j][i].state == -1) {
          finished++;
        }
      }
    }

    while(!q.isEmpty()) {
      Tomato tomato = q.poll();
      finished++;
      result = Math.max(result, tomato.day);
      if (tomato.x > 0 && box[tomato.x-1][tomato.y].state == 0) {
        Tomato leftTomato = box[tomato.x-1][tomato.y];
        leftTomato.ripe(tomato);
        q.add(leftTomato);
      }
      if (tomato.x < M-1 && box[tomato.x+1][tomato.y].state == 0) {
        Tomato rightTomato = box[tomato.x+1][tomato.y];
        rightTomato.ripe(tomato);
        q.add(rightTomato);
      }
      if (tomato.y > 0 && box[tomato.x][tomato.y-1].state == 0) {
        Tomato bottomTomato = box[tomato.x][tomato.y-1];
        bottomTomato.ripe(tomato);
        q.add(bottomTomato);
      }
      if (tomato.y < N-1 && box[tomato.x][tomato.y+1].state == 0) {
        Tomato topTomato = box[tomato.x][tomato.y+1];
        topTomato.ripe(tomato);
        q.add(topTomato);
      }
    }

    if (finished < N*M) {
      System.out.println(-1);
    } else {
      System.out.println(result-1);
    }


  }
  public static void main(String[] args) throws Exception{
    new Main().solution();
  }
}
