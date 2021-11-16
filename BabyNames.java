import java.util.HashMap;

public class BabyNames {

    public static void main(String[] args) {
        Kattio sc = new Kattio(System.in, System.out);
        int i = sc.getInt();
        Trie tree = new Trie();
        while (i != 0) {
            if (i == 1) {
                String s = sc.getWord();
                int strLen = s.length();
                if (strLen > 0)
                    tree.insert(s, strLen, sc.getInt());
            } else if (i == 3) {
                sc.println(tree.query(sc.getWord(), sc.getWord(), sc.getInt()));
            } else {
                tree.remove(sc.getWord());
            }          
            i = sc.getInt();
        }
        sc.close();
    }
}

class Trie {
    TrieNode root = new TrieNode(new Array(), null, null);
    
    //o(26 + 29) ops max
    public int query(String start, String end, int val) {
        TrieNode curr = this.root;
        int i = (int) start.charAt(0) - 65;
        int j = (int) end.charAt(0) - 65;
        int result1 = 0;
        int result2 = 0;
        for (int k = i; k < j; k++) {
            if (curr.arr.get(k) != null) {
                result1 += curr.arr.get(k).n1;
                result2 += curr.arr.get(k).n2;
            }
        }
        int endLen = end.length();
        curr = curr.arr.get((int) end.charAt(0) - 65);
        if (endLen > 1 && curr != null) {
            int m = 1;
            while (m < endLen) {
                int indx = (int) end.charAt(m) - 65;
                int minusFactor1 = 0;
                int minusFactor2 = 0;
                if (curr.arr.get(indx) != null) {   
                    minusFactor1 = curr.arr.get(indx).n1;
                    minusFactor2 = curr.arr.get(indx).n2;
                }
                result1 += curr.n1 - minusFactor1;
                result2 += curr.n2 - minusFactor2;
                TrieNode t = curr.arr.get(indx);
                if (t == null) {
                    break;
                } else {
                    curr = t;
                }
                m++; //indx of end str not in tree
            }
        }

        if (val == 1) {
            return result1;
        } else if (val == 2) {
            return result2;
        } else {
            return result1 + result2;
        }
    }

    // O(2L) + 26
    public void insert(String s, int strLen, int v) {
        char c = s.charAt(0);
        int indx = (int) c - 65;
        Array arr = this.root.arr;
        this.root.updateP1(indx);
        TrieNode n = insert(s, 0, v, arr.get(indx), this.root, strLen);
        arr.add(indx, n);
        this.root.updateP2(indx);
    }

    // O(2L) -> L is length of string
    public TrieNode insert(String s, int i, int v, TrieNode curr, TrieNode parent, int strLen) {
        if (i == strLen - 1) {
            if (curr == null) {
                TrieNode t = new TrieNode(new Array(), new Value(v), parent);
                t.updateNode(v);
                return t;
            } else { // curr != null
                if (curr.v != null) {
                    curr.removeV(curr.v.value);
                }
                curr.updateNode(v);
                curr.v = new Value(v);
                return curr;
            }
        } else {
            char c = s.charAt(i + 1);
            int indx = (int) c - 65;
            TrieNode b = curr;
            if (curr == null) {
                b = new TrieNode(new Array(), null, parent);
            }
            b.updateP1(indx);
            TrieNode n = insert(s, i + 1, v, b.arr.get(indx), curr, strLen);    
            b.arr.add(indx, n);
            b.updateP2(indx);
            return b;
        }
    }

    // O(2L)
    public void remove(String s) {
        int indx = (int) s.charAt(0) - 65;
        this.root.updateP1(indx);
        remove(s, 0, this.root, s.length());
        this.root.updateP2(indx);
    }

    // O(2L) -> L is length of string
    public TrieNode remove(String s, int i, TrieNode curr, int strLen) {
        if (curr == null) {
            return null;
        } else if (strLen == 1) {
            int indx = (int) s.charAt(i) - 65;
            TrieNode t = curr.arr.get(indx);
            if (t.v == null) {
                return null;
            } else if (t.arr.length > 0) {
                t.removeV(t.v.value);
                t.v = null;
                return null;
            } else {
                curr.updateP1(indx);
                curr.arr.remove(indx);
                return curr;
            }
        } else {
            int indx = (int) s.charAt(i) - 65;
            curr.updateP1(indx);
            TrieNode removalNode = remove(s, i + 1, curr.arr.get(indx), strLen - 1);
            if (removalNode != null && curr.arr.length == 1) {
                curr.arr.remove(indx);
                curr.updateP2(indx);
                return curr;
            } else {
                curr.updateP2(indx);
                return null;
            }
        }
    }

    @Override
    public String toString() {
        return this.root.toString();
    }
}

class TrieNode {
    Array arr;
    Value v;
    TrieNode parent;
    int n1 = 0;
    int n2 = 0;

    public TrieNode(Array arr, Value k, TrieNode parent) {
        this.arr = arr;
        this.v = k;
        this.parent = parent;
    }

    public void updateNode(int v) {
        if (v == 1) {
            n1++;
        } else {
            n2++;
        }
    }

    public void removeV(int v) {
        if (v == 1) {
            n1--;
        } else {
            n2--;
        }
    }


    public void updateP1(int indx) {
        TrieNode t = this.arr.get(indx);
        if (t != null) {
            n1 -= t.n1;
            n2 -= t.n2;
        }
    }
    // O(26)
    public void updateP2(int indx) {
        TrieNode t = this.arr.get(indx);
        if (t != null) {
            n1 += t.n1;
            n2 += t.n2;
        }
    }

    @Override
    public String toString() {
        return arr.toString();
    }
}

class Value {
    int value;

    public Value(int v) {
        this.value = v;
    }
}

class Array {
    int length = 0;
    TrieNode[] map = new TrieNode[26];

    void add(int i, TrieNode t) {
        map[i] = t;
        length++;
    }

    void remove(int i) {
        map[i] = null;
        length--;
    }

    TrieNode get(int i) {
        return map[i];
    }

    @Override
    public String toString() {
        return map.toString();
    }
}
