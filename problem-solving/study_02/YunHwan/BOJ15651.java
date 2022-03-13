import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[] arr;
    private static boolean[] visited;
    private static int[] answer;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }

        answer = new int[M];
        combination(0, N, M);

        System.out.println(sb.toString());
    }

    private static void combination(int r,int N, int M) {
        if (r == M) {
            for (int i = 0; i < M; i++) {
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 0; i < N;i++){
                answer[r] = arr[i];
                combination(r + 1, N, M);
        }
    }
}