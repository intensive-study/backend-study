package study_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ2174 {

    static final int OK = 0;
    static final int CRASH_WALL = 1;
    static final int CRASH_OTHER = 2;

    static final int RIGHT = 0;
    static final int LEFT = 1;
    // 움직이던 방향 {상: 0, 하: 1, 좌: 2, 우: 3}
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};
    static final int[][] turningDirection = {{3, 2}, {2, 3}, {0, 1}, {1, 0}};

    static Robot[][] map;
    static Robot[] robots;
    static int X;
    static int Y;
    static int ROBOT;
    static int OP;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        X = Integer.parseInt(st.nextToken());
        Y = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        ROBOT = Integer.parseInt(st.nextToken());
        OP = Integer.parseInt(st.nextToken());

        map = new Robot[Y+1][X+1];
        robots = new Robot[ROBOT+1];
        for (int i = 1; i <= ROBOT; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            robots[i] = new Robot(Y+1-y, x, dir, i);
            map[Y+1 - y][x] = robots[i];
        }

        int result = OK;
        for (int op = 0; op < OP; op++) {
            st = new StringTokenizer(br.readLine());
            Robot target = robots[Integer.parseInt(st.nextToken())];
            String operation = st.nextToken();
            if(operation.equals("F")) {
                result = target.goStraight(Integer.parseInt(st.nextToken()));
                if(result != OK) break;
            }
            else target.turn(operation.charAt(0), Integer.parseInt(st.nextToken()));
        }
        if(result == OK) System.out.println("OK");
    }

    static class Robot {
        int row;
        int col;
        int direction;
        int idx;

        final int N = 0;
        final int S = 1;
        final int W = 2;
        final int E = 3;

        Robot(int row, int col, char direction, int idx) {
            this.row = row;
            this.col = col;
            if(direction == 'N') this.direction = N;
            else if(direction == 'S') this.direction = S;
            else if(direction == 'W') this.direction = W;
            else this.direction = E;
            this.idx = idx;
        }

        void turn(char op, int repeat) {
            for (int i = 0; i < repeat; i++) {
                if(op == 'R') this.direction = turningDirection[this.direction][RIGHT];
                else this.direction = turningDirection[this.direction][LEFT];
            }
        }

        int goStraight(int repeat) {
            // 이전의 값 초기화
            map[row][col] = null;
            for (int i = 0; i < repeat; i++) {
                this.row += dr[direction];
                this.col += dc[direction];

                // 어차피 움직이다가 명력이 완료되지 못하는 상황에는 플레이가 종료됨으로 row, col 값을 되돌리지 못하는 상황은 고려할 필요가 없음
                if(row < 1 || row > Y || col < 1 || col > X) {
                    System.out.printf("Robot %d crashes into the wall", this.idx);
                    return CRASH_WALL;
                }
                if(map[row][col] != null) {
                    System.out.printf("Robot %d crashes into robot %d", this.idx, map[row][col].idx);
                    return CRASH_OTHER;
                }
            }
            map[row][col] = this;
            return OK;
        }
    }
}
