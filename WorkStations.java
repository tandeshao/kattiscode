import java.util.PriorityQueue;
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

public class WorkStations {

    public static void main(String[] args) {
        PriorityQueue<WorkStation> queue = new PriorityQueue<>((o1, o2) -> {
            if (o1.time < o2.time) {
                return -1;
            } else if (o1.time == o2.time) {
                return 0;
            } else {
                return 1;
            }
        });

        ArrayList<Person> arr = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String[] line1 = sc.nextLine().split(" ");
        int lockdown = Integer.parseInt(line1[1]);
        int users = Integer.parseInt(line1[0]);
        for (int i = 0; i < users; i++) {
            String[] line = sc.nextLine().split(" ");
            Person person = new Person(line[0], line[1]);
            arr.add(person);
        }

        Collections.sort(arr, (o1, o2) -> {
            if (o1.arrival < o2.arrival) {
                return -1;
            } else if (o1.arrival == o2.arrival) {
                return 0;
            } else {
                return 1;
            }
        });

        int result = 0;
        int i = 0;
        while (i < users) {
            Person p = arr.get(i);
            if (queue.isEmpty()) {
                queue.add(new WorkStation(p.arrival, p.duration));
                i++;
            } else if (queue.peek().time > p.arrival) {
                queue.add(new WorkStation(p.arrival, p.duration));
                i++;
            } else if (queue.peek().time <= p.arrival && p.arrival <= queue.peek().time + lockdown) {
                WorkStation ws = queue.poll();
                ws.time = p.arrival + p.duration;
                queue.add(ws);
                result++;
                i++;
            } else {
                queue.poll();
            }
        }

        System.out.println(result);
        sc.close();
    }
}

class WorkStation {
    int time;

    public WorkStation(int arrival, int duration) {
        time = arrival + duration;
    }

}

class Person {
    int arrival;
    int duration;

    public Person(String arrival, String duration) {
        this.arrival = Integer.parseInt(arrival);
        this.duration = Integer.parseInt(duration);
    }
}