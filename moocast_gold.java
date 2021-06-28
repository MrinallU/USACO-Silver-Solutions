import java.io.*;
import java.util.*;

public class moocast_gold {

    static boolean [] visited; static int cowsReached = 0;

    static class InputReader {
        BufferedReader reader;
        StringTokenizer tokenizer;

        public InputReader(File stream) throws FileNotFoundException {
            reader = new BufferedReader(new FileReader(stream), 32768);
            tokenizer = null;
        }
        String next() { // reads in the next string
            while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                try {
                    tokenizer = new StringTokenizer(reader.readLine());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return tokenizer.nextToken();
        }

        public int nextInt() {
            return Integer.parseInt(next());
        } // reads in the next int

        public long nextLong() {
            return Long.parseLong(next());
        } // reads in the next long

        public double nextDouble() {
            return Double.parseDouble(next());
        } // reads in the next double
    }

    static InputReader sc;

    static {
        try {
            sc = new InputReader(new File("moocast.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class Graph{

        private final ArrayList<ArrayList<Integer>> adj;
        Graph(int V){
            adj = new ArrayList<>(V);
            for (int i = 0; i < V; i++){
                adj.add(new ArrayList<>());
            }
        }
        void addEdge(int u, int v) {
            adj.get(u).add(v);
            adj.get(v).add(u);

        }


        void DFSUtil(int v)
        {
            // Mark the current node as visited and print it
            visited[v] = true;
            ++cowsReached;
            // Recur for all the vertices adjacent to this vertex
            for (int n : adj.get(v)) {
                if (!visited[n])
                    DFSUtil(n);
            }
        }

        // The function to do DFS traversal. It uses recursive DFSUtil()
        void DFS(int v)
        {
            cowsReached = 0;
            DFSUtil(v);
        }
    }



    static class point{
        int val;
        int x;
        int y;
        public point(int val ,int x, int y){
            this.val = val;
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
        int N = sc.nextInt();  visited = new boolean[N];
        point [] coordinates = new point[N];

        for (int i = 0; i < N; i++) {
            coordinates[i] = new point(i, sc.nextInt(), sc.nextInt());
        }
        //binary search
        int hi = (int) Math.pow(25000, 2); int lo = 0;
        for (hi ++; lo < hi; ) {
            // returns smallest x in [lo,hi] that satisfies f
            // hi+1 if no x satisfies f
            int mid = (lo+hi)/2;
            if(validate(mid, N , coordinates)) hi = mid; else lo = mid+1;
        }
        out.println(lo);
        out.close();
    }

    private static boolean validate(int mid, int n, point [] coords) {
        Graph g1 = new Graph(n);
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if(i != j){
                    double distance = Math.sqrt(Math.pow((coords[i].x-coords[j].x), 2) + Math.pow((coords[i].y-coords[j].y), 2));
                    if (distance <= Math.sqrt(mid)){
                        g1.addEdge(coords[i].val, coords[j].val);
                    }
                }
            }
        }
        visited = new boolean[n];
        g1.DFS(0);
        return cowsReached == n;
    }
}








