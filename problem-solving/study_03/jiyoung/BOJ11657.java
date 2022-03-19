package study_03.jiyoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;


public class BOJ11657 {
    static final int START = 1;
    static final long NOT_ARRIVED = Long.MAX_VALUE;
    static int node;
    static int edge;
    static int[][] edges;
    static long[][] minWight;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        node = Integer.parseInt(st.nextToken());
        edge = Integer.parseInt(st.nextToken());
        edges = new int[edge][];
        minWight = new long[node+1][node+1];

        for (long[] weight : minWight) {
            Arrays.fill(weight, NOT_ARRIVED);
        }

        for (int e = 0; e < edge; e++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            edges[e] = new int[] {start, dest, weight};
        }

        solution();

    }

    public static void solution() {
        boolean hasNegativeCycle = bellmanFord();
        if(hasNegativeCycle) System.out.println(-1);
        else {
            for (int i = START + 1; i < node + 1; i++) {
                if(minWight[START][i] == NOT_ARRIVED) System.out.println(-1);
                else System.out.println(minWight[START][i]);
            }
        }
    }

    public static boolean bellmanFord() {
        for (int i = 0; i < node; i++) {
            for (int[] targetEdge : edges) {
                if(minWight[START][targetEdge[0]] == NOT_ARRIVED) continue;

                if (minWight[START][targetEdge[1]] > minWight[START][targetEdge[0]] + targetEdge[2]) {
                    minWight[START][targetEdge[1]] = minWight[START][targetEdge[0]] + targetEdge[2];

                    if (i == node-1) return true;
                }
            }
        }
        return false;
    }
}
