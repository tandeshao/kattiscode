//Tan De Shao
//A0218351L
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.ArrayList;
import java.util.HashMap;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class JoinString {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))) {
            int num = Integer.parseInt(br.readLine());
            if (num > 1) {
                ArrayList<String> strings = new ArrayList<>();
                HashMap<String, LinkedList<String>> order = new HashMap<>();
                
                // O(n)
                for (int i = 0; i < num; i++) {
                    strings.add(br.readLine());
                    order.put(String.valueOf(i + 1), new LinkedList<String>());
                }

                // O(n)
                for (int i = 1; i <= num - 1; i++) {
                    String str = br.readLine();
                    String[] arr = str.split(" ");
                    String key = arr[0];
                    if (i == num - 1) {
                        LinkedList<String> arr1 = new LinkedList<>();
                        arr1.addFirst(str);
                        order.put("last", arr1);
                    } else {
                        order.get(key).addFirst(str);
                    }
                }

                print(order, strings, "last", out);
                
            } else if (num == 1) {
                out.print(br.readLine());
            } 

        } catch (IOException err) {}
    }

    public static void print(HashMap<String, LinkedList<String>> order, ArrayList<String> strings, String text, PrintWriter out) {
        LinkedList<String> res = order.get(text);
        if (res.isEmpty()) {
            out.print(strings.get(Integer.parseInt(text) - 1));
        } else {
            String[] arr = res.removeFirst().split(" ");
            print(order, strings, arr[0], out);
            print(order, strings, arr[1], out);
        }
    }
}
