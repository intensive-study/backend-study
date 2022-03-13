import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static List<Integer> gaps = new ArrayList<>();
    private static boolean[] visited;
    private static int[][] status;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        final int N = Integer.parseInt(bf.readLine());

        status = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(bf.readLine());
            for (int j = 0; j < N; j++) {
                status[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N];

        combination(0, 0, N);

        System.out.println(Collections.min(gaps));

    }

    private static void combination(int start, int level, int r) {
        if (level == r / 2) {
            int a = 0, b = 0;
            for (int i = 0; i < r; i++) {
                for (int j = i + 1; j < r; j++) {
                    if (visited[i] && visited[j]) {
                        a += status[i][j] + status[j][i];
                    }
                    if (!visited[i] && !visited[j]) {
                        b += status[i][j] + status[j][i];
                    }
                }
            }

            gaps.add(Math.abs(a - b));

            return;
        }

        for (int i = start ;i < r; i++) {
            if (!visited[i]) {
                visited[i] = true;
                combination(i + 1, level + 1, r);
                visited[i] = false;
            }
        }
    }
}