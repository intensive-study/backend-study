import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

class Main {
    private static int N, M;
    private static int[][] edges;
    private static long[] dist; //1에서 idx 노드로 가는 최단 거리 배열

    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new int[M][3]; //시작노드, 도착노드, 비용
        dist = new long[N + 1];
        Arrays.fill(dist, (long) 1e9); //dist 초기화

        //간선 배열 input()
        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine());
            edges[idx][0] = Integer.parseInt(st.nextToken());
            edges[idx][1] = Integer.parseInt(st.nextToken());
            edges[idx][2] = Integer.parseInt(st.nextToken());
        }

        //음수 순환이 존재하는 경우
        if(bellmanFord(1)){
            System.out.println("-1");
        }
        else{
            for (int i = 2; i < N + 1; i++) {
                //최소노드로 갱신된 경우
                if (dist[i] != 1e9) System.out.println(dist[i]);
                    //도달할 수 없는 노드인 경우
                else System.out.println("-1");
            }
        }
    }

    private static boolean bellmanFord(int start) {
        dist[start] = 0; //본인 점은 거리 0
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                int now = edges[j][0];
                int next = edges[j][1];
                int cost = edges[j][2];

                // 현재 노드를 경유하며, 다음 노드로 가는 거리가 짧은 경우 갱신
                if (dist[now] != 1e9 & dist[next] > dist[now] + cost) {
                    dist[next] = dist[now] + cost;

                    //negative 순환으로 무한히 반복되는 것을 체크
                    if (i == N - 1) return true;
                }
            }
        }
        //negative 순환에 걸리지 않음
        return false;
    }

}
