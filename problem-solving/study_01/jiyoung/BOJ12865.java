package study_01.jiyoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ12865 {
    static int totalItem;
    static int weight;
    static int[][] items;
    static int[][] maxValue;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        totalItem = Integer.parseInt(st.nextToken());
        weight = Integer.parseInt(st.nextToken());
        items = new int[totalItem+1][2];
        maxValue = new int[totalItem+1][weight+1];

        int count = totalItem;
        while (count > 0){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            items[totalItem-count+1][0] = w;
            items[totalItem-count+1][1] = v;
            count--;
        }


        for (int item = 1; item <= totalItem; item++) {
            for (int w = 1; w <= weight; w++) {
                maxValue[item][w] = maxValue[item-1][w];
                if(w < items[item][0]) continue;
                maxValue[item][w] = Math.max(maxValue[item-1][w], items[item][1] + maxValue[item-1][w-items[item][0]]);
            }
        }

        System.out.println(maxValue[totalItem][weight]);
    }
}
