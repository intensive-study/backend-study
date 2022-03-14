package study_02.jiyoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ18430 {
    static final int UP_DOWN = 0;
    static final int LEFT_RIGHT = 1;
    static final int[][][] direction = {{{-1, 0}, {1, 0}}, {{0, -1}, {0, 1}}};

    static int row;
    static int col;
    static int[][] strength;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        strength = new int[row][col];

        for (int r = 0; r < row; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < col; c++) {
                strength[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        System.out.println(backtrack(0, 0, new boolean[row][col]));
    }

    public static int backtrack(int curRow, int value, boolean[][] visited) {
        int maxValue = value;


        for (int r = curRow; r < row; r++) {
            for (int c = 0; c < col; c++) {
                if(visited[r][c]) continue;
                visited[r][c] = true;
                for (int idx = 0; idx < 2; idx++) {
                    int upDownRow = r + direction[UP_DOWN][idx][0];
                    int upDownCol = c + direction[UP_DOWN][idx][1];

                    if(!isAccessible(upDownRow, upDownCol, visited)) continue;
                    visited[upDownRow][upDownCol] = true;
                    for (int idx2 = 0; idx2 < 2; idx2++) {
                        int leftRightRow = r + direction[LEFT_RIGHT][idx2][0];
                        int leftRightCol = c + direction[LEFT_RIGHT][idx2][1];

                        if(!isAccessible(leftRightRow, leftRightCol, visited)) continue;

                        visited[leftRightRow][leftRightCol] = true;
                        maxValue = Math.max(maxValue,
                                backtrack(r,value + (strength[r][c]*2 + strength[upDownRow][upDownCol] + strength[leftRightRow][leftRightCol]), visited));
                        visited[leftRightRow][leftRightCol] = false;
                    }
                    visited[upDownRow][upDownCol] = false;
                }
                visited[r][c] = false;
            }
        }

        return maxValue;
    }

    public static boolean isAccessible(int r, int c, boolean[][] visited) {
        if(r < 0 || r > row - 1 || c < 0 || c > col - 1 ) return false;
        return !visited[r][c];
    }
}
