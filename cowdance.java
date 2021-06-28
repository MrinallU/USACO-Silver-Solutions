import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class cowdance {

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
            sc = new InputReader(new File("cowdance.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowdance.out")));
        int n = sc.nextInt(); int t_MAX = sc.nextInt();
       int [] cows = new int[n];


        for (int i = 0; i < n; i++) {
            cows[i] = sc.nextInt();
        }

        int high = n; int low = 1;

        while(low != high){
            int mid = (low+high)/2;
            PriorityQueue<Integer> pq = new PriorityQueue<>();
            int t = 0; boolean isTrue = true;
            for (int j = 0; j < n; j++) {
                if(pq.size() == mid){
                    t = pq.poll();
                }
                if(t + cows[j] > t_MAX){
                    isTrue = false;
                    break;
                }
                pq.add(t + cows[j]);
            }


            if(!isTrue) low = mid + 1; else high = mid ;
        }

            out.println(low);
            out.close();
        }
    }






