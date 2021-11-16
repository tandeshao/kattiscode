import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class Ladice {
    public static void main(String[] args) {
        Kattio sc = new Kattio(System.in, System.out);
        int n = sc.getInt();
        int l = sc.getInt();
        UFDS set = new UFDS(l);
        for (int k = 0; k < n; k++) {
            int set1 = sc.getInt() - 1;
            int set2 = sc.getInt() - 1;
            int m = set.unionSet(set1, set2);
            if (set.size[m] < set.limit[m]) {
                sc.println("LADICA");
                set.setSize(m);
            } else {
                sc.println("SMECE");
            }
        }
        sc.close();
    }
}

class UFDS {
    int[] parent;
    int[] limit;
    int[] rank;
    int[] size;

    public UFDS(int n) {
        this.parent = new int[n];
        this.limit = new int[n];
        this.size = new int[n];
        this.rank = new int[n];
        for (int i = 0; i < n; i++) {
            this.parent[i] = i;
            this.limit[i] = 1;
            this.rank[i] = 0;
            this.size[i] = 0;
        }
    }

    public int findSet(int i) {
        if (parent[i] == i) {
            return i;
        } else {
            int p = findSet(parent[i]);
            parent[i] = p;
            return p;
        }
    }

    public boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    public int unionSet(int i, int j) {
        if (!isSameSet(i, j)) {
            int p1 = findSet(i);
            int p2 = findSet(j);
            if (rank[p1] > rank[p2]) {
                parent[p2] = p1;
                limit[p1] += limit[p2];
                size[p1] += size[p2];
                return p1;
            } else {
                parent[p1] = p2;
                limit[p2] += limit[p1];
                size[p2] += size[p1];
                if (rank[p1] == rank[p2]) {
                    rank[p2]++;
                }
                return p2;
            }
        } else {
            return findSet(i);
        }
    }

    public void setSize(int i) {
        size[i]++;
    }
}

class Kattio extends PrintWriter {
    public Kattio(InputStream i) {
        super(new BufferedOutputStream(System.out));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public Kattio(InputStream i, OutputStream o) {
        super(new BufferedOutputStream(o));
        r = new BufferedReader(new InputStreamReader(i));
    }

    public boolean hasMoreTokens() {
        return peekToken() != null;
    }

    public int getInt() {
        return Integer.parseInt(nextToken());
    }

    public double getDouble() {
        return Double.parseDouble(nextToken());
    }

    public long getLong() {
        return Long.parseLong(nextToken());
    }

    public String getWord() {
        return nextToken();
    }

    private BufferedReader r;
    private String line;
    private StringTokenizer st;
    private String token;

    private String peekToken() {
        if (token == null)
            try {
                while (st == null || !st.hasMoreTokens()) {
                    line = r.readLine();
                    if (line == null)
                        return null;
                    st = new StringTokenizer(line);
                }
                token = st.nextToken();
            } catch (IOException e) {
            }
        return token;
    }

    private String nextToken() {
        String ans = peekToken();
        token = null;
        return ans;
    }
}