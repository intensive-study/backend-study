package study_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ11967 {
    static int N;
    static int M;

    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static List<int[]>[][] adjoined;
    static boolean[][][] visited;
    static boolean[][] alreadyTurnOn;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        adjoined = new List[N+1][N+1];
        visited = new boolean[N+1][N+1][N*N+1];
        alreadyTurnOn = new boolean[N+1][N+1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int startRow = Integer.parseInt(st.nextToken());
            int startCol = Integer.parseInt(st.nextToken());
            if(adjoined[startRow][startCol] == null) adjoined[startRow][startCol] = new ArrayList<>();
            adjoined[startRow][startCol].add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
        }
        System.out.println(search());
    }

    public static int search() {
        int turnOff = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {1, 1});
        visited[1][1][1] = true;
        alreadyTurnOn[1][1] = true;


        while (!queue.isEmpty()) {
            int[] curPosition = queue.poll();
            if(adjoined[curPosition[0]][curPosition[1]] != null) {
                for (int[] next : adjoined[curPosition[0]][curPosition[1]]) {
                    if(alreadyTurnOn[next[0]][next[1]]) continue;
                    System.out.println(next[0]+" "+next[1]);
                    if(next[0] == 3 && next[1] == 1) {
                        System.out.println(next[0] + " " + next[1]);
                    }
                    alreadyTurnOn[next[0]][next[1]] = true;
                    turnOff++;
                }
            }

            for (int i = 0; i < 4; i++) {
                int newRow = curPosition[0] + dr[i];
                int newCol = curPosition[1] + dc[i];
                if(newRow < 1 || newRow > N || newCol < 1 || newCol > N) continue;
                if(visited[newRow][newCol][turnOff]) continue;
                if(!alreadyTurnOn[newRow][newCol]) continue;

                queue.add(new int[] {newRow, newCol});
                visited[newRow][newCol][turnOff] = true;

            }
        }

        return turnOff;
    }

}
