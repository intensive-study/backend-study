package study_03.jiyoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1976 {
    static int N;
    static int M;

    static int[] plan;
    static Map<Integer, Set<Integer>> related;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        related = new HashMap<>();
        plan = new int[M];

        for (int row = 1; row < N+1; row++) {
            related.put(row, new HashSet<>());
            related.get(row).add(row);
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col < N+1; col++) {
                int isRelated = Integer.parseInt(st.nextToken());
                if(isRelated == 1) related.get(row).add(col);
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int idx = 0; idx < M; idx++) {
            plan[idx] = Integer.parseInt(st.nextToken());
        }

        if(isPossiblePath()) System.out.println("YES");
        else System.out.println("NO");

    }

    public static boolean isPossiblePath() {
        int[][] visited = new int[N+1][N+1];
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[] {plan[0], 1});

        if(plan.length == 1) return true;
        while (!queue.isEmpty()) {
            int[] currentValue = queue.poll();

            for (int linked : related.get(currentValue[0])) {

                if(linked == plan[currentValue[1]]) {
                    if(currentValue[1]+1 == plan.length) return true;
                    if(visited[currentValue[0]][linked] >= currentValue[1]+1) continue;
                    queue.add(new int[] {linked, currentValue[1]+1});
                    visited[currentValue[0]][linked] = currentValue[1]+1;
                } else {
                    if(visited[currentValue[0]][linked] >= currentValue[1]) continue;
                    queue.add(new int[] {linked, currentValue[1]});
                    visited[currentValue[0]][linked] = currentValue[1];
                }
            }
        }

        return false;
    }
}
