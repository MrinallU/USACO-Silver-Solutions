import java.io.*;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;


public class homework {

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
            sc = new InputReader(new File("homework.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    static class The_Comparator implements Comparator<Integer> {
        @Override
        public int compare(Integer o1, Integer o2) {
            return Integer.compare(o2, o1);
        }
    }

    static class data{
        int K; int d;

        public data(int K, int d){
            this.K = K;
            this.d = d;
        }
    }

    static class compareData implements Comparator<data>{
        @Override
        public int compare(data o1, data o2) {
                return Integer.compare(o1.K, o2.K);
        }
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("homework.out")));
        int N = sc.nextInt(); PriorityQueue<data> tracker = new PriorityQueue<>(new compareData());
        int [] arr = new int[N]; int [] lows = new int [N]; int min = Integer.MAX_VALUE;
        for (int i = 0; i < N; i++) {
            int g = sc.nextInt();
            arr[i] = g;
        }

        for (int i = N - 1; i >= 0; i--) {
            if(arr[i] < min){
                min = arr[i];
            }
            lows[i] = min;
        }

        int sum = arr[N - 1]; double temp; double count = 1.0; double average = 0;
        for (int i = N - 2; i > 0; i--) {
            sum += arr[i];
            temp = (sum - lows[i]) / count;
            if(temp > average){
                average = temp;
                tracker.clear();
            }
            if(average ==  temp){
                tracker.add(new data(i, (int) average));
            }
            ++count;
        }
        for (data d : tracker) {
            out.println(d.K);
        }
        out.close();
    }
}













