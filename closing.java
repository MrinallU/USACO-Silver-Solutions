import java.io.*;
import java.util.*;


public class closing {
    static Kattio sc;

    static {
        try {
            sc = new Kattio("closing");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static int n, m;
    static ArrayList<Integer> adj[];
    static boolean [] visited, closed;

    public static void dfs(int node) {
        visited[node] = true;
        for (int u: adj[node])
            if(!visited[u] && !closed[u])
                dfs(u);
    }

    public static void close(int b){
        closed[b] = true;
    }

    public static int count_components() {
        int count = 0;
        visited = new boolean[n + 1]; // reset
        for (int i = 1; i <= adj.length-1; i++){
            if(!visited[i] && !closed[i]){
                count++;
                dfs(i);
            }
        }
        return count;
    }

    public static void main(String[] args){
        n = sc.nextInt();
        m = sc.nextInt();

        visited = new boolean[n + 1];
        closed = new boolean[n + 1];
        adj = new ArrayList[n + 1]; // can be one indexed

        for (int i = 0; i <= n; i++) adj[i] = new ArrayList<Integer>();
        for (int i = 0; i < m; i++) {
            int u = sc.nextInt();
            int v = sc.nextInt();
            adj[u].add(v);
            adj[v].add(u);
        }

        for(int i = 0; i < n; ++i){
            if(count_components() == 1){
                sc.println("YES");
            }else{
                sc.println("NO");
            }
            close(sc.nextInt());
        }

        sc.close();
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







