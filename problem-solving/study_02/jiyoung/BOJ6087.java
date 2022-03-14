package study_02.jiyoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ6087 {
    static int row;
    static int col;
    static char[][] map;
    static int[][][] visited;

    // 움직이던 방향 {상: 0, 하: 1, 좌: 2, 우: 3}
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static final int[] opposite = {1, 0, 3, 2};
    static final char BLOCK = '*';
    static final char TARGET = 'C';


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[][] start = new int[2][2];

        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        map = new char[row][col];
        visited = new int[2][row][col];
        Queue<int[]> queue = new LinkedList<>();

        int cnt = -1;
        for (int r = 0; r < row; r++) {
            String curRow = br.readLine();
            for (int c = 0; c < col; c++) {
                map[r][c] = curRow.charAt(c);
                if(map[r][c] == TARGET) {
                    start[++cnt] = new int[] {r, c};
                    // 상 {row, col, used, direction, which target}
                    queue.add(new int[] {start[cnt][0], start[cnt][1], 0, 0, cnt});
                    // 하
                    queue.add(new int[] {start[cnt][0], start[cnt][1], 0, 1, cnt});
                    // 좌
                    queue.add(new int[] {start[cnt][0], start[cnt][1], 0, 2, cnt});
                    // 우
                    queue.add(new int[] {start[cnt][0], start[cnt][1], 0, 3, cnt});
                }
            }
        }

        System.out.println(searchPath(queue, start));

    }

    public static int searchPath(Queue<int[]> queue, int[][] start) {
        int minUsed = Integer.MAX_VALUE;
        while (!queue.isEmpty()) {
            int[] target = queue.poll();
            int currentUsed = target[2];

            for (int idx = 0; idx < 4; idx++) {
                if(opposite[target[3]] == idx) continue;
                int newRow = target[0] + dr[idx];
                int newCol = target[1] + dc[idx];

                if(newRow < 0 || newRow > row-1 || newCol < 0 || newCol > col-1) continue;
                // 이동한 지점이 본인인 경우
                if(newRow == start[target[4]][0] && newCol == start[target[4]][1]) continue;
                if(map[newRow][newCol] == BLOCK) continue;
                if(map[newRow][newCol] == TARGET) {
                    if(target[3] == idx) minUsed = Math.min(minUsed, currentUsed);
                    else minUsed = Math.min(minUsed, currentUsed+1);
                }

                if(target[3] == idx) {
                    if(visited[target[4]][newRow][newCol] != 0 && visited[target[4]][newRow][newCol] < currentUsed) continue;
                    queue.add(new int[]{newRow, newCol, currentUsed, idx, target[4]});
                    visited[target[4]][newRow][newCol] = currentUsed;

                } else {
                    if(visited[target[4]][newRow][newCol] != 0 && visited[target[4]][newRow][newCol] < currentUsed+1) continue;
                    queue.add(new int[]{newRow, newCol, currentUsed+1, idx, target[4]});
                    visited[target[4]][newRow][newCol] = currentUsed+1;
                }

            }
        }
        return minUsed;
    }

}
