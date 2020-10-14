import java.io.*;
import java.util.*;

public class closing {
   static  TreeSet<Integer> set = new TreeSet<>();

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
            sc = new InputReader(new File("closing.in"));
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
            adj[w].add(v);
        }

        void removeEdge(int v){
            for (int i: adj[v]) {
                adj[i].remove((Integer) v);
            }
            adj[v].removeAll(adj[v]);
        }

        // A function used by DFS
        int DFSUtil(int v, boolean[] visited)
        {
            // Mark the current node as visited and print it

            visited[v] = true;
            set.add(v);


            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> i = adj[v].listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                   DFSUtil(n, visited);


            }
            return 1;

        }

        // The function to do DFS traversal. It uses recursive DFSUtil()
        int DFS(int v)
        {
            // Mark all the vertices as not visited(set as
            // false by default in java)
            set.clear();
            boolean visited[] = new boolean[V];

            // Call the recursive helper function to print DFS traversal

            return DFSUtil(v, visited);
        }


    }


    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("closing.out")));
        int N = sc.nextInt(); int M = sc.nextInt(); int connectionsRequired = N;
        Graph g = new Graph(N + 1);

        for (int i = 0; i < M; i++) {
            g.addEdge(sc.nextInt(), sc.nextInt());
        }
        for (int i = 0; i < N; i++) {
            int connections = 0;
            for (int j = 1; j <= N; j++) {
                g.DFS(j);
                connections = Math.max(set.size(), connections);

            }
            if(connections == connectionsRequired){
                out.println("YES");
            }else{
                out.println("NO");
            }
            g.removeEdge(sc.nextInt());
            --connectionsRequired;
        }


        out.close();

    }
}


