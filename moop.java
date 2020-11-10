import java.io.*;
import java.util.*;

public class moop {

    static boolean [] visited;

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
            sc = new InputReader(new File("moop.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class Graph{
        private int V;

        private ArrayList<ArrayList<Integer> > adj;
        Graph(int V){
            this.V = V;
            adj = new ArrayList<ArrayList<Integer> >(V);
            for (int i = 0; i < V; i++){
                adj.add(new ArrayList<Integer>());
            }
        }
        void addEdge(int u, int v) {
            adj.get(u).add(v);
            adj.get(v).add(u);

        }
        void printGraph() {
            for (int i = 0; i < adj.size(); i++) {
                System.out.println("\nAdjacency list of vertex" + i);
                System.out.print("head");
                for (int j = 0; j < adj.get(i).size(); j++) {
                    System.out.print(" -> "+adj.get(i).get(j));
                }
                System.out.println();
            }
        }




        void DFSUtil(int v)
        {
            // Mark the current node as visited and print it
            visited[v] = true;

            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> i = adj.get(v).listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                    DFSUtil(n);
            }
        }

        // The function to do DFS traversal. It uses recursive DFSUtil()
        int DFS(int v)
        {
            int connectedComponents = 0;

            for (int i = 0; i < v; i++) {
                if(!visited[i]) {
                    DFSUtil(i);
                    ++connectedComponents;
                }
            }
            return connectedComponents;
        }
    }



    static class point{
        int x;
        int y;
        public point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moop.out")));
        int N = sc.nextInt();
        point [] coordinates = new point[N]; visited = new boolean[N];
        Graph g = new Graph(N);

        for (int i = 0; i < N; i++) {
            coordinates[i] = new point(sc.nextInt(), sc.nextInt());
        }

        for (int i = 0; i < coordinates.length; i++) {
            for (int j = 0; j < coordinates.length; j++) {
                    if(i != j)
                        if(coordinates[i].x <= coordinates[j].x && coordinates[i].y <= coordinates[j].y)
                            g.addEdge(i, j);
            }
        }
        out.println(g.DFS(N));
        out.close();
    }
}








