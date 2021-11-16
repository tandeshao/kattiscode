import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class GCPC {
    public static void main(String[] args) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        Kattio sc = new Kattio(System.in, System.out);
        AVLTree tree = new AVLTree();
        sc.getInt();
        int m = sc.getInt();
        ArrayList<Integer> ls = new ArrayList<>();
        ls.add(0);
        ls.add(0);
        map.put(1, ls);
        tree.insert(new Node(1, 0, 0, null, null));
        for (int i = 0; i < m; i++) {
            int k = sc.getInt();
            int penalty = sc.getInt();
            if (map.get(k) == null) {
                ArrayList<Integer> arr = new ArrayList<>();
                arr.add(1);
                arr.add(penalty);
                map.put(k, arr);
            } else {
                ArrayList<Integer> arr = map.get(k);
                Node node = new Node(k, arr.get(0), arr.get(1), null, null);
                tree.remove(node);
                ArrayList<Integer> newArr = new ArrayList<>();
                newArr.add(arr.get(0) + 1);
                newArr.add(arr.get(1) + penalty);
                map.put(k, newArr);
            }
            Node fav = new Node(1, map.get(1).get(0), map.get(1).get(1), null, null);
            Node node = new Node(k, map.get(k).get(0), map.get(k).get(1), null, null);
            tree.insert(node);
            System.out.println(tree.rank(fav, tree.root));

        }

        sc.close();
    }
}

// for avl trees the order in which you put the elements changes the root of the
// tree
class AVLTree {
    Node root;

    // OLogn
    public Node search(Node n, Node curr) {
        if (curr == null || curr.key == n.key) {
            return curr;
        } else if (n.lessThan(curr)) {
            return search(n, curr.left);
        } else {
            return search(n, curr.right);
        }
    }

    public void insert(Node n) {
        this.root = insert(n, this.root);
    }

    public Node insert(Node n, Node curr) {
        if (curr == null) {
            return n;
        }
        if (n.lessThan(curr)) {
            curr.left = insert(n, curr.left);
            curr.left.parent = curr;
        } else {
            curr.right = insert(n, curr.right);
            curr.right.parent = curr;
        }

        if (curr != null) {
            curr.updateAtrributes();
        }
        curr = balance(curr);
        return curr;
    }

    public void remove(Node n) {
        this.root = remove(n, this.root);
    }

    public Node remove(Node n, Node curr) {
        if (n.key == curr.key) {
            if (curr.left == null && curr.right == null) {
                curr = null;
            } else if (curr.left == null) {
                curr.right.parent = curr.parent;
                curr = curr.right;
            } else if (curr.right == null) {
                curr.left.parent = curr.parent;
                curr = curr.left;
            } else {
                Node k = successor(curr);
                curr.key = k.key;
                curr.value = k.value;
                curr.penalty = k.penalty;
                curr.right = remove(k, curr.right);
            }
        } else if (n.lessThan(curr)) {
            curr.left = remove(n, curr.left);
        } else {
            curr.right = remove(n, curr.right);
        }

        if (curr != null) {
            curr.updateAtrributes();
            curr = balance(curr);
        }

        return curr;
    }

    public Node balance(Node n) {
        int bf = n.bf;
        if (bf == 2) {
            if (n.left.bf == -1) {
                n.left = rotateLeft(n.left);
            }
            n = rotateRight(n);
        } else if (bf == -2) {
            if (n.right.bf == 1) {
                n.right = rotateRight(n.right);
            }
            n = rotateLeft(n);
        }
        return n;
    }

    // //Ologn
    public Node successor(Node n) {
        if (n != null) {
            if (n.right != null) {
                Node result = n.right;
                // Ologn
                while (result.left != null) {
                    result = result.left;
                }
                return result;
            } else {
                Node res = n;
                // Ologn
                while (res != null && res.lessThan(n)) {
                    res = res.parent;
                }
                if (res.key != root.key) {
                    return res;
                } else if (res.moreThan(n)) {
                    return res;
                } else {
                    return null;
                }
            }
        } else {
            return null;
        }
    }

    public Node rotateLeft(Node n) {
        if (n == null) 
            return null;
        Node w = n.right;
        w.parent = n.parent;
        n.parent = w;
        n.right = w.left;
        if (w.left != null)
            w.left.parent = n;
        w.left = n; 
        n.updateAtrributes();
        w.updateAtrributes();
        return w;
    }

    public Node rotateRight(Node n) {
        if (n == null) 
            return null;
        
        Node w = n.left;
        w.parent = n.parent;
        n.parent = w;
        n.left = w.right;
        if (w.right != null)
            w.right.parent = n;
        w.right = n;
        n.updateAtrributes();
        w.updateAtrributes();
        return w;
    }

    public int rank(Node k, Node curr) {
        if (curr.key == k.key && curr.left != null) {
            return curr.left.size + 1;
        } else if (curr.key == k.key) {
            return 1;
        } else if (k.lessThan(curr)) {
            return rank(k, curr.left);
        } else if (k.moreThan(curr) && curr.left != null) {
            return curr.left.size + 1 + rank(k, curr.right);
        } else {
            return 1 + rank(k, curr.right);
        }

    }
}

class Node {
    int key;
    int value;
    int penalty;
    int height = 0;
    int size = 1;
    Node left;
    Node right;
    Node parent;
    int bf = 0;

    public Node(int k, int v, int penalty, Node left, Node right) {
        this.key = k;
        this.value = v;
        this.left = left;
        this.right = right;
        this.penalty = penalty;
    }

    public Node updateAtrributes() {
        if (this.left != null && this.right != null) {
            this.bf = this.left.height - this.right.height;
            this.height = Math.max(this.left.height, this.right.height) + 1;
            this.size = this.left.size + this.right.size + 1;
        } else if (this.right != null) {
            this.bf = -1 - this.right.height;
            this.height = this.right.height + 1;
            this.size = this.right.size + 1;
        } else if (this.left != null) {
            this.bf = this.left.height - (-1);
            this.height = this.left.height + 1;
            this.size = this.left.size + 1;
        } else {
            this.bf = 0;
            this.height = 0;
            this.size = 1;
        }
        return this;
    }

    @Override
    public String toString() {
        return String.valueOf(key);
    }

    public boolean lessThan(Node b) {
        if (this.value > b.value) {
            return true;
        } else if (this.value == b.value) {
            if (this.penalty < b.penalty) {
                return true;
            } else if (this.penalty == b.penalty) {
                return this.key < b.key;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    public boolean moreThan(Node b) {
        if (this.value <= b.value) {
            return true;
        } else if (this.value == b.value) {
            if (this.penalty >= b.penalty) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
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
