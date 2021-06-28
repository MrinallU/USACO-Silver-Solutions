import java.io.*;
import java.util.*;

public class BirthdayParty {
    static int peopleReached = 0;

    
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




        void DFSUtil(int v,boolean visited[])
        {
            // Mark the current node as visited and print it
            visited[v] = true;
            ++peopleReached;
            // Recur for all the vertices adjacent to this vertex
            Iterator<Integer> i = adj.get(v).listIterator();
            while (i.hasNext())
            {
                int n = i.next();
                if (!visited[n])
                    DFSUtil(n, visited);
            }
        }

        void delEdge(int u, int v) {
            for (int i = 0; i < adj.get(u).size(); i++) {
                if(adj.get(u).get(i) == v){
                    adj.get(u).remove(i);
                    break;
                }
            }
            for (int i = 0; i < adj.get(v).size(); i++) {
                if(adj.get(v).get(i) == u){
                    adj.get(v).remove(i);
                    break;
                }
            }
        }

        // The function to do DFS traversal. It uses recursive DFSUtil()
        void DFS(int v)
        {
            // Mark all the vertices as not visited(set as
            // false by default in java)
            boolean visited[] = new boolean[V];

            // Call the recursive helper function to print DFS traversal
            DFSUtil(v, visited);
        }

        public boolean contains(int i, int j) {
            return adj.get(i).contains(j);
        }
    }
 public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        while(true){
            int p = sc.nextInt(); int c = sc.nextInt();

            if(p == 0 && c == 0)
                break;
            else {
                Graph g = new Graph(p);
                Graph g1 = new Graph(p);

                for (int i = 0; i < c; i++) {
                    int x = sc.nextInt(), y = sc.nextInt();
                    g.addEdge(x, y);
                    g1.addEdge(x, y);
                }
                /**
                 * Jason is always the og sender so we only start at his node
                 * We simulate removing a non source connected edge: call removeEdge with a for loop of all the nodes other than 0
                 */
                boolean exit = false;
                for (int i = 0; i < g1.adj.size(); i++) {
                    for (int j : g1.adj.get(i)) {
                            g.delEdge(i, j);
                            g.DFS(0); //DFS always starts from Jason
                            g.addEdge(i, j);
                            if (peopleReached < p) {
                                exit = true;
                                break;
                            }
                            peopleReached = 0;
                    }
                    if (exit) break;
                }
                if(exit)
                    System.out.println("YES");
                else
                    System.out.println("NO");
            }


        }

    }
	
}








