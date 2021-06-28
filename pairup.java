import java.io.*;
import java.util.Comparator;
import java.util.StringTokenizer;
import java.util.*;

public class pairup {

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
            sc = new InputReader(new File("pairup.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static class Cow implements Comparable<Cow>{
       int numCow; int time;

       public Cow(int numCow, int time){
           this.numCow = numCow;
           this.time = time;
       }

        @Override
        public int compareTo(Cow o) {
            return Integer.compare(this.time, o.time);
        }
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pairup.out")));
        int N = sc.nextInt(); int ans = 0;
        Cow [] cows = new Cow[N];
        for (int i = 0; i < N; i++) {
            cows[i] = new Cow(sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(cows);

        int right = cows.length - 1; int left = 0;
        while(right >= left){
            int minCow = Math.min(cows[left].numCow, cows[right].numCow);

            cows[left].numCow -= minCow;
            cows[right].numCow -= minCow;

            int currSum = cows[left].time + cows[right].time;

            ans = Math.max(currSum, ans);

            if(cows[left].numCow <= 0){
                ++left;
            }
            if(cows[right].numCow <= 0){
                --right;
            }

        }

        out.println(ans);
        out.close();
    }
}







