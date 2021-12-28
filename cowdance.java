import java.io.*;
import java.util.*;


public class Main {
    static Kattio io;

    static {
        try {
            io = new Kattio("citystate");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int n, ans = 0, t;
    static HashMap<String, Integer> map = new HashMap<>();
    static String [] arr;

    public static void main(String[] args) {
        int n = io.nextInt();
        arr = new String[n];
        // state prefix: city prefix count
        for (int i = 0; i < n; i++) {
            String city = io.next().substring(0, 2);
            String state = io.next().substring(0, 2);
            String comb = city.concat(state);
            String revComb = state.concat(city);
            arr[i] = comb;

            if(!city.equals(state)) {
                if (!map.containsKey(revComb)) {
                    map.put(revComb, 1);
                } else {
                    map.replace(revComb, map.get(revComb) + 1);
                }
            }
        }

        for (int i = 0; i < n; i++) {
            if(map.containsKey(arr[i]))
                ans += map.get(arr[i]);
        }

        io.println(ans / 2);
        io.close();
    }

    // 0 indexed sparse seg tree (capacity 10^9)
    public static class segTree{
        int size;
        static int [] tree;

        // builds the tree
        public segTree(int size, int [] vals){
            this.size = size;
            tree = new int[2*size];
            // insert leaf nodes in tree
            for (int i = 0; i < size; i++)
                tree[size + i] = vals[i];
            // build the tree by calculating parents
            for (int i = size - 1; i > 0; --i)
                tree[size] = tree[size << 1] +
                        tree[i << 1 | 1];
        }

        public static void update(int p, int value){
            // set value at position p
            tree[p + n] = value;
            p = p + n;
            // move upward and update parents
            for (int i = p; i > 1; i >>= 1)
                tree[i >> 1] = tree[i] + tree[i^1];
        }

        // function to get operation on interval [l, r]
        public static int query(int l, int r) {
            int res = 0;
            ++r;
            // loop to find the sum in the range
            for (l += n, r += n; l < r;
                 l >>= 1, r >>= 1) {
                if ((l & 1) > 0)
                    res += tree[l++];
                if ((r & 1) > 0)
                    res += tree[--r];
            }
            return res;
        }
    }

    public static class pair implements Comparable < pair > {

        private final int x;
        private final int y;
        int w;

        public pair(final int x, int y, int w) {
            this.x = x;
            this.y = y;
            this.w = w;
        }

        public pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void print() {
            System.out.println(x + " " + y);
        }

        @Override
        public boolean equals(final Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof pair)) {
                return false;
            }

            final pair pair = (pair) o;

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
            long result = x;
            result = 31 * result + y;
            return (int) result;
        }

        @Override
        public int compareTo(pair pair) {
            return Double.compare(
                    Math.floor(Math.pow(10, 6) * ((double) pair.w / ((double) pair.y))),
                    Math.floor(Math.pow(10, 6) * ((double) w / (double) y)));
        }
    }

    static class Kattio extends PrintWriter {

        private BufferedReader r;

        private StringTokenizer st;



        // standard input

        public Kattio() {
            this(System.in, System.out);
        }

        public Kattio(InputStream i, OutputStream o) {

            super(o);

            r = new BufferedReader(new InputStreamReader(i));

        }

        // USACO-style file input

