import java.io.*;
import java.util.*;


public class wormsort {
    static Kattio sc;

    static {
        try {
            sc = new Kattio("wormsort");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    adj[u].add(new Edge(v, width));
//    adj[v].add(new Edge(u, width));
    static int n, m, maxW = -1, minW = Integer.MAX_VALUE, count;
    public static ArrayList<Integer> adj[] = new ArrayList[100001];
    static HashSet<Integer> set = new HashSet<>();
    static HashSet<Integer> cmp = new HashSet<>();
    public static boolean[] visited;
    static Edge [] edg;
    static int [] arr;


    public static boolean f(int x){
        count = 0;
        cmp.clear();

        for (int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Integer>();
        for (Edge e: edg) {
            if(e.w >= x){
                adj[e.v].add(e.u);
                adj[e.u].add(e.v);

                cmp.add(e.v);
                cmp.add(e.u);
            }
        }

        it();
        return count == set.size();
    }

    public static void main(String[] args){
        n = sc.nextInt();
        m = sc.nextInt();

        visited = new boolean[adj.length];
        edg = new Edge[m];
        arr = new int [n];

        for (int i = 0; i < adj.length; i++) adj[i] = new ArrayList<Integer>();
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
            if(arr[i] != i + 1)
                set.add(arr[i]);
        }

        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            int width = sc.nextInt();

            maxW = Math.max(maxW, width);
            minW = Math.min(minW, width);
            edg[i] = new Edge(u, v, width);
        }

        Arrays.sort(edg);

        if(set.size() > 0) {
            sc.println(firstTrue(minW, maxW));
        }else{
            sc.println(-1);
        }
        sc.close();
    }

    public static void dfs(int node) {
        visited[node] = true;

        if(set.contains(node)){
            ++count;
        }

        for (int u: adj[node])
            if(!visited[u])
                dfs(u);
    }

    public static void it() {
        visited = new boolean[n+1];
        for (int i: cmp) {
            if (!visited[i]) {
                dfs(i);
            }
        }
    }

    public static int firstTrue(int lo, int hi) {
        for (--lo; lo < hi; ) {
            int mid = lo+(hi-lo+1)/2;
            if (f(mid)) lo = mid;
            else hi = mid-1;
        }
        return lo;
    }

    static class Edge implements Comparable<Edge>{
        int u, v, w;
        public Edge(int val, int v, int w){
            this.u = val;
            this.v = v;
            this.w = w;
        }

        @Override
        public int compareTo(Edge o) {
            return this.w - o.w;
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
