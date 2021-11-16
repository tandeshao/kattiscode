import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Conformity {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HashMap<HashSet<String>, Integer> map = new HashMap<>();
        int nextNum = sc.nextInt();
        sc.nextLine();
        int highestVar = 0;
        for (int i = 0; i < nextNum; i++) {
            HashSet<String> set = new HashSet<>();
            set.addAll(Arrays.asList(sc.nextLine().split(" ")));
            if (map.get(set) == null) {
                map.put(set, 1);
            } else {
                map.put(set, map.get(set) + 1);
            }

            int value = map.get(set);
            if (value >= highestVar) {
                highestVar = value;
            }
        }

        int res = 0;
        for (Integer i : map.values()) {
            if (i == highestVar) {
                res+=i;
            }
        }
        System.out.println(res);
    }
}
