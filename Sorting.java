import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Sorting {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        if (n != 0) {
            sc.nextLine();
            boo(sc, n);
        }
    }

    public static void boo(Scanner sc, int n) {
        ArrayList<String> list = new ArrayList<>();
        if (n != 0) {
            while (n > 0) {
                list.add(sc.nextLine());
                n--;
            }

            Collections.sort(list, (o1, o2) -> {
                char i = o1.charAt(0);
                int j = o2.charAt(0);
                if (i < j) {
                    return -1;
                } else if (i == j) {
                    char a = o1.charAt(1);
                    char b = o2.charAt(1);
                    if (a < b) {
                        return -1;
                    } else if (a == b) {
                        return 0;
                    } else {
                        return 1;
                    }
                } else {
                    return 1;
                }
            });

            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i));
            }

            int n2 = sc.nextInt();
            if (n2 != 0) {
                sc.nextLine();
                System.out.println(" ");
                boo(sc, n2);
            }
        }
    }
}
