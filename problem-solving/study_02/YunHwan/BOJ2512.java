import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] requests = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            requests[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(requests);

        int M = Integer.parseInt(br.readLine());

        int answer = 0;
        int start = 0;
        int end = requests[N - 1];
        while (start <= end) {
            int mid = (start + end) / 2;
            int sum = 0;
            for (int req : requests) {
                if(req >= mid) sum += mid;
                else sum+= req;
            }
            if (sum > M) {
                end = mid - 1;
            } else{
                start = mid + 1;
                answer = Math.max(answer, mid);
            }
        }

        System.out.println(answer);
    }
}