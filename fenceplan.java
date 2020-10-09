

import java.io.*;
import java.util.*;

public class fenceplan {
    static int minX = Integer.MAX_VALUE;  static int minY = Integer.MAX_VALUE;
    static int maxX = Integer.MIN_VALUE;  static int maxY = Integer.MIN_VALUE;
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
            sc = new InputReader(new File("fenceplan.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static class Cow{
	    int x; 
	    int y; 
	    int id; 
	    public Cow(int x, int y, int id){
	      this.x = x;
		  this.y = y; 
		  this.id = id; 
	    }
    }
    
   static class Graph
    {
        private int V;   // No. of vertices


        // Array  of lists for Adjacency List Representation
        private LinkedList<Cow> adj[]; // Change data type as desired


        // Constructor
        @SuppressWarnings("unchecked")
        Graph(int v)
        {
            V = v;
            adj = new LinkedList[v];
            for (int i=0; i<v; ++i)
                adj[i] = new LinkedList<>();
        }

        //Function to add an edge into the graph
        void addEdge(Cow v, Cow w)
        {
            int id1 = v.id; int id2 = w.id;
            adj[id1].add(w);  // Add w to v's list.
            adj[id2].add(v);
        }


        // A function used by DFS
         void DFSUtil(Cow v, boolean[] visited)
        {
            // Mark the current node as visited and print it

            visited[v.id] = true;
            maxX = Math.max(maxX, v.x); maxY = Math.max(maxY, v.y);
            minX = Math.min(minX, v.x); minY = Math.min(minY, v.y);
            // Recur for all the vertices adjacent to this vertex
                Iterator<Cow> i = adj[v.id].listIterator();
                while (i.hasNext()) {
                    Cow n = i.next();
                    if (!visited[n.id])
                        DFSUtil(n, visited);
                }
        }
        // The function to do DFS traversal. It uses recursive DFSUtil()
        void DFS(Cow v)
        {
            // Mark all the vertices as not visited(set as
            // false by default in java)
            boolean[] visited = new boolean[V];
             DFSUtil(v, visited);

        }
    }
   
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("fenceplan.out")));
       int N =  sc.nextInt();  int M = sc.nextInt(); int ans = Integer.MAX_VALUE;
        Cow [] cows = new Cow[N]; Graph g = new Graph(N + 1);
	for (int i = 0; i < cows.length; i++) {
	    cows[i] = new Cow(sc.nextInt(), sc.nextInt(), i + 1); 	
	}
        for (int i = 0; i < M; i++) {
            g.addEdge(cows[sc.nextInt() - 1], cows[sc.nextInt() - 1]);
        }
        for (int i = 1; i <= N; i++) {
            g.DFS(cows[i - 1]);
            int x = Math.abs(maxX - minX) * 2;
            int y = Math.abs(maxY - minY) * 2;
            ans = Math.min(ans, (x +  y));
            maxY = Integer.MIN_VALUE; maxX = Integer.MIN_VALUE;
            minY = Integer.MAX_VALUE; minX = Integer.MAX_VALUE;
        }
        out.println(ans);
        out.close();    
    }
}









