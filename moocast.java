

import java.io.*;
import java.util.*;

public class moocast {

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
    static class Graph
    {
        private int V;   // No. of vertices


        // Array  of lists for Adjacency List Representation
        private LinkedList<Integer> adj[];


        // Constructor
        @SuppressWarnings("unchecked")
        Graph(int v)
        {
            V = v;
            adj = new LinkedList[v];
            for (int i=0; i<v; ++i)
                adj[i] = new LinkedList();
        }

        //Function to add an edge into the graph
        void addEdge(int v, int w)
        {
            adj[v].add(w);  // Add w to v's list.
        }


        // A function used by DFS
         int DFSUtil(int v, boolean[] visited)
        {
            // Mark the current node as visited and print it

            visited[v] = true;
            int res = 1;

                // Recur for all the vertices adjacent to this vertex
                Iterator<Integer> i = adj[v].listIterator();
                while (i.hasNext()) {
                    int n = i.next();
                    if (!visited[n])
                       res += DFSUtil(n, visited);
                }
            return res;
        }
        // The function to do DFS traversal. It uses recursive DFSUtil()
        int DFS(int v)
        {
            // Mark all the vertices as not visited(set as
            // false by default in java)
            boolean[] visited = new boolean[V];
             return DFSUtil(v, visited);

        }
    }


    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moocast.out")));
        int N = sc.nextInt(); int ans = 0;
        int[] x = new int[N];
        int[] y = new int[N];
        int[] p = new int[N];
        Graph g = new Graph(N + 1);

        for (int i = 0; i < N; i++) {

            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
            p[i] = sc.nextInt();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int squareDist = (x[i] - x[j]) * (x[i] - x[j]) + (y[i] - y[j]) * (y[i] - y[j]);
                if(squareDist <= p[i] * p[i]) {
                    g.addEdge(i, j);
                }
            }
        }

        for (int i = 0; i < N; i++) {
            ans = Math.max(g.DFS(i), ans);
        }
        out.println(ans);
        out.close();

    }
}








