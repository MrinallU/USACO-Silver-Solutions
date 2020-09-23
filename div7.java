
import java.io.*;
import java.util.*;

public class div7 {

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
            sc = new InputReader(new File("div7.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
     public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("div7.out")));
        int N = sc.nextInt(); int [] arr = new int[N]; long [] prefix = new long[N + 1]; int ans = 0;
        
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            prefix[i + 1] = prefix[i] + arr[i];
        }




        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= N; j++) {
                if(j >= i){
                    break;
                }else if((prefix[i] - prefix[j]) % 7 == 0){
                        ans = Math.max(i - j, ans);
                    }

            }
        }


        out.println(ans);



        out.close();

    }
}








