import java.io.*;
import java.util.*;

public class countcross {
    static Kattio sc;

    static {
        try {
            sc = new Kattio("countcross");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int n, k, r, ans;
    static boolean roadUsed = false;
    static HashMap<Pair, ArrayList<Pair>> f = new HashMap<>();
    static char [][] grid;
    static boolean [][] visited;
    static Pair [] c;


    public static void main(String[] args) {
        n = sc.nextInt();
        k = sc.nextInt();
        r = sc.nextInt();

        c = new Pair[k];
        grid = new char [n][n];
        visited = new boolean[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = '.';
            }
        }

        for (int i = 0; i < r; i++) {
            int x = sc.nextInt() - 1, y = sc.nextInt() - 1;
            int x1 = sc.nextInt() - 1, y1 = sc.nextInt() - 1;

            cont(x1, y1, x, y);
            cont(x, y, x1, y1);
        }
        
        for (int i = 0; i < k; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            grid[y - 1][x - 1] = 'c';
            c[i] = new Pair(x - 1, y - 1);
        }

        for (int r = 0; r < k; r++){
            for (int t = r + 1; t < k; t++){
                visited = new boolean[n][n];
                roadUsed = true;
                floodfill(c[r].y, c[r].x, new Pair(c[t].x, c[t].y));
                if(roadUsed) {
                    ++ans;
                }
           }
       }

        sc.println(ans);
        sc.close();
    }

    private static void cont(int x, int y, int x1, int y1) {
        if(!f.containsKey(new Pair(x1, y1))) {
            ArrayList<Pair> l = new ArrayList<>();

            l.add(new Pair(x, y));
            f.put(new Pair(x1, y1), l);
        }else{
            ArrayList<Pair> l = f.get(new Pair(x1, y1));

            l.add(new Pair(x, y));
            f.replace(new Pair(x1, y1), l);
        }
    }

    private static void floodfill(int r, int c, Pair dest) {
        if (
                (r < 0 || r >= n || c < 0 || c >= n)  // if out of bounds
                        || visited[r][c]  // already visited this square
        ) return;

        if(c == dest.x && r == dest.y){
            roadUsed = false;
        }

        visited[r][c] = true; // mark current square as visited


        // recursively call flood fill for neighboring squares
        if(f.containsKey(new Pair(c, r))) {
            if (!f.get(new Pair(c, r)).contains(new Pair(c + 1, r))) {
                floodfill(r, c + 1, dest);
            }

            if (!f.get(new Pair(c, r)).contains(new Pair(c - 1, r))) {
                floodfill(r, c - 1, dest);
            }

            if (!f.get(new Pair(c, r)).contains(new Pair(c, r - 1))) {
                floodfill(r - 1, c, dest);
            }

            if (!f.get(new Pair(c, r)).contains(new Pair(c, r + 1))) {
                floodfill(r + 1, c, dest);
            }
        } else {
            floodfill(r, c + 1, dest);
            floodfill(r, c - 1, dest);
            floodfill(r - 1, c, dest);
            floodfill(r + 1, c, dest);
        }
    }

    public static class Pair {

        private final int x;
        private final int y;

        public Pair(final int x, final int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Pair)) {
                return false;
            }

            final Pair pair = (Pair) o;

            if (x != pair.x) {
                return false;
            }
            if (y != pair.y) {
                return false;
            }

            return true;
        }

        @Override
        public int hashCode() {
            int result = x;
            result = 31 * result + y;
            return result;
        }
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
