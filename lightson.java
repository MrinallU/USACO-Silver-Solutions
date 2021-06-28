import javax.swing.*;
import java.io.*;
import java.util.*;


public class lightson {
    static Kattio sc;

    static {
        try {
            sc = new Kattio("lightson");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int n, m, ans = 0;
    static boolean[][] visited;
    static boolean[][] back;
    static int[][] grid;
    static int [][] sw;

    public static boolean switchOn(int x, int y) {
        boolean poss = false;
        for (int i = 0; i < m; i++) {
            if(x == sw[i][0] && y == sw[i][1]){
                grid[sw[i][2] - 1][sw[i][3] - 1] = 1;
                poss = true;
            }
        }
        return poss;
    }

    public static void main(String[] args) {
        n = sc.nextInt();
        m = sc.nextInt();

        visited = new boolean[n][n];
        back = new boolean[n][n];
        grid = new int[n][n];
        sw = new int [m][4];

        for (int i = 0; i < m; i++) {
            sw[i][0] = sc.nextInt();
            sw[i][1] = sc.nextInt();
            sw[i][2] = sc.nextInt();
            sw[i][3] = sc.nextInt();
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 0;
                visited[i][j] = false;
                back[i][j] = false;
            }
        }

        grid[0][0] = 1;

        floodfill(0, 0);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(grid[i][j] == 1)
                    ++ans;
            }
        }
        sc.println(ans);
        sc.close();
    }

    static void floodfill(int r, int c) {
        if (
                (r < 0 || r >= n || c < 0 || c >= n)  // if out of bounds
                        || grid[r][c] != 1  // wrong color
                        || visited[r][c]  // already visited this square
        ) return;

        boolean isSwitch = false;
        visited[r][c] = true; // mark current square as visited

        if(!back[r][c]) {
            isSwitch = switchOn(r + 1, c + 1);
            back[r][c] = true;
        }

        if(isSwitch)
            visited = new boolean[n][n];

        // recursively call flood fill for neighboring squares
        floodfill(r, c + 1);
        floodfill(r, c - 1);
        floodfill(r - 1, c);
        floodfill(r + 1, c);
    }


    static class Kattio extends PrintWriter {
        private BufferedReader r;
        private StringTokenizer st;

        // standard input
        public Kattio() { this(System.in,System.out); }
        public Kattio(InputStream i, OutputStream o) {
            super(o);
            r = new BufferedReader(new InputStreamReader(i));
        }
        // USACO-style file input
        public Kattio(String problemName) throws IOException {
            super(new FileWriter(problemName+".out"));
            r = new BufferedReader(new FileReader(problemName+".in"));
        }

        // returns null if no more input
        public String next() {
            try {
                while (st == null || !st.hasMoreTokens())
                    st = new StringTokenizer(r.readLine());
                return st.nextToken();
            } catch (Exception e) {}
            return null;
        }

        public int nextInt() { return Integer.parseInt(next()); }
        public double nextDouble() { return Double.parseDouble(next()); }
        public long nextLong() { return Long.parseLong(next()); }
    }

}
