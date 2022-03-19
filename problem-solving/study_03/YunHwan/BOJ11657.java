import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[] arr;
    private static boolean[] visited;
    private static int[] answer;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //그래프 초기화
        int N = Integer.parseInt(st.nextToken());
        Node[] graph = new Node[N+1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new Node(i);
        }

        //간선 초기화
        int M = Integer.parseInt(st.nextToken());
        Edge[] edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int time = Integer.parseInt(st.nextToken());

            //간선의 시작노드 끝노드, 걸리는 시간 초기화
            edges[i] = new Edge(graph[start], graph[end], time);
        }

        //게산할 정점 N - 1 개 만큼 모든 간선을 업데이트
        graph[1].cost = 0;
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
               edges[j].relax();
            }
        }

        //모든 간선을 업데이트 후에도 또 변화가 발생하면
        for (int i = 0; i < M; i++) {
            boolean changed = edges[i].relax();
            if(changed) {
                System.out.println(-1);
                return;
            }
        }

        //출력
        for (int i = 2; i <= N; i++) {
            if(!graph[i].isInfinity()){
                System.out.println(graph[i].cost);
            } else{
                System.out.println(-1);
            }
        }
    }

    private static class Node{
        private int data;
        private long cost;
        public  Node(int data){
            this.data = data;
            cost = Long.MAX_VALUE;
        }

        public boolean isInfinity() {
            return cost == Long.MAX_VALUE;
        }
    }

    private static class Edge {

        private Node start;
        private Node end;
        private int cost;

        Edge(Node start, Node end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }

        private long getCost() {
            return start.cost + cost;
        }

        //변하면 true 반환
        public boolean relax() {
            if (start.isInfinity()) { //시작 정점이 무한대 거리이면 함수 종료
                return false;
            }

            long min = Math.min(end.cost, getCost());
            if(min == end.cost){
                return false;
            }else{
                end.cost = min;
                return true;
            }
        }
    }
}