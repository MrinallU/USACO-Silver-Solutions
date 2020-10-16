import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class moobuzz {

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
            sc = new InputReader(new File("moobuzz.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("moobuzz.out")));
        long N = sc.nextLong(); long high = N * 5; long low = 0; 
	while(low < high){
		long mid = (high + low) / 2; 
		long nums = mid - (mid / 3) - (mid / 5) + (mid / 15); // Finds the number of numbers not divsible by 5 and 3 
		if(nums >= N){
			high = mid; 
		}else{
			low = mid + 1; 
		}
	}
        

	out.println(low); 
        out.close();
    }
}








