
import java.io.*;
import java.util.*;

public class milkvisits {

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
        public char nextChar() {
            return (next().charAt(0));
        } // reads in the next int

        public double nextDouble() {
            return Double.parseDouble(next());
        } // reads in the next double
    }

    static InputReader sc;

    static {
        try {
            sc = new InputReader(new File("milkvisits.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static class Node{
        int val; char milkType;
        public Node(int val, char milkType){
            this.val = val;
            this.milkType = milkType;
        }
    }
    static class Graph{
        private int V;

        private final ArrayList<ArrayList<Node> > adj;
        Graph(int V){
            this.V = V;
            adj = new ArrayList<>(V);
            for (int i = 0; i < V; i++){
                adj.add(new ArrayList<>());
            }
        }
        void addEdge(Node u, Node v) {
            adj.get(u.val).add(v);
            adj.get(v.val).add(u);

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




        void  DFSUtil(int v, boolean[] visited, char required, int endPoint, ArrayList<Boolean> stack)
        {
            // Mark the current node as visited and print it 
            visited[v] = true;
            if(v == endPoint){
                isHappy = stack.contains(true);
                return;
            }
            // Recur for all the vertices adjacent to this vertex 
            for (Node node : adj.get(v)) {
                int n = node.val;
                if (!visited[n]) {
                    stack.add(node.milkType == required);
                    DFSUtil(n, visited, required, endPoint, stack);
                }
            }
            stack.remove(stack.size() - 1);
        }

        // The function to do DFS traversal. It uses recursive DFSUtil() 
        void DFS(int v, char req, int eP, int sID)
        {
            // Mark all the vertices as not visited(set as 
            // false by default in java) 
            boolean[] visited = new boolean[V];

            // Call the recursive helper function to print DFS traversal
            ArrayList<Boolean> stack1 = new ArrayList<>();
            stack1.add(nodeIDCollection[sID] == req);
            DFSUtil(v, visited, req, eP, stack1);
        }
    }
    static boolean isHappy = false; static char [] nodeIDCollection;
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("milkvisits.out")));
        int N = sc.nextInt(); int M = sc.nextInt(); String s = sc.next();
        nodeIDCollection = s.toCharArray(); Graph g = new Graph(N + 1);

        for (int i = 0; i <  N - 1; i++) {
            int n1 = sc.nextInt(); int n2 = sc.nextInt();
            g.addEdge(new Node(n1, nodeIDCollection[n1 - 1]), new Node(n2, nodeIDCollection[n2 - 1]));
        }

        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();
            char requiredMilk = sc.next().charAt(0);
            g.DFS(start, requiredMilk, end, i);
            if(isHappy)
                out.print(1);
            else
                out.print(0);
        }
        out.println();


        out.close();

    }

}





