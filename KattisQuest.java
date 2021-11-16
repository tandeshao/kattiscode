//Tan De shao
// A0218351L
import java.util.Scanner;
import java.util.TreeSet;

public class KattisQuest {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long limit = sc.nextLong(); 
        sc.nextLine();
        TreeSet<Quest> set = new TreeSet<>();

        for (long i = 0; i < limit; i++) {
            String[] line = sc.nextLine().split(" ");
            if (line[0].equals("add")) {
                set.add(new Quest(Long.parseLong(line[1]), Long.parseLong(line[2])));
            } else {
                Long energyLevel = Long.parseLong(line[1]);
                Quest q = new Quest(0, 0);
                long totalGold = 0, energy = energyLevel;
                while (true) {
                    q = set.floor(new Quest(energy, Long.MAX_VALUE)); 
                    if (q == null) {
                        break;
                    } else {
                        set.remove(q);
                        energy -= q.cost;
                        totalGold += q.reward;
                    }
                }
                System.out.println(totalGold);
            }
        }
        sc.close();
    }
    
}

class Quest implements Comparable<Quest> {
    long reward;
    long cost;
    static long count;
    long id;

    public Quest(long cost, long reward) {
        this.cost = cost;
        this.reward = reward;
        count++;
        id = count;
    }

    public int compareTo(Quest q) {
        if (this.cost < q.cost) {
            return -1;
        } else if (this.cost == q.cost) {
            if (this.reward < q.reward) {
                return -1;
            } else if (this.reward == q.reward) {
                if (this.id < q.id) {
                    return -1;
                } else if (this.id == q.id) {
                    return 0;
                } else {
                    return 1;
                }
            } else {
                return 1;
            }
        } else {
            return 1;
        }
    }
}