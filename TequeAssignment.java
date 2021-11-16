
// Tan De Shao
// A0218351L
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class TequeAssignment {
    static class FastReader {
        BufferedReader br;
        StringTokenizer st;

        public FastReader() {
            br = new BufferedReader(new InputStreamReader(System.in));
        }
        
        String next() {
            while (st == null || !st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            return st.nextToken();
        }

        int nextInt() {
            return Integer.parseInt(next());
        }

        long nextLong() {
            return Long.parseLong(next());
        }

        double nextDouble() {
            return Double.parseDouble(next());
        }

        String nextLine() {
            String str = "";
            try {
                str = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return str;
        }
    }

    public static void main(String[] args) {
        FastReader br = new FastReader();
        PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
        int n = Integer.parseInt(br.nextLine());
        Teque queue = new Teque();
        for (int i = 1; i <= n; i++) {
            String[] line = br.nextLine().split(" ");
            if (line[0].equals("push_back")) {
                queue.pushBack(Integer.parseInt(line[1]));
            } else if (line[0].equals("push_front")) {
                queue.pushFront(Integer.parseInt(line[1]));
            } else if (line[0].equals("push_middle")) {
                queue.pushMiddle(Integer.parseInt(line[1]));
            } else {
                out.println(queue.get(Integer.parseInt(line[1])));
            }
        }
        out.close();

    }

}

class Teque {
    QueueArr arr1 = new QueueArr();
    QueueArr arr2 = new QueueArr();

    public void pushFront(int i) {
        if (arr2.size() > arr1.size()) {
            arr1.addFront(i);
        } else {
            arr1.addFront(i);
            arr2.addFront(arr1.removeLast());
        }
    }

    public void pushBack(int i) {
        if (arr2.size() > arr1.size()) {
            arr2.addBack(i);
            arr1.addBack(arr2.removeFirst());
        } else {
            arr2.addBack(i);
        }
    }

    public void pushMiddle(int i) {
        if (arr1.size() < arr2.size()) {
            arr1.addBack(arr2.removeFirst());
            arr2.addFront(i);
        } else {
            arr2.addFront(i);
        }

    }

    public int get(int i) {
        if (i < arr1.size()) {
            return arr1.get(i);
        } else {
            return arr2.get(i - arr1.size());
        }
    }
}
