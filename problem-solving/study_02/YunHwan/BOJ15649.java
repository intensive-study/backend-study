import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    private static int[] arr;
    private static boolean[] visited;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = i + 1;
        }
        int[] answer = new int[M];
        permutation( answer, 0, N, M);
        System.out.print(sb.toString());
    }

    private static void permutation( int[] answer,int level, int N, int M) {
        if (level == M) {
            for (int i = 0; i < answer.length; i++) {
                sb.append(answer[i]).append(" ");
            }
            sb.append("\n");
            return;
        }
        for(int i = 0;i < N;i++){
            if(!visited[i]){
                answer[level] = arr[i];
                visited[i] = true;
                permutation( answer,level + 1, N , M);
                visited[i] = false;
            }
        }
    }
}
