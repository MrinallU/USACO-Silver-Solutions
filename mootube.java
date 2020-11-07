import java.io.*;
import java.util.*;

public class mootube {
    static int currVideos = 0;
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
            sc = new InputReader(new File("mootube.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    static class Edge {
        int val;
        int weight;

        Edge(int val, int weight) {
            this.val = val;
            this.weight = weight;
        }
    }
    static class Graph {
        private final int V;

        private final ArrayList<ArrayList<Edge>> adj;

        Graph(int V) {
            this.V = V;
            adj = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v, int r) {
            adj.get(u).add(new Edge(v, r));
            adj.get(v).add(new Edge(u, r));
        }


        void DFSUtil(Edge v, boolean[] visited, int sc, int currSc, int requiredValueToPass) {
            // Mark the current node as visited
            int currNode = v.val;
            visited[currNode] = true;


            // Recur for all the vertices adjacent to this vertex
            for (Edge n : adj.get(currNode)) {
                if (!visited[n.val] && n.weight >= requiredValueToPass) {
                    ++currVideos;
                    DFSUtil(n, visited, sc, v.val, requiredValueToPass);
                }
            }
        }

        // The function to do DFS traversal. It uses recursive DFSUtil()
        void DFS(Edge v) {
            // Mark all the vertices as not visited(set as
            // false by default in java)
            boolean[] visited = new boolean[V];

            // Call the recursive helper function to print DFS traversal

            DFSUtil(v, visited, v.val, v.val, v.weight);
        }
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("mootube.out")));
        int N = sc.nextInt(); int Q = sc.nextInt();
        Graph g = new Graph(N + 1);

        for (int i = 0; i < N - 1; i++) {
            int j = sc.nextInt(); int k = sc.nextInt(); int l = sc.nextInt();
            g.addEdge(j, k, l);
        }
        for (int i = 0; i < Q; i++) {
            int req = sc.nextInt(); int v = sc.nextInt();

            g.DFS(new Edge(v, req));
            out.println(currVideos);
            currVideos = 0;
        }
        out.close();
    }
}