        public Kattio(String problemName) throws IOException {

            super(new FileWriter(problemName + ".out"));

            r = new BufferedReader(new FileReader(problemName + ".in"));

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



        public int nextInt() {
            return Integer.parseInt(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

    }

}


//import java.io.*;
//        import java.util.*;
//
//
//public class Main {
//    static Kattio io;
//
//    static {
//
//        io = new Kattio();
//    }
//    // 12 >=
//    static final int[] dx = {0, 0, -1, 1};
//    static final int[] dy = {-1, 1, 0, 0};
//    static PriorityQueue < n > pq = new PriorityQueue < > ();
//    static int[][] grid, dist;
//    static boolean[][] vis;
//    static int n, t;
//
//    public static void main(String[] args) {
//        n = io.nextInt();
//        t = io.nextInt();
//
//        grid = new int[n+2][n+2];
//        vis = new boolean[n+1][n+1];
//        dist = new int[n+1][n+1];
//
//        for (int i = 0; i < n+2; i++) {
//            for (int j = 0; j < n+2; j++) {
//                grid[i][j] = -1;
//            }
//        }
//
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j++) {
//                grid[i][j] = io.nextInt();
//            }
//        }
//
//        for (int i = 1; i <= n; i++) {
//            for (int j = 1; j <= n; j++) {
//                dist[i][j] = Integer.MAX_VALUE;
//            }
//        }
//
//        // dijkstra with a step counter and modifying weights everytime stepCount % 3 == 0
//
//
//        dist[1][1] = 0;
//        pq.add(new n(1, 1, 0, 0 )); // r, c, cost, steps
//        while (!pq.isEmpty()) {
//            n node = pq.poll();
//            if (vis[node.r][node.c])
//                continue;
//            vis[node.r][node.c] = true;
//
//            System.out.println("sc " + node.r + " " + node.c + " " + node.steps + " " + node.cost);
//            for (int i = 0; i < dx.length; i++) {
//                int nx = node.r + dx[i];
//                int ny = node.c + dy[i];
//
//                if(nx < 0 || ny < 0 || nx >= n+1 || ny >= n+1) continue;
//                if(grid[nx][ny] == -1) continue;
//
//
//                int calcCost = (node.steps + 1) % 3 == 0 ?
//                        (grid[nx][ny] + t)  : t;
//                System.out.println("nVals " + nx + " " + ny );
//                System.out.println((calcCost + node.cost) + " " + dx[i] + " " + dy[i]);
//
//                if(dist[nx][ny] > node.cost + calcCost) {
//                    dist[nx][ny] = node.cost + calcCost;
//                    pq.add(new n(nx, ny, dist[nx][ny], node.steps + 1));
//                }
//            }
//        }
//        io.println(dist[1][4]);
//        io.println(dist[4][4]);
//        io.println(dist[n][n]);
//        io.close();
//    }
//
//    public static class n implements Comparable<n>{
//        int r, c, cost, steps;
//
//        public n(int r, int c, int cost, int steps){
//            this.r = r;
//            this.c = c;
//            this.cost = cost;
//            this.steps = steps;
//        }
//
//        @Override
//        public int compareTo(n n) {
//            return Integer.compare(this.cost, n.cost);
//        }
//    }
//
//    public static class pair implements Comparable < pair > {
//
//        private final long x;
//        private final long y;
//        long w;
//
//        public pair(final long x, long y, long w) {
//            this.x = x;
//            this.y = y;
//            this.w = w;
//        }
//
//        public pair(long x, long y) {
//            this.x = x;
//            this.y = y;
//        }
//
//        public void print() {
//            System.out.println(x + " " + y);
//        }
//
//        @Override
//        public boolean equals(final Object o) {
//            if (this == o) {
//                return true;
//            }
//            if (!(o instanceof pair)) {
//                return false;
//            }
//
//            final pair pair = (pair) o;
//
//            if (x != pair.x) {
//                return false;
//            }
//            if (y != pair.y) {
//                return false;
//            }
//
//            return true;
//        }
//
//        @Override
//        public int hashCode() {
//            long result = x;
//            result = 31 * result + y;
//            return (int) result;
//        }
//
//        @Override
//        public int compareTo(pair pair) {
//            return Double.compare(
//                    Math.floor(Math.pow(10, 6) * ((double) pair.w / ((double) pair.y))),
//                    Math.floor(Math.pow(10, 6) * ((double) w / (double) y)));
//        }
//    }
//
//    static class Kattio extends PrintWriter {
//
//        private BufferedReader r;
//
//        private StringTokenizer st;
//
//
//
//        // standard input
//
//        public Kattio() {
//            this(System.in, System.out);
//        }
//
//        public Kattio(InputStream i, OutputStream o) {
//
//            super(o);
//
//            r = new BufferedReader(new InputStreamReader(i));
//
//        }
//
//        // USACO-style file input
//
//        public Kattio(String problemName) throws IOException {
//
//            super(new FileWriter(problemName + ".out"));
//
//            r = new BufferedReader(new FileReader(problemName + ".in"));
//
//        }
//
//
//
//        // returns null if no more input
//
//        public String next() {
//
//            try {
//
//                while (st == null || !st.hasMoreTokens())
//
//                    st = new StringTokenizer(r.readLine());
//
//                return st.nextToken();
//
//            } catch (Exception e) {}
//
//            return null;
//
//        }
//
//
//
//        public int nextInt() {
//            return Integer.parseInt(next());
//        }
//
//        public double nextDouble() {
//            return Double.parseDouble(next());
//        }
//
//        public long nextLong() {
//            return Long.parseLong(next());
//        }
//
//    }
//
//}



