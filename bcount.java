
import java.io.*;
import java.util.*;

public class bcount {

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
            sc = new InputReader(new File("bcount.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("bcount.out")));
        int N = sc.nextInt();
        int Q = sc.nextInt();
        int[] cows = new int[N];
        int[][] queries = new int[Q][2];
        int[] Holsteins = new int[N + 1]; Holsteins[0] = 0;
        int[] Guernseys = new int[N + 1]; Guernseys[0] = 0;
        int[] Jerseys = new int[N + 1]; Jerseys[0] = 0;

        for (int i = 0; i < N; i++) {
            cows[i] = sc.nextInt();
        }

        for (int i = 0; i < Q; i++) {
            queries[i][0] = sc.nextInt();
            queries[i][1] = sc.nextInt();
        }

        for (int i = 1; i <= N; i++) {      // Be sure to update but not increase on non-cows
            if(cows[i - 1] == 1){
                Holsteins[i] = Holsteins[i - 1] + 1;
                Guernseys[i] = Guernseys[i - 1];
                Jerseys[i] = Jerseys[i - 1];
            }else if(cows[i - 1] == 2){
                Guernseys[i] = Guernseys[i - 1] + 1;
                Holsteins[i] = Holsteins[i - 1];
                Jerseys[i] = Jerseys[i - 1];
            }else if(cows[i - 1] == 3){
                Jerseys[i] = Jerseys[i - 1] + 1;
                Holsteins[i] = Holsteins[i - 1];
                Guernseys[i] = Guernseys[i - 1];
            }

        }


        for (int i = 0; i < Q; i++) {
            int q1 = queries[i][0] - 1; int q2 = queries[i][1];

            out.println((Holsteins[q2] - Holsteins[q1] ) + " " + (Guernseys[q2] - Guernseys[q1]) + " " + (Jerseys[q2] - Jerseys[q1] ));

        }

        out.close();

    }
}







