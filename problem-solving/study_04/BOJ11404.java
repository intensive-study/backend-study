package study_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ11404 {
    static int N;
    static int M;
    static int[][] route;
    static final int MAX_COST = Integer.MAX_VALUE >> 1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        route = new int[N+1][N+1];
        System.out.println(MAX_COST);
        for (int row = 1; row <= N; row++) {
            Arrays.fill(route[row], MAX_COST);
        }

        for (int idx = 0; idx < M; idx++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            route[start][end] = Math.min(route[start][end], cost);
        }


        for (int mid = 1; mid <= N; mid++) {
            for (int start = 1; start <= N; start++) {
                if(mid == start) continue;
                for (int end = 1; end <= N ; end++) {
                    if(start == end || mid == end) continue;
                    route[start][end] = Math.min(route[start][end], route[start][mid] + route[mid][end]);
                }
            }
        }

        sb = new StringBuilder();
        for (int row = 1; row <= N ; row++) {
            for (int col = 1; col <= N ; col++) {
                sb.append(route[row][col] == MAX_COST? 0: route[row][col]).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }
}
