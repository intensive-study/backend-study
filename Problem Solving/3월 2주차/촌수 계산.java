import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int v = sc.nextInt();
        Node[] graph = new Node[v + 1];
        for (int i = 1; i <= v; i++) {
            graph[i] = new Node(i);
        }

        int a = sc.nextInt();
        int b = sc.nextInt();

        int e = sc.nextInt();
        for (int i = 0; i < e; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            graph[x].connect(graph[y]);
        }

        List<Node> queue = new LinkedList<>();
        int[] visited = new int[v + 1];
        visited[a] = 1;
        queue.add(graph[a]);
        while (!queue.isEmpty()) {
            Node poll = queue.remove(0);
            if(poll.getData() == b){
                System.out.println(visited[b] - 1);
                return;
            }
            
            List<Node> adjacent = poll.getAdjacent();
            for (Node node : adjacent) {
                if (visited[node.getData()] == 0) {
                    queue.add(node);
                    visited[node.getData()] = visited[poll.getData()] + 1;
                }
            }
        }
        //answer
        System.out.println(-1);
    }

    static class Node {
        private final int data;
        private final List<Node> adjacent;

        public Node(int data) {
            this.data = data;
            adjacent = new LinkedList<>();
        }

        public void connect(Node node) {
            this.adjacent.add(node);
            node.getAdjacent().add(this);
        }

        public int getData() {
            return data;
        }

        public List<Node> getAdjacent() {
            return adjacent;
        }
    }
}