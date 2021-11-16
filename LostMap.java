import java.util.ArrayList;
import java.util.PriorityQueue;

public class LostMap {
    public static void main(String[] args) {
        Kattio sc = new Kattio(System.in, System.out);
        int n = sc.getInt();
        MapGraph graph = new MapGraph(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == j) {
                    sc.getInt(); 
                    continue;
                }
                graph.addEdge(i, new IntTriple(i, j, sc.getInt()));
            }
        }
        graph.Prim(sc);
        sc.close();
    }
}

class MapGraph {
    ArrayList<IntTriple>[] adjList;
    int length;

    MapGraph(int length) {
        @SuppressWarnings("unchecked")
        ArrayList<IntTriple>[] adj = (ArrayList<IntTriple>[]) new ArrayList<?>[length];
        int i = 0;
        while (i < length) {
            adj[i] = new ArrayList<>();
            i++;
        }
        adjList = adj;
        this.length = length;
    }

    void addEdge(int vtx, IntTriple u) {
        adjList[vtx].add(u);
    }

    void Prim(Kattio sc) {
        PriorityQueue<IntTriple> pq = new PriorityQueue<>((x, y) -> {
            if (x.third < y.third) {
                return -1;
            } else if (x.third == y.third) {
                return 0;
            } else {
                return 1;
            }
        });
        boolean[] visited = new boolean[length];
        visited[0] = true;
        for (IntTriple item : adjList[0]) {
            pq.add(item);
        }
        while (!pq.isEmpty()) {
            IntTriple p = pq.poll();
            if (!visited[p.second]) {
                visited[p.second] = true;
                sc.println(String.valueOf(p.first + 1) + " " + String.valueOf(p.second + 1));
                for (IntTriple item : adjList[p.second]) {
                    pq.add(item);
                }
            }
        }
    }
}

class IntTriple {
    int first;
    int second;
    int third;

    public IntTriple(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }

}
