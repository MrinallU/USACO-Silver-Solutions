
import java.io.*;
import java.util.*;

public class pails {

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
            sc = new InputReader(new File("pails.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static boolean[][] v;
    public static int X, Y, K, M, ans;
    static class Pails{
        public int p1MilkFilled, p2MilkFilled, movesExecuted;
        public Pails(int a, int b, int c){
            this.p1MilkFilled = a;
            this.p2MilkFilled = b;
            this.movesExecuted = c;
        }
    }
    public static void ff(){
        Stack<Pails> s = new Stack<>();
        s.push(new Pails(0, 0, 0));

        while(!s.isEmpty()){
            Pails p = s.pop();
            ans = Math.min(Math.abs((p.p1MilkFilled + p.p2MilkFilled) - M), ans);
            if(p.movesExecuted < K && !v[p.p1MilkFilled][p.p2MilkFilled]){
                v[p.p1MilkFilled][p.p2MilkFilled] = true;
                // Add to p1 or p2
                s.add(new Pails(X, p.p2MilkFilled, p.movesExecuted + 1));
                s.add(new Pails(p.p1MilkFilled, Y, p.movesExecuted + 1));
                // Empty p1 or p2
                s.add(new Pails(0, p.p2MilkFilled, p.movesExecuted + 1));
                s.add(new Pails(p.p1MilkFilled, 0, p.movesExecuted + 1));
                // Fill p1 with p2 milk and sub from p2
                int pail1Diff = X - p.p1MilkFilled;
                int pail2Diff = Y - p.p2MilkFilled;
                int toFillPail1 = Math.min(pail1Diff, p.p2MilkFilled);
                int toFillPail2 = Math.min(pail2Diff, p.p1MilkFilled);

                s.add(new Pails(p.p1MilkFilled + toFillPail1, p.p2MilkFilled - toFillPail1, p.movesExecuted + 1));
                s.add(new Pails(p.p1MilkFilled - toFillPail2, p.p2MilkFilled + toFillPail2, p.movesExecuted + 1));
                // Fill p2 with p1 milk and sub from p1
            }
        }
      }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("pails.out")));
        X = sc.nextInt(); Y = sc.nextInt(); K = sc.nextInt(); M = sc.nextInt(); ans = Integer.MAX_VALUE;
        v = new boolean[X + 1][Y + 1];
        ff();
        out.println(ans);
        out.close();

    }

}





