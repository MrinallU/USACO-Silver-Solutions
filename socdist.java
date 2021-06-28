import java.io.*;
import java.util.StringTokenizer;
import java.util.Arrays;

public class socdist{

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
            sc = new InputReader(new File("socdist.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static class data implements Comparable<data>{
        int d1; int d2;
        public data(int d1, int d2){
            this.d1 = d1;
            this.d2 = d2;
        }

        @Override
        public int compareTo(data o) {
            return Integer.compare(this.d1, o.d1);
        }
    }
    static boolean validate(int M, data[] arr, int D, int N){
         int currP =  -1000000000; int c = 0;
        for (int i = 0; i < M; i++) {
            while(Math.max(currP +  D, arr[i].d1) <= arr[i].d2){
                currP = Math.max(currP + D, arr[i].d1);
                ++c;
                if(c >= N){
                    break;
                }
            }
            if(c >= N){
                break;
            }
        }
        return c >= N;
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("socdist.out")));
        int N = sc.nextInt(); // num cows
        int M = sc.nextInt();

        data[] arr = new data[M];
        for (int i = 0; i < M; i++) {
            arr[i] = new data(sc.nextInt(), sc.nextInt());
        }
        Arrays.sort(arr);

        int hi = 1000000000; int lo = 0; int res = -1;

        while (lo <= hi) {
            int mid = (lo + hi) / 2;

            if (validate(M, arr, mid, N)) {
                res = mid;
                lo = mid + 1;
            }
            else {
                hi = mid - 1;
            }
        }

        out.println(res);
        out.close();
    }
}
















