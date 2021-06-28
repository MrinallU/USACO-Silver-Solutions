
import java.io.*;
import java.util.*;

public class hps {

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
            sc = new InputReader(new File("hps.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("hps.out")));
 int N = sc.nextInt(); String [] arr = new String[N + 1]; int ans = 0;
        int[] h = new int [N + 1]; int[] p = new int[N + 1]; int[] s = new int[N + 1];
        String [] gestures = {"H", "P", "S"};
        for (int i = 1; i <= N; i++) {
            arr[i] = sc.next();
            if(arr[i].equals("H")){
                h[i] = h[i - 1] + 1;
                p[i] = p[i - 1];
                s[i] = s[i - 1];
            }else if(arr[i].equals("S")){
                s[i] = s[i - 1] + 1;
                h[i] = h[i - 1];
                p[i] = p[i - 1];
            }else if(arr[i].equals("P")){
                p[i] = p[i - 1] + 1;
                s[i] = s[i - 1];
                h[i] = h[i - 1];
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < 3; j++) {
                int temp = 0;
                if (i > 0) {
                    String OGMove = gestures[j];
                    switch (OGMove) {
                        case "H":
                            temp += s[0] + s[i - 1];
                            break;
                        case "S":
                            temp += p[0] + p[i - 1];
                            break;
                        case "P":
                            temp += h[0] + h[i - 1];
                            break;
                    }
                }
                for (int k = 0; k < 3; k++) {
                    int temp2 = 0;
                    String NextMove = gestures[k];
                    switch (NextMove) {
                        case "H":
                            temp2 += s[N] - s[i-1];

                            break;
                        case "S":
                            temp2 += p[N] - p[i-1];
                            break;
                        case "P":
                            temp2 += h[N] - h[i-1];
                            break;
                    }
                    ans = Math.max(temp + temp2, ans);
                }
            }
        }
        out.println(ans);
        out.close();

    }
}

    








