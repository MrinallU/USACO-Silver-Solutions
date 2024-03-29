
import java.io.*;
import java.util.*;

public class maxcross {

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
            sc = new InputReader(new File("maxcross.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("maxcross.out")));
        int N = sc.nextInt(); int K = sc.nextInt(); int B = sc.nextInt(); int ans = 1000000000;
        int [] prefix = new int[N + 1]; TreeSet<Integer> bLights = new TreeSet<>();
        for (int i = 0; i < B; i++) {
           bLights.add(sc.nextInt());
        }

        for (int i = 1; i <= N; i++) {
            if(bLights.contains(i)){
                prefix[i] = prefix[i - 1] + 1;
            }else{
                prefix[i] = prefix[i - 1];
            }
        }

        for (int i = 1; i <= N - (K - 1); i++) {
            int sum = prefix[i + (K - 1)] - prefix[i - 1];
            ans = Math.min(ans, sum);
        }
        
        out.println(ans);
        out.close();

    }
}







