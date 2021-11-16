import java.util.LinkedList;
import java.util.Scanner;

// Tan De Shao
// A0218351L
class Player {
    int id;
    String action;

    public Player(int id, String action) {
        this.id = id;
        this.action = action;
    }

    @Override
    public String toString() {
        return String.format("%d", id);
    }
}

public class Coconutsplat {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] line = sc.nextLine().split(" ");
        int syllabus = Integer.parseInt(line[0]);
        int numPeople = Integer.parseInt(line[1]);
        LinkedList<Player> arr = new LinkedList<>();
        // initialise LinkedList
        for (int i = 1; i <= numPeople; i++) {
            arr.add(new Player(i, "folded"));
        }

        int indx = (0 + syllabus - 1) % arr.size();

        while (arr.size() > 1) {
            if (arr.size() == 2 && arr.get(0).id == arr.get(1).id) {
                break;
            } else {
                Player p = arr.get(indx);
                if (p.action.equals("folded")) {
                    arr.remove(indx);
                    arr.add(indx, new Player(p.id, "fist"));
                    arr.add(indx + 1, new Player(p.id, "fist"));
                    indx = (indx + syllabus - 1) % arr.size();

                } else if (p.action.equals("fist")) {
                    Player removed = arr.remove(indx);
                    removed.action = "palm";
                    arr.add(indx, removed);
                    indx = (indx + 1 + syllabus - 1) % arr.size();

                } else if (p.action.equals("palm")) {
                    arr.remove(indx);
                    indx = (indx + syllabus - 1) % arr.size();
                }
            }
        }
        System.out.println(arr.get(0));
    }
}
