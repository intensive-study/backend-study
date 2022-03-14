import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(bf.readLine());

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        while (w != 0 || h != 0) {
            int[][] map = new int[h][w];
            for (int i = 0; i < h; i++) {
                st = new StringTokenizer(bf.readLine());
                for (int j = 0; j < w; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            int sum = 0;

            for (int i = 0; i < h; i++) {
                for (int j = 0; j < w; j++) {
                    if(map[i][j] == 1){
                        sum++;
                        dfs(map, i, j);
                    }
                }
            }

            System.out.println(sum);

            st = new StringTokenizer(bf.readLine());

            w = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
        }

    }

    private static void dfs(int[][] map, int i, int j) {
        if(map[i][j] == 1){
            map[i][j] = 2;
            int[] pos;
            while ((pos = getAdjacent(map, i, j)) != null) {
                dfs(map, pos[0], pos[1]);
            }
        }
    }

    private static int[] getAdjacent(int[][] map,int i, int j) {

        int[][] pos = {
                {i - 1, j - 1}, {i - 1, j}, {i - 1, j + 1},
                {i, j - 1}, {i, j + 1},
                {i + 1, j - 1}, {i + 1, j}, {i + 1, j + 1}
        };

        for (int k = 0; k < pos.length; k++) {
            if((pos[k][0] >= 0 && pos[k][0] < map.length)
                    && (pos[k][1] >= 0 && pos[k][1] < map[0].length)){
                if(map[pos[k][0]][pos[k][1]] == 1){
                    return pos[k];
                }
            }
        }
        return null;
    }
}