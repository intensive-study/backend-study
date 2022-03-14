import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int W = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        //지도 입력
        char[][] map = new char[H][W];
        for (int i = H-1; i >= 0; i--) {
            String line = br.readLine();
            map[i] = line.toCharArray();
        }

        Point start = new Point(findC(map, 0));
        Point end = new Point(findC(map, 1));

        List<Point> queue = new LinkedList<>();
        queue.add(start);

        int min = Integer.MAX_VALUE;

        int[][] visited = new int[H][W];
        visited[start.getY()][start.getX()] = 1;

        int[] xd = {1, 0, -1, 0};
        int[] yd = {0, 1, 0, -1};
        while (!queue.isEmpty()) {
            Point p = queue.remove(0);
            if (p.isArrived(end)) {
                min = Math.min(min, p.getMirror());
                continue;
            }

            for (int i = 0; i < 4; i++) {
                int nextX = p.getX() + xd[i];
                int nextY = p.getY() + yd[i];

                //벽을 만났을 때
                if(nextX < 0 || nextX >= W || nextY < 0 || nextY >= H
                        || map[nextY][nextX] == '*'){
                    continue;
                }

                int prevMirror = p.getMirror();

                if(p.getDirect() != -1 && p.getDirect() != i){
                    prevMirror+= 1;
                }

                //처음 방문일 때
                if (visited[nextY][nextX] == 0 || visited[nextY][nextX] >= prevMirror) {
                    visited[nextY][nextX] = prevMirror;
                    queue.add(new Point(nextX, nextY, i, prevMirror));
                }

            }
        }

        System.out.println(min);
    }

    private static int[] findC(char[][] map,int order){
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j] == 'C') {
                    if(order <= 0){
                        return new int[]{j,i};
                    }
                    order--;
                }
            }
        }
        return null;
    }


    private static class Point{
        private int x;
        private int y;
        private int direct;

        private int mirror;

        public Point(int[] coord) {
            x = coord[0];
            y = coord[1];
            direct = -1;
        }

        public Point(int x, int y, int direct, int mirror) {
            this.x = x;
            this.y = y;
            this.direct = direct;
            this.mirror = mirror;
        }

        public void setPosiition(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setDirect(int direct) {
            this.direct = direct;
        }

        public void setX(int x) {
            this.x = x;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getDirect() {
            return direct;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getMirror() {
            return mirror;
        }

        public void countMirror() {
            mirror++;
        }

        public boolean isArrived(Point point) {
            if(this.x == point.getX() && this.y == point.getY())
                return true;
            return false;
        }
    }
}
