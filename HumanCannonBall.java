public class HumanCannonBall {
    public static void main(String[] args) {
        Kattio sc = new Kattio(System.in, System.out);
        double startX = sc.getDouble();
        double startY = sc.getDouble();
        double endX = sc.getDouble();
        double endY = sc.getDouble();
        int n = sc.getInt();
        Coordinate[] coord = new Coordinate[n + 2];
        coord[0] = new Coordinate(startX, startY);
        coord[n + 1] = new Coordinate(endX, endY);
        // make coordinates into a collection
        for (int i = 1; i < n + 1; i++) {
            coord[i] = new Coordinate(sc.getDouble(), sc.getDouble());
        }
        double[][] d = new double[n + 2][n + 2];
        for (int i = 0; i < n + 2; i++) {
            for (int j = 0; j < n + 2; j++) {
                if (i == j) {
                    d[i][j] = 0.0;
                } else {
                    d[i][j] = calcDist(coord[i], coord[j]) / 5.0;
                }
            }
        }

        for (int i = 1; i < n + 1; i++) {
            for (int j = 1; j < n + 2; j++) {
                d[i][j] = calcFastset(coord[i], coord[j]);
            }
        }

        for (int k = 0; k < n + 2; k++) {
            for (int i = 0; i < n + 2; i++) {
                for (int j = 0; j < n + 2; j++) {
                    d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
                }
            }
        }
 
        sc.println(d[0][n + 1]);
        sc.close();
    }

    public static double calcDist(Coordinate u, Coordinate v) {
        return Math.sqrt(Math.pow(u.x - v.x, 2) + Math.pow(u.y - v.y, 2));
    }

    public static double calcFastset(Coordinate u, Coordinate v) {
        return Math.min(2 + Math.abs(((calcDist(u, v) - 50) / 5.0)), calcDist(u, v) / 5.0);
    }

}

class Coordinate {
    double x;
    double y;

    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }
}