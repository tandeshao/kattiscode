import java.util.ArrayList;
import java.util.*;

public class Dominos {
    public static void main(String[] args) {
        Kattio sc = new Kattio(System.in, System.out);
        int testCase = sc.getInt();
        for (int i = 0; i < testCase; i++) {
            Graph g = new Graph(sc.getInt());
            int limit = sc.getInt();
            for (int j = 0; j < limit; j++) {
                int a = sc.getInt();
                int b = sc.getInt();
                g.addEdge(a - 1, b - 1);
            }
            sc.println(g.printSCCs());
        }
        sc.close();

    }

}

// This class represents a directed graph using adjacency list
// representation
class Graph {
    private int v; // No. of vertices
    private ArrayList<Integer>[] adj; // Adjacency List

    Graph(int v) {
        this.v = v;
        @SuppressWarnings("unchecked")
        ArrayList<Integer>[] adjacency = (ArrayList<Integer>[]) new ArrayList<?>[v];
        adj = adjacency;
        for (int i = 0; i < v; ++i)
            adj[i] = new ArrayList<>();
    }

    void addEdge(int v, int w) {
        adj[v].add(w);
    }

    Stack<Integer> toposort(ArrayList<Integer>[] arr) {
        Stack<Integer> stack = new Stack<>();
        boolean[] visited = new boolean[arr.length];
        for (int i = 0; i < arr.length; i++) {
            if (!visited[i]) {
                DFSrec(i, visited, arr, stack);
            }
        }
        return stack;
    }

    void DFSrec(int u, boolean visited[], ArrayList<Integer>[] adj, Stack<Integer> stack) {
        visited[u] = true;
        for (Integer vtx : adj[u]) {
            if (!visited[vtx]) {
                DFSrec(vtx, visited, adj, stack);
            }
        }
        stack.push(u);
    }

    int printSCCs() {
        Stack<Integer> stack = toposort(adj);
        boolean[] visited = new boolean[v];
        int result = 0;
        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (!visited[u]) {
                result++;
                DFSrec(u, visited, adj, new Stack<Integer>());
            }
        }
        return result;
    }
}