import java.io.*;
import java.util.*;

public class helpcross {

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
            sc = new InputReader(new File("helpcross.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    static class Cow{
        private int beginningTime, endingTime, chickensAbleToFit;
        public Cow(int b, int e, int c){
            this.beginningTime = b;
            this.endingTime = e;
            this.chickensAbleToFit = c;
        }
    }

    static class cowComp implements Comparator<Cow>{

        @Override
        public int compare(Cow o1, Cow o2) {
             if(Integer.compare(o1.endingTime, o2.endingTime) == 0){
               return Integer.compare(o1.beginningTime, o2.beginningTime);
             }
            return Integer.compare(o1.endingTime, o2.endingTime);

        }
    }

   static TreeMap<Integer, Integer> chickens = new TreeMap<>();

    static void add(int x){
        if(chickens.containsKey(x)){
            chickens.put(x, chickens.get(x) + 1);
        } else {
            chickens.put(x, 1);
        }
    }
    static void remove(int x){
        chickens.put(x, chickens.get(x) - 1);
        if(chickens.get(x) == 0){
            chickens.remove(x);
        }
    }

    public static void main(String[] args) throws IOException {
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("helpcross.out")));
        int c = sc.nextInt(); int n = sc.nextInt();  int pairs = 0;
        LinkedList<Cow> cows = new LinkedList<>();
        for (int i = 0; i < c; i++) {
            add(sc.nextInt()) ;
        }
        for (int i = 0; i < n; i++) {
            cows.add(new Cow(sc.nextInt(), sc.nextInt(), 0));
        }
        Collections.sort(cows, new cowComp());
        for (Cow cow: cows) {
            if(chickens.higherKey(cow.beginningTime) != null && !chickens.containsKey(cow.beginningTime)){
                int currChicken = chickens.higherKey(cow.beginningTime);
                if(currChicken <= cow.endingTime){
                    ++pairs;
                    chickens.remove(currChicken);
                }
            }
            if(chickens.containsKey(cow.beginningTime)){
                int currChicken = cow.beginningTime;
                if(currChicken <= cow.endingTime){
                    ++pairs;
                    chickens.remove(currChicken);
                }
            }
        }        out.println(pairs);
        out.close();
    }
}



