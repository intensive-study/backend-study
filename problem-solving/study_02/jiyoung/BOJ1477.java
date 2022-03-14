package study_02.jiyoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ1477 {
    static int already;
    static int need;
    static int length;

    static int[] services;
    static int[] distances;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        already = Integer.parseInt(st.nextToken());
        need = Integer.parseInt(st.nextToken());
        length = Integer.parseInt(st.nextToken());

        services = new int[already];
        distances = new int[already+1];

        st = new StringTokenizer(br.readLine());
        for (int a = 0; a < already; a++) {
            services[a] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(services);

        // 거리 초기화
        for (int idx = 1; idx < already; idx++) {
            distances[idx] = services[idx] - services[idx-1];
        }
        if(already > 0) {
            distances[0] = services[0];
            distances[already] = length - services[already - 1];
        } else {
            distances[0] = 0;
            distances[already] = length;
        }
        Arrays.sort(distances);

        System.out.println(getMiDistance());

    }
    public static int getMiDistance() {
        int start = 1;
        int end = distances[already];

        while (start <= end) {
            int mid = (start + end) / 2;
            int serviceNum = getAvailableService(mid);

            if(serviceNum > need) start = mid + 1;
            else end = mid - 1;
        }

        return start;
    }

    public static int getAvailableService(int dist) {
        int count = 0;
        for (int distance : distances) {
            if(distance == 0) continue;
            if (distance % dist == 0) count += distance / dist - 1;
            else count += distance / dist;
        }
        return count;
    }
}
