
import java.io.*;
import java.util.*;

public class haybales  {

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
            sc = new InputReader(new File("haybales.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class v implements Comparable<v>{
        public int value;
        public int index;

        public v(int value, int index){
            this.value = value;
            this.index = index;
        }

        @Override
        public int compareTo(v o) {
            return Integer.compare(this.value, o.value);
        }
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("haybales.out")));
        int n = sc.nextInt(); int q = sc.nextInt();   int y = 1;
        TreeSet<v> set = new TreeSet<>(); int [] prefix = new int[n + 1]; prefix[0] = 0;
        ArrayList<Integer> list =  new ArrayList<>();  TreeSet<Integer> valueSet = new TreeSet<>();

        for (int i = 1; i <= n; i++) {
            int value = sc.nextInt();
            list.add(value);
            prefix[i] = prefix[i - 1] + 1;
        }

         Collections.sort(list);


        for (int i: list) {
            set.add(new v(i, y));
            valueSet.add(i);
            ++y;
        }
        set.add(new v(0, 0));
        valueSet.add(0);

        for (int i = 0; i < q; i++) {
           v q1 = new v(sc.nextInt(), -1); v q2 = new v(sc.nextInt(), -1);
           v val1 = set.lower(q1); v val2 = set.lower(q2);


           int sumIndexofQ1 = -1; int sumIndexofQ2 = -1;

           if(valueSet.contains(q1.value)){
               sumIndexofQ1 = valueSet.headSet(q1.value).size(); //gets index in prefix array
           }else if(val1 != null){
               sumIndexofQ1 = val1.index;
           }

            if(valueSet.contains(q2.value)){
                sumIndexofQ2 = valueSet.headSet(q2.value).size();
            }else if(val2 != null){
                sumIndexofQ2 = val2.index;
            }

            if(val1 == null && sumIndexofQ1 == -1 && val2 == null && sumIndexofQ2 == -1){
                out.println(0);
                continue;
            }

            if(!valueSet.contains(q1.value) && !valueSet.contains(q2.value)){
                out.println(prefix[sumIndexofQ2] - prefix[sumIndexofQ1]);
                continue;
            }

            out.println(prefix[sumIndexofQ2] - prefix[sumIndexofQ1 - 1]);

        }




        out.close();
    }
}



