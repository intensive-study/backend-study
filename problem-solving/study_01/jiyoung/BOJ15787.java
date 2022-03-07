package study_01.jiyoung;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class BOJ15787 {
    static int[] trains;
    static final int SEAT = 20;
    static final int ADD = 1;
    static final int REMOVE = 2;
    // 뒤로 감
    static final int SHIFT_LEFT = 3;
    // 앞으로 감
    static final int SHIFT_RIGHT = 4;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int totalTrain = Integer.parseInt(st.nextToken());
        int operation = Integer.parseInt(st.nextToken());

        trains = new int[totalTrain];

        while (operation-- > 0){
            st = new StringTokenizer(br.readLine());
            int op = Integer.parseInt(st.nextToken());

            switch (op) {
                case ADD -> add(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
                case REMOVE -> remove(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1);
                case SHIFT_LEFT -> shiftLeft(Integer.parseInt(st.nextToken()) - 1);
                case SHIFT_RIGHT -> shiftRight(Integer.parseInt(st.nextToken()) - 1);
                default -> {
                }
            }
        }

        System.out.println(getNumberOfPassableTrain());

    }

    private static int getNumberOfPassableTrain() {
        Set<Integer> already = new HashSet<>();
        for (int train : trains) {
            already.add(train);
        }

        return already.size();
    }

    public static void add(int train, int seat) {
        trains[train] |= (1 << seat);
    }

    public static void remove(int train, int seat) {
        trains[train] &= ~(1 << seat);
    }

    // 뒤로 가는것(001 -> 010)
    public static void shiftLeft(int train) {
        trains[train] = (trains[train] << 1) & ((1 << SEAT)-1);
    }


    // 앞으로 가는것
    public static void shiftRight(int train) {
        trains[train] >>= 1;
    }

}
