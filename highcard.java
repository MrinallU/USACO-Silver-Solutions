import java.io.*;
import java.util.*;

public class highcard {

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
            sc = new InputReader(new File("highcard.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("highcard.out")));
        
int n = sc.nextInt(); int [] elsieCards = new int[n]; boolean [] elsieVisited = new boolean[n * 2 + 1]; int points = 0;
        TreeSet<Integer> bessieQueue = new TreeSet<>();

        for (int i = 0; i < n; i++) {
            int c = sc.nextInt();
            elsieCards[i] = c;
            elsieVisited[c] = true;
        }

        for (int i = 1; i <= n * 2; i++) {
            if(!elsieVisited[i]){
                bessieQueue.add(i); // bessie has all of the cards that elsie does not have
            }
        }


        for (int card: elsieCards){
            if(bessieQueue.higher(card) != null) {
                int upperCard = bessieQueue.higher(card);
                ++points;
                bessieQueue.remove(upperCard);
            }
        }
        out.println(points);






        out.close();

    }

}





