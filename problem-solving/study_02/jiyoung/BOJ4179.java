package study_02.jiyoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ4179 {
    static final int JH = 0;
    static final int FIRE = 1;
    static final char BLOCK = '#';
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static int row;
    static int col;
    static char[][] map;
    static int[][][] moving;
    static Queue<int[]> queue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        map = new char[row][col];
        moving = new int[2][row][col];

        queue = new LinkedList<>();
        for (int r = 0; r < row; r++) {
            String rowOfMap = br.readLine();
            for (int c = 0; c < col; c++) {
                map[r][c] = rowOfMap.charAt(c);
                if(map[r][c] == 'J') queue.add(new int[] {JH, r, c});
                if(map[r][c] == 'F') queue.add(new int[] {FIRE, r, c});
            }
        }
        int escapable = isEscapable();
        if(escapable < 0) System.out.println("IMPOSSIBLE");
        else System.out.println(escapable);

    }

    public static int isEscapable() {
        while (!queue.isEmpty()) {
            int[] elm = queue.poll();
            if(elm[0] == JH && moving[JH][elm[1]][elm[2]] != 0 && moving[JH][elm[1]][elm[2]] == moving[FIRE][elm[1]][elm[2]]) continue;
            if(elm[0] == JH && (elm[1] == 0 || elm[1] == row-1 || elm[2] == 0 || elm[2] == col-1)) return moving[elm[0]][elm[1]][elm[2]] + 1;

            for (int i = 0; i < 4; i++) {
                int newRow = elm[1] + dr[i];
                int newCol = elm[2] + dc[i];

                if(newRow < 0 || newRow > row-1 || newCol < 0 || newCol > col-1) continue;
                if(map[newRow][newCol]== BLOCK) continue;

                if(moving[FIRE][newRow][newCol] != 0 || map[newRow][newCol] == 'F') continue;
                if(elm[0] == JH && (map[newRow][newCol] == 'J' || moving[JH][newRow][newCol] != 0)) continue;


                moving[elm[0]][newRow][newCol] = moving[elm[0]][elm[1]][elm[2]] + 1;
                queue.add(new int[] {elm[0], newRow, newCol});

            }

        }
        return -1;
    }
}
