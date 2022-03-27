package study_04;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ1043 {
    static int person;
    static int party;

    static int[] knownTruth;
    // 파티원들이 속한 파티
    static Set<Integer>[] included;
    // 파티 안에 속한 파티원
    static int[][] parties;
    static boolean[] visitedPerson;
    static boolean[] visitedParty;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        person = Integer.parseInt(st.nextToken());
        party = Integer.parseInt(st.nextToken());

        included = new Set[person+1];
        parties = new int[party][];
        visitedPerson = new boolean[person+1];
        visitedParty = new boolean[party];

        st = new StringTokenizer(br.readLine());
        knownTruth = new int[Integer.parseInt(st.nextToken())];
        for (int i = 0; i < knownTruth.length; i++) {
            knownTruth[i] = Integer.parseInt(st.nextToken());
        }
        for (int i = 1; i <= person; i++) {
            included[i] = new HashSet<>();
        }
        for (int i = 0; i < party; i++) {
            st = new StringTokenizer(br.readLine());
            parties[i] = new int[Integer.parseInt(st.nextToken())];
            for (int j = 0; j < parties[i].length; j++) {
                parties[i][j] = Integer.parseInt(st.nextToken());
                included[parties[i][j]].add(i);
            }
        }

        System.out.println(solution());
    }

    public static int solution() {
        int excluded = 0;
        // 검증할 파티
        List<Integer> stack = new LinkedList<>();
        addStack(knownTruth, stack);

        while (!stack.isEmpty()) {
            int currentParty = stack.remove(stack.size()-1);
            addStack(parties[currentParty], stack);
        }

        for (boolean visited : visitedParty) {
            if(!visited) excluded++;
        }

        return excluded;
    }

    public static void addStack(int[] personGroup, List<Integer> stack) {
        for (int ps : personGroup) {
            if(visitedPerson[ps]) continue;

            for (int pt : included[ps]) {
                if(visitedParty[pt]) continue;

                stack.add(pt);
                visitedParty[pt] = true;
            }
            visitedPerson[ps] = true;
        }
    }
}
