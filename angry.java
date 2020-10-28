import java.io.*;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class angry {

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
            sc = new InputReader(new File("angry.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static boolean validate(int r, int cowsRemaining, int numberOfBailsToppled, int beginning, PriorityQueue<Integer> q, int N){
        while (cowsRemaining != 0){
            boolean firstTime = true;
            while (!q.isEmpty()){
                if(firstTime) {
                    beginning = q.peek() + r;
                    firstTime = false;
                }else{
                    int currentHayBail = q.peek();
                    if(beginning + r >= currentHayBail && beginning - r <= currentHayBail){
                        numberOfBailsToppled++;
                        q.poll();
                    }else{
                        break;
                    }

                }

            }
            cowsRemaining--;
        }
        return numberOfBailsToppled == N;
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("angry.out")));
        int N = sc.nextInt();
        int K = sc.nextInt();
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.add(sc.nextInt());
        }

        // Checker

        int hi = 1000000000; int lo = 0;
        for (hi ++; lo < hi; ) {
            // returns smallest x in [lo,hi] that satisfies f
            // hi+1 if no x satisfies f
            int r = lo+hi;
            r = (r-(r&1))/2;
            PriorityQueue<Integer> q = new PriorityQueue<>(pq);
            if(validate(r, K, 0, 0, q, N)) hi = r; else lo = r+1;
        }
        out.println(lo);
        out.close();
    }
}








