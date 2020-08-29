import java.io.*;
import java.util.*;

public class measurement{

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
            sc = new InputReader(new File("measurement.in"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


static final class cow {
    public int id;
    public int val;

    public cow(int i, int v) {
        this.id = i;
        this.val = v;
    }
    public void remove(cow c){
        cows.remove(c);
    }
    public boolean equals(Object other) {
        if (!(other instanceof cow)) {
            return false;
        }
        cow otherCard = (cow) other;
        return this.val == otherCard.val &&
                this.id == otherCard.id;
    }


}

    static class Sort implements Comparator<cow> {
        public int compare(cow a, cow b) {
            return Integer.compare(b.val, a.val);
        }
    }


    static PriorityQueue<cow> cows = new PriorityQueue<>(new Sort());
        public static void main (String[]args) throws IOException {
             PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("measurement.out")));
                int N = sc.nextInt();
            int init = sc.nextInt();
            int ans = 0;

            int[][] a = new int[N][3];
            HashMap<Integer,Integer> vals = new HashMap<>();


            TreeSet<Integer> leaderBoard = new TreeSet<>();
            for (int i = 0; i < N; i++) {
                a[i][0] = sc.nextInt();
                a[i][1] = sc.nextInt();
                a[i][2] = sc.nextInt();
                vals.put(a[i][1] - 1, init);

                cow c = new cow(a[i][1], init);
                if (!cows.contains(c)) {
                    cows.add(c);
                }

                leaderBoard.add(a[i][1]);

            }

            Arrays.sort(a, new Comparator<int[]>(){
                public int compare(int [] o1, int [] o2){
                    return Integer.compare(o1[0], o2[0]);
                }
            });

            for (int i = 0; i < N; i++) {
                TreeSet<Integer> tempBoard = new TreeSet<>();
                cow ce = new cow(a[i][1], vals.get(a[i][1] - 1));
                ce.remove(ce);
                vals.replace(a[i][1] - 1, vals.get(a[i][1] - 1), vals.get(a[i][1] - 1) + a[i][2]);


                cows.add(new cow(a[i][1], vals.get(a[i][1] - 1)));


                int j = 0;
                assert cows.peek() != null;
                int max = cows.peek().val;


                for (cow c: cows) {
                    if(j == 0){
                        max = c.val;
                        tempBoard.add(c.id);
                        ++j;
                    }else if(j > 0 && c.val == max){
                        tempBoard.add(c.id);
                    }
                }

                if (!tempBoard.equals(leaderBoard)){
                    ++ans;
                }

                leaderBoard = tempBoard;

            }
            out.println(ans);
            out.close();
        }
    }