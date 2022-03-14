package study_02.jiyoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ22945 {

    static int N;
    static int[] skills;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        skills = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            skills[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solution());
    }

    public static int solution() {
        int start = 0;
        int end = N-1;

        int maxSkill = -1;
        while(start < end) {
            maxSkill = Math.max(maxSkill, ((end - start - 1) * Math.min(skills[start], skills[end])));

            if(skills[start] <= skills[end]) start++;
            else end--;
        }

        return maxSkill;

    }

}
