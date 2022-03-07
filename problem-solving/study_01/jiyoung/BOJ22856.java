package study_01.jiyoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;


public class BOJ22856 {

    static final int LEFT = 0;
    static final int RIGHT = 1;

    static int Node;
    static int[][] nodes;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Node = Integer.parseInt(br.readLine());
        nodes = new int[Node+1][2];

        int cnt = 0;
        while (Node > cnt++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            nodes[node][LEFT] = left;
            nodes[node][RIGHT] = right;
        }

        int edges = countRightEdge();
        System.out.println(solution(edges));

    }
    public static int countRightEdge() {
        List<Integer> stack = new ArrayList<>();
        stack.add(1);
        int count = 0;
        while(!stack.isEmpty()) {
            int node = stack.remove(stack.size() - 1);
            if(nodes[node][RIGHT] == -1) break;

            count++;
            stack.add(nodes[node][RIGHT]);
        }

        return count;
    }
    public static int solution(int edges) {
        return 2 * (Node-1) - edges;
    }

}
