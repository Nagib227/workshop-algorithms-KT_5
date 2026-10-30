import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 5. k-е слово с данным префиксом. Более короткое слово меньше своего продолжения.
public class WordKth {
    static final int MAX = 1_000_005;
    static int[][] next = new int[MAX][26];
    static int[] sub = new int[MAX];
    static int[] ends = new int[MAX];
    static int ptr = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int q = Integer.parseInt(in.readLine().trim());
        StringBuilder outSb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            String op = st.nextToken();
            String word = st.nextToken();

            if (op.equals("ADD"))
                change(word, 1);
            else if (op.equals("REMOVE"))
                change(word, -1);
            else {
                int k = Integer.parseInt(st.nextToken());
                if (outSb.length() > 0)
                    outSb.append('\n');
                outSb.append(kth(word, k));
            }
        }

        out.println(outSb);
        out.flush();
    }

    private static void change(String word, int delta) {
        int v = 0;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (next[v][c] == 0)
                next[v][c] = ++ptr;
            v = next[v][c];
            sub[v] += delta;
        }
        if (delta > 0)
            ends[v] = 1;
        else
            ends[v] = 0;
    }

    private static String kth(String prefix, int k) {
        int v = 0;
        for (int i = 0; i < prefix.length(); i++) {
            int c = prefix.charAt(i) - 'a';
            if (next[v][c] == 0)
                return "NONE";
            v = next[v][c];
        }
        if (k > sub[v])
            return "NONE";

        StringBuilder sb = new StringBuilder(prefix);
        return pick(v, k, sb);
    }

    private static String pick(int v, int k, StringBuilder sb) {
        if (ends[v] == 1) {
            if (k == 1)
                return sb.toString();
            k--;
        }

        for (int c = 0; c < 26; c++) {
            int to = next[v][c];
            if (to == 0 || sub[to] == 0)
                continue;
            if (k <= sub[to]) {
                sb.append((char) ('a' + c));
                return pick(to, k, sb);
            }
            k -= sub[to];
        }

        return "NONE";
    }
}
