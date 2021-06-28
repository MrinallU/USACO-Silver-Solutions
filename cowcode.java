

import java.io.*;
import java.util.*;

public class cowcode {

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
            sc = new InputReader(new File("cowcode.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class v implements Comparable<v> {
        public int value;
        public int index;

        public v(int value, int index) {
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(v o) {
            return Integer.compare(this.value, o.value);
        }
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("cowcode.out")));
        String s = sc.next(); int n =  sc.nextInt();
        StringBuilder modString = new StringBuilder();


        modString.append(s); // Adds the original string to the builder
        while (modString.length() < n) {
            StringBuilder tempBuilder = new StringBuilder();
            tempBuilder.append(modString);
            tempBuilder.insert(0, tempBuilder.charAt(tempBuilder.length() - 1));
            tempBuilder.replace(tempBuilder.length() - 1, tempBuilder.length(), "");
            modString.append(tempBuilder);
        }

        out.println(modString.charAt(n - 1));
        out.close();
    }
}



