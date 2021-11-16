import java.lang.StringBuilder;
import java.util.Scanner;

class Lab2 {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        boo(sc);
        sc.close();
        
    }

    public static void boo(Scanner sc) {
        int num = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < num; i++) {
            String word = sc.nextLine();
            display(word, i + 1);
        }
    }
    
    public static void display(String word, int i) {
        StringBuilder str = new StringBuilder("Case #");
        str.append(String.valueOf(i));
        str.append(": ");
        String prev = "nothing";
        String[] map= {"2", "22", "222", "3", "33", "333", 
                        "4", "44", "444", "5", "55", "555", "6", "66", "666", 
                        "7", "77", "777", "7777", "8", "88", "888", "9", "99", "999", "9999"};
        for (int j = 0; j < word.length(); j++) {
            char letter = word.charAt(j);
            int indx = (int) letter - 97;
            if (letter == ' ') {
                if ('0' == prev.charAt(0)) {
                    str.append(" ");
                }
                prev = "0";
                str.append("0");
            } else if (indx < 26 && indx >= 0) {
                String number = map[indx];
                if (number.charAt(0) == prev.charAt(0)) {
                    str.append(" ");
                }
                prev = number;
                str.append(number);
            }
            
        }
        System.out.println(str.toString());
    }
}