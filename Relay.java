import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Collections;

class Runner {
    String name;
    float firstLeg;
    float secondLeg;

    public Runner(String runner, float t1, float t2) {
        this.name = runner;
        this.firstLeg = t1;
        this.secondLeg = t2;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

public class Relay {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try (PrintWriter out = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)))) {
            // out.println(br.readLine().split(" "));
            int num = Integer.parseInt(br.readLine());
            int i = 0;
            ArrayList<Runner> l1 = new ArrayList<>();
            while (i++ < num) {
                String[] line = br.readLine().split(" ");
                Runner r1 = new Runner(line[0], Float.parseFloat(line[1]), Float.parseFloat(line[2]));
                l1.add(r1);
            }

            ArrayList<Runner> l2 = new ArrayList<>(l1);
            Collections.sort(l1, (o1, o2) -> {
                if (o1.firstLeg < o2.firstLeg) {
                    return -1;
                }
                return 1;
            });

            Collections.sort(l2, (o1, o2) -> {
                if (o1.secondLeg < o2.secondLeg) {
                    return -1;
                }
                return 1;
            });

            float prev = Float.POSITIVE_INFINITY;
            ArrayList<Runner> result = new ArrayList<>();
            for (int j = 0; j < num; j++) {
                ArrayList<Runner> potentialResult = new ArrayList<>();
                float time = 0.0f;
                Runner firstRunner = l1.get(j);
                time += firstRunner.firstLeg;
                potentialResult.add(firstRunner);
                String name = firstRunner.name;
                int counter = 3;
                // find top 3 runners
                for (int k = 0; k < num && counter > 0; k++) {
                    Runner others = l2.get(k);
                    if (!(name == others.name)) {
                        time += others.secondLeg;
                        potentialResult.add(others);
                        counter--;
                    }
                }
                if (time < prev) {
                    prev = time;
                    result = potentialResult;
                }
            }
            
            
            out.println(String.format("%.2f", prev));
            for (int z = 0; z < result.size(); z++) {
                out.println(result.get(z));
            }
        } catch (IOException e) {
            System.out.println("error");
        }

    }
}