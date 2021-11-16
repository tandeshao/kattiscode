import java.util.LinkedList;

public class Islands {
    static char[][] matrix = new char[0][0];
    static boolean[][] visited = new boolean[0][0];
    public static void main(String[] args) {
        Kattio sc = new Kattio(System.in, System.out);
        int rows = sc.getInt();
        int columns = sc.getInt();
        int res = 0;
        matrix = new char[rows][columns];
        visited = new boolean[rows][columns];
        for (int n = 0; n < rows; n++) {
            String line = sc.getWord();
            for (int m = 0; m < columns; m++) {
                matrix[n][m] = line.charAt(m);
            }
        }

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (matrix[i][j] == 'L' && !visited[i][j]) {
                    res++;
                    visited[i][j] = true;
                    bfs(i, j, rows, columns);
                }
            }
        }
        sc.println(res);
        sc.close();
    }

    public static void bfs(int i, int j, int xmax, int ymax) {
        LinkedList<IntegerPair> q = new LinkedList<>();
        int[] xdir = { 1, 0, -1, 0 };
        int[] ydir = { 0, -1, 0, 1 };
        q.add(new IntegerPair(i, j));
        while (!q.isEmpty()) {
            IntegerPair u = q.removeFirst();
            for (i = 0; i < 4; i++) {
                int xcord = u.first - xdir[i];
                int ycord = u.second - ydir[i];
                
                if (xcord >= 0 && xcord < xmax && ycord >= 0 && ycord < ymax) {
                    char c = matrix[xcord][ycord];
                    if (!visited[xcord][ycord] && (c == 'C' || c == 'L')) {
                        visited[xcord][ycord] = true;
                        q.add(new IntegerPair(xcord, ycord));
                    }
                }

            }
        }

    }
}

class IntegerPair {
    int first;
    int second;

    public IntegerPair(int first, int second) {
        this.first = first;
        this.second = second;
    }

}