import java.util.Scanner;

public class WeakVertices {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = Integer.parseInt(sc.nextLine());
        while (v != -1) {
            boolean[] arr = new boolean[v];
            String s = "";
            int[][] adjMatrix = new int[v][v];
            for (int n = 0; n < v; n++) {
                String[] line = sc.nextLine().split(" ");
                for (int m = 0; m < v; m++) {
                    adjMatrix[n][m] = Integer.parseInt(line[m]);
                }
            }
            
            for (int i = 0; i < v; i++) {
                for (int j = 0; j < v; j++)  {
                    for (int k = 1; k < v ; k++) {
                        if (adjMatrix[i][j] == 1 && adjMatrix[i][k] == 1 && adjMatrix[j][k] == 1) {
                            arr[i] = true;
                            break;
                        }
                    }
                    
                    if (arr[i]) break;
                }
                
                if (!arr[i]) s += String.valueOf(i) + " ";
            }            
            System.out.println(s);
            v = Integer.parseInt(sc.nextLine());
        }
        sc.close();
    }
}
