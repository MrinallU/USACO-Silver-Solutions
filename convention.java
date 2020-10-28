import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;


public class convention {

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
            sc = new InputReader(new File("convention.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
     static int N = sc.nextInt(); static int nubBuses = sc.nextInt();static int capacity = 0;
    static int [] arr = new int[N];
    public static int binSearch(int low,int high) {
        if(low == high) return low;
        if(low + 1 == high)
        {
            if(check(low)) return low;
            return high;
        }
        int mid = (low+high)/2;
        if(check(mid)) return binSearch(low,mid);
        else return binSearch(mid+1,high);
    }
    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("convention.out")));
        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);

        out.println( binSearch(0, 1000000000));

            out.close();
        }

    private static boolean check(int interval) {
	int wagons = 1;
	int firstArrival = arr[0];
	int firstIndex = 0;
	for(int i=1;i<N;i++)
	{
		if(arr[i] - firstArrival > interval || i + 1 - firstIndex > capacity)
		{
			wagons += 1;
			firstArrival = arr[i];
			firstIndex = i;
		}
	}
	return (wagons <= nubBuses);
    }
}


 





