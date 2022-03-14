package study_02.jiyoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BOJ7576 {
    static final int RIPEN = 1;
    static final int NOT_RIPEN = 0;
    static final int EMPTY = -1;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    static int row;
    static int col;
    static int[][] record;
    static int[][] tomatoes;
    static int totalNotRipenTomato = 0;
    static Queue<int[]> queue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        tomatoes = new int[row][col];
        record = new int[row][col];

        queue = new LinkedList<>();

        for (int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < col; c++) {
                tomatoes[r][c] = Integer.parseInt(st.nextToken());
                if(tomatoes[r][c] == NOT_RIPEN) totalNotRipenTomato++;
                if(tomatoes[r][c] == RIPEN) {
                    queue.add(new int[] {r, c});
                }

            }
        }
        System.out.println(calcMinNumberOfDays());
    }

    public static int calcMinNumberOfDays() {

        if(totalNotRipenTomato == 0) return 0;

        int maxDay = 0;
        while (!queue.isEmpty()) {
            int[] position = queue.poll();

            for (int i = 0; i < 4; i++) {
                int newRow = position[0]+dr[i];


                int newCol = position[1]+dc[i];

                if(newRow < 0 || newRow > row-1 || newCol < 0 || newCol > col-1) continue;
                if(record[newRow][newCol] != 0 || tomatoes[newRow][newCol] == RIPEN) continue;
                if(tomatoes[newRow][newCol] == EMPTY) continue;

                record[newRow][newCol] = record[position[0]][position[1]] + 1;
                maxDay = Math.max(maxDay, record[newRow][newCol]);
                queue.add(new int[] {newRow, newCol});
                totalNotRipenTomato--;
            }

            if (totalNotRipenTomato == 0) break;
        }

        if(totalNotRipenTomato > 0) return -1;
        return maxDay;
    }
}
