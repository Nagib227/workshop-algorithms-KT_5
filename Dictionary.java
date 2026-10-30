import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// Задача 2. Словарь: ADD, REMOVE и сколько слов начинается с префикса.
public class Dictionary {
    static final int MAX = 1_000_005;
    static int[][] next = new int[MAX][26];
    static int[] pass = new int[MAX];
    static int ptr = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        PrintWriter out = new PrintWriter(System.out);

        int q = Integer.parseInt(in.readLine().trim());
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < q; i++) {
            StringTokenizer st = new StringTokenizer(in.readLine());
            String op = st.nextToken();
            String word = st.nextToken();

            if (op.equals("ADD"))
                change(word, 1);
            else if (op.equals("REMOVE"))
                change(word, -1);
            else {
                if (sb.length() > 0)
                    sb.append('\n');
                sb.append(count(word));
            }
        }

        out.println(sb);
        out.flush();
    }

    private static void change(String word, int delta) {
        int v = 0;
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i) - 'a';
            if (next[v][c] == 0)
                next[v][c] = ++ptr;
            v = next[v][c];
            pass[v] += delta;
        }
    }

    private static int count(String prefix) {
        int v = 0;
        for (int i = 0; i < prefix.length(); i++) {
            int c = prefix.charAt(i) - 'a';
            if (next[v][c] == 0)
                return 0;
            v = next[v][c];
        }
        return pass[v];
    }
}
