import java.util.PriorityQueue;

/**
 * MillionaireMadness
 */
public class MillionaireMadness {
    static int[][] dist;

    public static void main(String[] args) {
        Kattio sc = new Kattio(System.in, System.out);
        int rows = sc.getInt();
        int columns = sc.getInt();
        dist = new int[rows][columns];
        int[][] matrix = new int[rows][columns];
        initSSP(rows, columns);
        PriorityQueue<Vertex> pq = new PriorityQueue<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                matrix[i][j] = sc.getInt();
            }
        }
        pq.add(new Vertex(0, 0, 0));
        dist[0][0] = 0;
        int[] xdir = {1, 0, -1 ,0};
        int[] ydir = {0, 1, 0, -1};
        while (!pq.isEmpty()) {
            Vertex[] adjVertex = new Vertex[4];
            int j = 0;
            Vertex vtx = pq.poll();
            for (int i = 0; i < 4; i++) {
                int xCord = vtx.x + xdir[i];
                int yCord = vtx.y + ydir[i];
                if (xCord >= 0 && xCord < rows) {
                    if (yCord >= 0 && yCord < columns) {
                        int val;
                        int nbVal = matrix[xCord][yCord];
                        int vtxVal = matrix[vtx.x][vtx.y];
                        if (nbVal > vtxVal + vtx.weight) {
                            val = matrix[xCord][yCord] - matrix[vtx.x][vtx.y];
                        } else {
                            val = vtx.weight;
                        }
                        adjVertex[j] = new Vertex(xCord, yCord, val);
                        j++;
                    }
                }
            }

            for (Vertex item : adjVertex) {     
                if (item != null) {
                    Vertex res = relax(item, pq);
                    if (res != null) {
                        pq.offer(res);
                    }
                }
            }
        }
        sc.println(dist[rows - 1][columns - 1]);
        sc.close();

    }

    public static void initSSP(int maxX, int maxY) {
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                dist[i][j] = Integer.MAX_VALUE;
            }
        }
    }

    public static Vertex relax(Vertex v, PriorityQueue<Vertex> pq) {
        if (dist[v.x][v.y] > v.weight) {
            pq.remove(new Vertex(v.x, v.y, dist[v.x][v.y]));
            dist[v.x][v.y] = v.weight;
            return v;
        } else {
            return null;
        }
    }

}

class Vertex implements Comparable<Vertex> {
    int x;
    int y;
    int weight;

    public Vertex(int a, int b, int w) {
        this.x = a;
        this.y = b;
        this.weight = w;

    }

    @Override
    public int compareTo(Vertex o) {
        if (this.weight < o.weight) {
            return -1;
        } else if (this.weight == o.weight) {
            return 0;
        } else {
            return 1;
        }
    }
}