import java.util.Scanner;

public class lab1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println(boo(sc));
        sc.close();
    }

    public static String boo(Scanner input) {
        int numOfRes = input.nextInt();
        input.nextLine();
        while (numOfRes > 0) {
            int menuItems = input.nextInt();
            input.nextLine();
            String shopName = input.nextLine();
            String[] arr = new String[2];
            for (int i = 0; i < menuItems; i++) {
                String itemName = input.nextLine();
                if (itemName.equals("pea soup")) {
                    arr[0] = "avail";

                } else if (itemName.equals("pancakes")) {
                    arr[1] = "avail";
                }

                if (arr[0] != null && arr[1] != null) {
                    return shopName;
                }
            }
            numOfRes--;
        }
        return "Anywhere is fine I guess";
    }
}